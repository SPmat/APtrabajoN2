package turismo_en_tierra_media;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Importador.LectorDeArchivos;
import model.*;

public class lectorTest {

	List<Atraccion> lasAtracciones = new ArrayList<Atraccion>();
	List<Promocion> lasPromociones = new ArrayList<Promocion>();
	List<Usuario> losUsuarios = new ArrayList<Usuario>();

	@Before
	public void setUp() {
		LectorDeArchivos lector = new LectorDeArchivos();
		lector.leerAtracciones(lasAtracciones);
		lector.leerPromos(lasPromociones, lasAtracciones);
		lector.leerUsuarios(losUsuarios);
	}
	
	@After
	public void tearDown() {
		lasAtracciones.clear();
		lasPromociones.clear();
		losUsuarios.clear();
	}

	@Test
	public void cantidadDePromosTest() {

		assertNotNull(lasAtracciones);
		assertNotNull(lasPromociones);
		assertNotNull(losUsuarios);

	}

}