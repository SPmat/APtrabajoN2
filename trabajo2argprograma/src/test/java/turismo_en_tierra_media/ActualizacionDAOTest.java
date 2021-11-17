package turismo_en_tierra_media;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import app.Ofertador;
import dao.AtraccionDAO;
import dao.AtraccionDAOImpl;
import dao.DAOFactory;
import dao.ItinerarioDAO;
import dao.ItinerarioDAOImpl;
import dao.PromocionDAOImpl;
import dao.UsuarioDAOImpl;
import model.Atraccion;
import model.Itinerario;
import model.Promocion;
import model.Usuario;

public class ActualizacionDAOTest {

	AtraccionDAO factory;
	ItinerarioDAO facItinerario;

	List<Atraccion> todasLasAtracciones = new ArrayList<Atraccion>();
	List<Usuario> todosLosUsuarios = new ArrayList<Usuario>();
	List<Promocion> todasLasPromociones = new ArrayList<Promocion>();
	List<Itinerario> todosLosItinerarios = new ArrayList<Itinerario>();

	PromocionDAOImpl promociones = new PromocionDAOImpl();
	UsuarioDAOImpl usuarios = new UsuarioDAOImpl();
	AtraccionDAOImpl atracciones = new AtraccionDAOImpl();
	ItinerarioDAOImpl itinerarios = new ItinerarioDAOImpl();

	
	@Before
	public void setUp(){
		facItinerario= DAOFactory.getItinerarioDAO();
		factory= DAOFactory.getAtraccionDAO();
		
		todasLasAtracciones= atracciones.findAll();
		todosLosUsuarios= usuarios.findAll();
		todasLasPromociones= promociones.findAll(todasLasAtracciones);
		todosLosItinerarios= itinerarios.findAll();
		

		
	}

	@After
	public void tearDown() {
		factory=null;
	}
	
	
	
	
	
	
	@Test
	public void convervaPosicionTest() throws SQLException {

		Ofertador app = new Ofertador(todasLasAtracciones, todasLasPromociones);

		app.separarEnListas();	
		
		
		app.separarItinerario(todosLosUsuarios, todosLosItinerarios);	
		
		String nombre=todasLasAtracciones.get(0).getNombre();
			
			
			
			todosLosUsuarios.get(0).agregarAtraccion(todasLasAtracciones.get(0));
		
			app.actualizarUsuarios(todosLosUsuarios);
			app.actualizarAtracciones(todasLasAtracciones);
			
			assertEquals(todasLasAtracciones.get(0).getNombre(), nombre);
			
	
	
}
	
	
	@Test
	public void actualizaCantidadUsosTest() throws SQLException {

		Ofertador app = new Ofertador(todasLasAtracciones, todasLasPromociones);

		app.separarEnListas();	
		
		
		app.separarItinerario(todosLosUsuarios, todosLosItinerarios);	
		
		
		int usos=todasLasAtracciones.get(0).getUsosDisponibles();
			
			
			
			todosLosUsuarios.get(0).agregarAtraccion(todasLasAtracciones.get(0));
		
			
			
			
			app.actualizarUsuarios(todosLosUsuarios);
			app.actualizarAtracciones(todasLasAtracciones);
			
			todasLasAtracciones= atracciones.findAll();
			todosLosUsuarios= usuarios.findAll();
			todosLosItinerarios= itinerarios.findAll();
			
			assertNotEquals(todasLasAtracciones.get(0).getUsosDisponibles(), usos);
	
	
}
	
	
	



}
