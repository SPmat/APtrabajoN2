package app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import comparadores.ComparadorAtraccion;
import comparadores.ComparadorPromocion;
import dao.AtraccionDAOImpl;
import dao.DAOFactory;
import dao.PromocionDAOImpl;
import dao.UsuarioDAOImpl;
import jdbc.ConnectionProvider;
import model.Atraccion;
import model.Itinerario;
import model.Promocion;
import model.TipoDeAtraccion;
import model.Usuario;

//La aplicacion se encarga del manejo y organizacion de todas las atracciones y promociones disponibles.

public class Aplicacion {

	// Todas las listas necesarias para organizar
	List<Atraccion> todasLasAtracciones = new ArrayList<Atraccion>();
	List<Atraccion> atraccionesDeAventura = new ArrayList<Atraccion>();
	List<Atraccion> atraccionesDeDegustacion = new ArrayList<Atraccion>();
	List<Atraccion> atraccionesDePaisaje = new ArrayList<Atraccion>();

	List<Promocion> todasLasPromociones= new ArrayList<Promocion>();
	List<Promocion> promocionesDeAventura = new ArrayList<Promocion>();
	List<Promocion> promocionesDeDegustacion = new ArrayList<Promocion>();
	List<Promocion> promocionesDePaisaje = new ArrayList<Promocion>();
	
	
	AtraccionDAOImpl atrImp= DAOFactory.getAtraccionDAO();
	UsuarioDAOImpl userImp= DAOFactory.getUsuarioDAO();
	PromocionDAOImpl promoImp= DAOFactory.getPromocionDAO();


	// El constructor recibe una lista de atracciones
	public Aplicacion(List<Atraccion> Atracciones, List<Promocion> promociones) {
		this.todasLasAtracciones = Atracciones;
		this.todasLasPromociones=promociones;

	}

	public void separarEnListas() {

		for (int i = 0; i < this.todasLasAtracciones.size(); i++) {

			if (this.todasLasAtracciones.get(i).getTipo() == TipoDeAtraccion.AVENTURA) {
				atraccionesDeAventura.add(todasLasAtracciones.get(i));

			} else if (this.todasLasAtracciones.get(i).getTipo() == TipoDeAtraccion.PAISAJE) {
				atraccionesDePaisaje.add(todasLasAtracciones.get(i));

			} else if (this.todasLasAtracciones.get(i).getTipo() == TipoDeAtraccion.DEGUSTACION) {
				atraccionesDeDegustacion.add(todasLasAtracciones.get(i));

			}
		}

		//separo las promociones
		
		for (int i = 0; i < todasLasPromociones.size(); i++) {

			if (todasLasPromociones.get(i).getAtraccionesEnPromocion().get(0).getTipo() == TipoDeAtraccion.AVENTURA) {
				promocionesDeAventura.add(todasLasPromociones.get(i));

			} else if (todasLasPromociones.get(i).getAtraccionesEnPromocion().get(0).getTipo() == TipoDeAtraccion.PAISAJE) {
				promocionesDePaisaje.add(todasLasPromociones.get(i));

			} else if (todasLasPromociones.get(i).getAtraccionesEnPromocion().get(0).getTipo() == TipoDeAtraccion.DEGUSTACION) {
				promocionesDeDegustacion.add(todasLasPromociones.get(i));

			}
		}
		
		ordenarListas();

	}
	
	protected void ordenarListas() {
		//atracciones
		atraccionesDeAventura.sort(new ComparadorAtraccion().reversed());
		atraccionesDePaisaje.sort(new ComparadorAtraccion().reversed());
		atraccionesDeDegustacion.sort(new ComparadorAtraccion().reversed());
		todasLasAtracciones.sort(new ComparadorAtraccion().reversed());
		
		//promociones
		promocionesDeAventura.sort(new ComparadorPromocion().reversed());
		promocionesDePaisaje.sort(new ComparadorPromocion().reversed());
		promocionesDeDegustacion.sort(new ComparadorPromocion().reversed());
		todasLasPromociones.sort(new ComparadorPromocion().reversed());
	}

