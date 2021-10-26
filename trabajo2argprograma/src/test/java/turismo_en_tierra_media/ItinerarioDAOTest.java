package turismo_en_tierra_media;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.DAOFactory;
import dao.ItinerarioDAO;

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
