package turismo_en_tierra_media;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Importador.LectorDeArchivos;
import model.*;


public class UsuarioTest {

	List<Usuario> personas = new ArrayList<Usuario>();
	
	@Before
	public void setUp() {
		LectorDeArchivos lector = new LectorDeArchivos();
		lector.leerUsuarios(personas);
	}
	
	@After
	public void tearDown() {
		personas.clear();
	}
	
	@Test
	public void pruebaDeLecturadeTipoDeAtraccion() {
		assertEquals(TipoDeAtraccion.AVENTURA, personas.get(0).getAtraccionFavorita());
		assertEquals(TipoDeAtraccion.DEGUSTACION, personas.get(1).getAtraccionFavorita());
		assertEquals(TipoDeAtraccion.PAISAJE, personas.get(2).getAtraccionFavorita());
		assertEquals(TipoDeAtraccion.PAISAJE, personas.get(3).getAtraccionFavorita());
	}
	
	@Test
	public void usuarioToString() {
		assertEquals("Eowyn tiene 10 monedas, 8 horas libres y su tipo de atraccion favorito es aventura", personas.get(0).userInfo());
	}
	
	@Test
	public void agregarUnaAtraccionAlItinerario() {
		Atraccion atraccion1= new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		
		atraccion1.reservarLugar(personas.get(0));
		
		assertEquals(5,atraccion1.getUsosDisponibles());
		assertEquals(6,personas.get(0).getTiempoDisponible(),0);
		List<Atraccion> atraccionEquals= new ArrayList<Atraccion>();
		atraccionEquals.add(atraccion1); 
		assertEquals(atraccionEquals, personas.get(0).getItinerario());
	}
	
	@Test
	public void atraccionesRestanTiempo() {
		//TODO agregar solo las promociones/atracciones que se usan
		List<Atraccion> lasAtracciones = new ArrayList<Atraccion>();
		List<Promocion> lasPromociones = new ArrayList<Promocion>();
		LectorDeArchivos lector = new LectorDeArchivos();
		lector.leerAtracciones(lasAtracciones);
		lector.leerPromos(lasPromociones, lasAtracciones);
		personas.get(1).agregarAtraccion(lasAtracciones.get(2));	//Sam con 8 horas toma La Comarca de 6.5 horas.
		assertEquals(1.5, personas.get(1).getTiempoDisponible(),0);
		
		for(Atraccion atraccion: lasPromociones.get(18).getAtraccionesEnPromocion()) {
			personas.get(4).agregarAtraccion(atraccion);			//Boromir con 50 horas toma Minas Tirith, Abismo de Helm, Rivendel de 9.5 horas.
		}
		assertEquals(40.5, personas.get(4).getTiempoDisponible(), 0);		
	}
	
	@Test
	public void pagaCorrectamente() {
		//TODO agregar solo las promociones/atracciones que se usan
		List<Atraccion> lasAtracciones = new ArrayList<Atraccion>();
		List<Promocion> lasPromociones = new ArrayList<Promocion>();
		LectorDeArchivos lector = new LectorDeArchivos();
		lector.leerAtracciones(lasAtracciones);
		lector.leerPromos(lasPromociones, lasAtracciones);
		personas.get(1).pagar(lasAtracciones.get(2));		//Sam con 36 monedas paga La Comarca a 3 monedas.
		assertEquals(33, personas.get(1).getPresupuesto());
		
		personas.get(3).pagar(lasPromociones.get(16));		//Galadriel con 120 monedas paga Minas Tirith y Erebor a 8 monedas.
		assertEquals(112, personas.get(3).getPresupuesto());
	}
}