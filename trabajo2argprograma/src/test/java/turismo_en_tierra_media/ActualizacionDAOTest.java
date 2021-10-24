package turismo_en_tierra_media;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import app.Aplicacion;
import dao.AtraccionDAO;
import dao.AtraccionDAOImpl;
import dao.DAOFactory;
import dao.ItinerarioDAO;
import dao.ItinerarioDAOImpl;
import dao.PromocionDAOImpl;
import dao.UsuarioDAOImpl;
import jdbc.ConnectionProvider;
import model.Atraccion;
import model.Itinerario;
import model.Promocion;
import model.TipoDeAtraccion;
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
		todasLasPromociones= promociones.findAll();
		todosLosItinerarios= itinerarios.findAll();
		

		
	}

	@After
	public void tearDown() {
		factory=null;
	}
	
	
	
	
	
	
	@Test
	public void convervaPosicionTest() throws SQLException {

		Aplicacion app = new Aplicacion(todasLasAtracciones, todasLasPromociones);

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

		Aplicacion app = new Aplicacion(todasLasAtracciones, todasLasPromociones);

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
