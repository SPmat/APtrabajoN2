package turismo_en_tierra_media;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.AtraccionDAO;
import dao.DAOFactory;
import jdbc.ConnectionProvider;
import model.Atraccion;
import model.TipoDeAtraccion;

public class AtraccionDAOTest {

	AtraccionDAO factory;
	
	@Before
	public void setUp(){
		factory= DAOFactory.getAtraccionDAO();
	}

	@After
	public void tearDown() {
		factory=null;
	}

	@Test
	public void contar() {
		assertEquals(15, factory.countAll());
	}
	
	@Test
	public void buscarPorNombre() {
		Atraccion erebor= new Atraccion("Erebor", 12, 3, 32, TipoDeAtraccion.PAISAJE);
		assertEquals(erebor, factory.findByNombre("Erebor"));
	}
	
	@Test
	public void insertarYEliminar() {
		Atraccion atraccion= new Atraccion("Mi casa", 2, 8, 5, TipoDeAtraccion.PAISAJE);
		factory.insert(atraccion);
		assertEquals(atraccion, factory.findByNombre("Mi casa"));
		factory.delete(factory.findByNombre("Mi casa"));
		assertEquals(null, factory.findByNombre("Mi casa"));
	}
	
	@Test 
	public void actualizarYDeshacer() {
		Atraccion erebor= new Atraccion("Erebor", 12, 3, 22, TipoDeAtraccion.PAISAJE);
		factory.update(erebor);
		assertEquals(erebor, factory.findByNombre("Erebor"));
		Atraccion ereborDeshacer= new Atraccion("Erebor", 12, 3, 32, TipoDeAtraccion.PAISAJE);
		factory.update(ereborDeshacer);
		assertEquals(ereborDeshacer, factory.findByNombre("Erebor"));
	}
	
	@Test
	public void resultadoAAtraccion() {
		try {
			String sql = "SELECT * FROM atracciones WHERE nombre='Erebor'";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();
			
			Atraccion erebor= new Atraccion("Erebor", 12, 3, 32, TipoDeAtraccion.PAISAJE);
			
			assertEquals(erebor, factory.toAtraccion(resultado));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
