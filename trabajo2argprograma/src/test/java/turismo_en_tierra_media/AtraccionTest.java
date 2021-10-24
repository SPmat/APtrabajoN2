package turismo_en_tierra_media;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Importador.LectorDeArchivos;
import model.Atraccion;
import model.Usuario;

public class AtraccionTest {
	List<Usuario> personas = new ArrayList<Usuario>();
	List<Atraccion> lasAtracciones = new ArrayList<Atraccion>();
	
	@Before
	public void setUp() throws Exception {
		LectorDeArchivos lector = new LectorDeArchivos();
		lector.leerUsuarios(personas);
		lector.leerAtracciones(lasAtracciones);
	}

	@After
	public void tearDown() throws Exception {
		personas.clear();
		lasAtracciones.clear();
	}

	@Test
	public void cuposTest() {
		Atraccion fangorn= lasAtracciones.get(11);			//atraccion con 3 cupos
		fangorn.reservarLugar(personas.get(0));
		fangorn.reservarLugar(personas.get(1));
		fangorn.reservarLugar(personas.get(2));
		
		assertEquals(0, fangorn.getUsosDisponibles());
		assertEquals(fangorn, personas.get(0).getItinerario().get(0));
		
	}
	
	@Test
	public void agregaAlItinerario() {
		Atraccion fangorn= lasAtracciones.get(11);
		fangorn.reservarLugar(personas.get(0));
		assertEquals(fangorn, personas.get(0).getItinerario().get(0));
	}
	

}
