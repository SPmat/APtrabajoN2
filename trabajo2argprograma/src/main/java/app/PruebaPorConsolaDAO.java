package app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.AtraccionDAOImpl;
import dao.ItinerarioDAOImpl;
import dao.PromocionDAOImpl;
import dao.UsuarioDAOImpl;
import jdbc.ConnectionProvider;
import model.Atraccion;
import model.Itinerario;
import model.Promocion;
import model.Usuario;

public class PruebaPorConsolaDAO {
	

	public static void main(String[] args) throws IOException, SQLException {

		List<Atraccion> todasLasAtracciones = new ArrayList<Atraccion>();
		List<Usuario> todosLosUsuarios = new ArrayList<Usuario>();
		List<Promocion> todasLasPromociones = new ArrayList<Promocion>();
		List<Itinerario> todosLosItinerarios = new ArrayList<Itinerario>();

		//LectorDeArchivos lector = new LectorDeArchivos();

	//	lector.leerUsuarios(todosLosUsuarios);
	//	lector.leerAtracciones(todasLasAtracciones);
	//	lector.leerPromos(todasLasPromociones, todasLasAtracciones);
		
		PromocionDAOImpl promociones = new PromocionDAOImpl();
		UsuarioDAOImpl usuarios = new UsuarioDAOImpl();
		AtraccionDAOImpl atracciones = new AtraccionDAOImpl();
		ItinerarioDAOImpl itinerarios = new ItinerarioDAOImpl();
		
		
		todasLasAtracciones= atracciones.findAll();
		todosLosUsuarios= usuarios.findAll();
		todasLasPromociones= promociones.findAll();
		todosLosItinerarios= itinerarios.findAll();
		

		Aplicacion app = new Aplicacion(todasLasAtracciones, todasLasPromociones);

		app.separarEnListas();	
		
		
		app.separarItinerario(todosLosUsuarios, todosLosItinerarios);
		
		

		
		
		System.out.println("Las atracciones separadas por tipo son:\n ");	
		
		
		System.out.print("Aventura: ");
		System.out.println(app.atraccionesDeAventura);
		System.out.print("Degustacion: ");
		System.out.println(app.atraccionesDeDegustacion);
		System.out.print("Paisaje : ");
		System.out.println(app.atraccionesDePaisaje);
		
		
		
		System.out.println("\n Iniciamos la oferta de atracciones:  \n \n ");	

		for (int i = 0; i < todosLosUsuarios.size(); i++) {

			app.ofrecerTodo(todosLosUsuarios.get(i));

			System.out.println(todosLosUsuarios.get(i).getNombre() + " va a ir a:");
			List <Atraccion> itinerarioIndividual= new ArrayList<Atraccion>();
			itinerarioIndividual=todosLosUsuarios.get(i).getItinerario();
			for(Atraccion atraccion : itinerarioIndividual) {
				System.out.println(atraccion);
				
				app.actualizarUsuarios(todosLosUsuarios);
				app.actualizarAtracciones(todasLasAtracciones);
				todasLasAtracciones= atracciones.findAll();
				todosLosUsuarios= usuarios.findAll();
				todosLosItinerarios= itinerarios.findAll();
				
			}
			
			System.out.println(" \n \n FIN DE USUARIO \n \n");
			
			

		}
		System.out.println("FIN DE LA APP. Gracias por utilizar Tierra Media APP");
		


	}
	

}