	public void ofrecerTodo(Usuario unUsuario) throws IOException {

		System.out.println("Bienvenido " + unUsuario.getNombre() + ", vamos a comenzar: \n\n");
		
		System.out.println("Ya vas a ir a: \n");
		
		System.out.println(unUsuario.itinerarioToString());
		
		System.out.println("\n A donde mas te gustaria ir?");

		switch(unUsuario.getAtraccionFavorita()) {
			
		case PAISAJE:
			//luego de sugerir cada cosa actualiza los datos para que tengan los cupos al dia
			Sugeridor.sugerirPromos(unUsuario, promocionesDePaisaje);
			Sugeridor.sugerirAtracciones(unUsuario, atraccionesDePaisaje);
			Sugeridor.sugerirPromosNoFavoritas(unUsuario, todasLasPromociones);
			Sugeridor.sugerirAtraccionesNoFavoritas(unUsuario, todasLasAtracciones);
			break;

		case DEGUSTACION:
			
			Sugeridor.sugerirPromos(unUsuario, promocionesDeDegustacion);
			Sugeridor.sugerirAtracciones(unUsuario, atraccionesDeDegustacion);
			Sugeridor.sugerirPromosNoFavoritas(unUsuario, todasLasPromociones);
			Sugeridor.sugerirAtraccionesNoFavoritas(unUsuario, todasLasAtracciones);
			break;

		case AVENTURA:
			
			Sugeridor.sugerirPromos(unUsuario, promocionesDeAventura);
			Sugeridor.sugerirAtracciones(unUsuario, atraccionesDeAventura);
			Sugeridor.sugerirPromosNoFavoritas(unUsuario, todasLasPromociones);
			Sugeridor.sugerirAtraccionesNoFavoritas(unUsuario, todasLasAtracciones);
			break;

		case DEFAULT:
			System.out.println("Hay una Atraccion cuyos datos no fueron correctamente ingresados.");
		}

		/////////////////////////////////////////////////////

		//crearArchivoUsuario(unUsuario);
		
		//ItinerarioDAOImpl cargadorDeItinerario= DAOFactory.getItinerarioDAO();
		
		//cargadorDeItinerario.cargarItinerarioUser(unUsuario);

	}
	
	/*public void crearArchivoUsuario(Usuario unUsuario) throws IOException {
		
		BufferedWriter bw = null;

		try {
			String ruta = "archivos_de_salida/"+unUsuario.getNombre()+".txt";
			new File("archivos_de_salida/").mkdir();							// Se crea la carpeta para guardar los archivos de salida.		
			//String ruta = unUsuario.getNombre() + ".txt";
			String contenido = unUsuario.toString();
			File file = new File(ruta);
			// Si el archivo no existe es creado
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(contenido);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bw.close();
		}

	}*/
	
	//metodo para que los datos de las atracciones se mantengan actualizados
	//a pesar de que los cambios se hagan en distintos objetos
	public void actualizarDesdeBaseDeDatos() {
		this.todasLasAtracciones= DAOFactory.getAtraccionDAO().findAll();
		this.todasLasPromociones= DAOFactory.getPromocionDAO().findAll();
		this.separarEnListas();
	}
	
	public void separarItinerario(List<Usuario> todosLosUsuarios, List<Itinerario> todoElItinerario) {

		for (Usuario cadaUsuario : todosLosUsuarios) {

			cadaUsuario.vaciarItinerario();

			for (Itinerario cadaItinerario : todoElItinerario) {

				if (cadaUsuario.getNombre().equals(cadaItinerario.getUsuario().getNombre())) {

					cadaUsuario.agregarAlItinerario(cadaItinerario.getAtraccion());

				}

			}

		}
	}


	public void borrarItinerario() throws SQLException {
	
		String sql = "DELETE FROM itinerarios";
		
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);

			statement.executeUpdate();
	}

//TODO fijarse si sigue sirviendo
	public void actualizarAtracciones(List<Atraccion> atracciones) throws SQLException {

		for (Atraccion atr : atracciones) {

			atrImp.update(atr);

		}

	}

	public void actualizarUsuarios(List<Usuario> usuarios) throws SQLException {

		for (Usuario user : usuarios) {

			userImp.update(user);

		}

	}

//TODO fijarse si sigue sirviendo
	public void actualizarPromociones(List<Promocion> promociones) throws SQLException {

		for (Promocion promocion: promociones) {

			promoImp.updateAtracciones(promocion);

		}

	}
}