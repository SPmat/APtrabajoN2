package turismo_en_tierra_media;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.DAOFactory;
import dao.ItinerarioDAO;
import dao.UsuarioDAO;
import jdbc.ConnectionProvider;
import model.TipoDeAtraccion;
import model.Usuario;

public class ItinerarioDAOTest {

ItinerarioDAO factory;
	
	@Before
	public void setup() {
		factory= DAOFactory.getItinerarioDAO();
	}
	
	@After
	public void tearDown() {
		factory= null;
	}
	
	@Test
	public void contarTodos() {
		//se ejecuta luego de insertar el usuario, por lo que cuenta 13
		assertEquals(0,factory.countAll());
	}

	
	
	

}
