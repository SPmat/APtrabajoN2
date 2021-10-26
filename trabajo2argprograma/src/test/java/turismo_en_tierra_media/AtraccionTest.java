package turismo_en_tierra_media;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.DAOFactory;
import model.Atraccion;
import model.TipoDeAtraccion;
import model.Usuario;

public class AtraccionTest {
	List<Usuario> personas = new ArrayList<Usuario>();
	
	@Before
	public void setUp() throws Exception {
		personas= DAOFactory.getUsuarioDAO().findAll();
	}

	@After
	public void tearDown() throws Exception {
		personas.clear();
	}

	@Test
	public void cuposTest() {
		Atraccion fangorn= new Atraccion("Nombre", 15, 7f, 3, TipoDeAtraccion.AVENTURA);
		fangorn.reservarLugar(personas.get(0));
		fangorn.reservarLugar(personas.get(1));
		fangorn.reservarLugar(personas.get(2));
		
		assertEquals(0, fangorn.getUsosDisponibles());
		assertEquals(fangorn, personas.get(0).getItinerario().get(0));
		
	}
	
	@Test
	public void agregaAlItinerario() {
		Atraccion fangorn= new Atraccion("Nombre", 15, 7f, 3, TipoDeAtraccion.AVENTURA);
		fangorn.reservarLugar(personas.get(0));
		assertEquals(fangorn, personas.get(0).getItinerario().get(0));
	}
	

}
