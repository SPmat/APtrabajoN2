package turismo_en_tierra_media;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Importador.LectorDeArchivos;
import model.*;

public class PromocionesTest {

	List<Atraccion> lasAtracciones = new ArrayList<Atraccion>();
	List<Promocion> lasPromociones = new ArrayList<Promocion>();

	@Before
	public void setUp() {
		LectorDeArchivos lector = new LectorDeArchivos();
		lector.leerAtracciones(lasAtracciones);
		lector.leerPromos(lasPromociones, lasAtracciones);
	}
	
	@After
	public void tearDown() {
		lasAtracciones.clear();
		lasPromociones.clear();
	}

	@Test
	public void cantidadDePromosTest() {
		
		//Vemos que se cargaron la cantidad correcta de promociones

		assertEquals(20, lasPromociones.size());
	}

	@Test
	public void tiposDePromosTest() {
		
		//Vemos los tipos de atraccion de cada una de las promociones

		assertEquals(TipoDePromo.ABSOLUTA, lasPromociones.get(0).getTipo());
		assertEquals(TipoDePromo.PORCENTUAL, lasPromociones.get(1).getTipo());
		assertEquals(TipoDePromo.AxB, lasPromociones.get(2).getTipo());
		assertEquals(TipoDePromo.ABSOLUTA, lasPromociones.get(3).getTipo());
	}
	
	
	@Test
	public void cantidadDeAtraccionesEnCadaPromoTest() {
		
		//Vemos cuantas atracciones entran en cada promocion

		assertEquals(2, lasPromociones.get(0).getAtraccionesEnPromocion().size());
		assertEquals(3, lasPromociones.get(1).getAtraccionesEnPromocion().size());
		assertEquals(2, lasPromociones.get(2).getAtraccionesEnPromocion().size());
		assertEquals(3, lasPromociones.get(3).getAtraccionesEnPromocion().size());
	}
	
	@Test
	public void nombresDeAtraccionesTest() {
		
		//Vemos los nombres de las atracciones

		assertEquals("Bosque Negro", lasPromociones.get(0).getAtraccionesEnPromocion().get(0).getNombre());
		assertEquals("Moria", lasPromociones.get(0).getAtraccionesEnPromocion().get(1).getNombre());
	}
	
	@Test
	public void valoresDeLasPromosTest() {
		
		//Vemos los nombres de las atracciones

		assertEquals(36, lasPromociones.get(3).getValorPromo());	// ABSOLUTA,36,Bosque Negro,Moria,Mordor
		assertEquals(5, lasPromociones.get(2).getValorPromo());		// AxB,0,Minas Tirith,Erebor
		assertEquals(18, lasPromociones.get(1).getValorPromo());	// PORCENTUAL,20,Minas Tirith,Abismo de Helm,Erebor 
		assertEquals(10, lasPromociones.get(0).getValorPromo());	// ABSOLUTA,50,Bosque Negro,Moria
	}
	
	
}