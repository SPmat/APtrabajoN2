package turismo_en_tierra_media;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.DAOFactory;
import dao.UsuarioDAO;
import jdbc.ConnectionProvider;
import model.TipoDeAtraccion;
import model.Usuario;

public class UsuarioDAOTest {

	UsuarioDAO factory;
	
	@Before
	public void setup() {
		factory= DAOFactory.getUsuarioDAO();
	}
	
	@After
	public void tearDown() {
		factory= null;
	}
	
	@Test
	public void contarTodos() {
		//se ejecuta luego de insertar el usuario, por lo que cuenta 13
		assertEquals(12,factory.countAll());
	}

	@Test
	public void buscarPorNombre(){
		Usuario eowyn= new Usuario("Eowyn", TipoDeAtraccion.AVENTURA, 10, 8f);
		assertEquals(eowyn, factory.findByNombre("Eowyn"));
	}
	
	@Test
	public void insertarYBorrarUsuario() {
		Usuario usuario= new Usuario("Montoto", TipoDeAtraccion.AVENTURA, 32, 8.5f);
		factory.insert(usuario);
		assertEquals(usuario, factory.findByNombre("Montoto"));
		DAOFactory.getUsuarioDAO().delete(factory.findByNombre("Montoto"));
		assertEquals(null, factory.findByNombre("Montoto"));
	}
	
	@Test
	public void actualizarYDeshacer() {
		Usuario frodo= new Usuario("Frodo", TipoDeAtraccion.PAISAJE, 10, 13.5f);
		factory.update(frodo);
		assertEquals(frodo, factory.findByNombre("Frodo"));
		
		Usuario frodoDeshacer= new Usuario("Frodo", TipoDeAtraccion.PAISAJE, 7, 7f);
		factory.update(frodoDeshacer);
		assertEquals(frodoDeshacer, factory.findByNombre("Frodo"));
		
	}
	
	@Test
	public void conversionAUsuario() {
		try {
			String sql = "SELECT * FROM usuarios WHERE nombre='Eowyn'";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();
			
			Usuario eowyn= new Usuario("Eowyn", TipoDeAtraccion.AVENTURA, 10, 8f);
			
			assertEquals(eowyn, factory.toUsuario(resultado));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
