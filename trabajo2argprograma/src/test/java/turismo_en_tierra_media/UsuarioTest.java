package turismo_en_tierra_media;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.DAOFactory;
import model.Atraccion;
import model.PromoAbsoluta;
import model.PromoAxB;
import model.TipoDeAtraccion;
import model.Usuario;


public class UsuarioTest {

	List<Usuario> personas = new ArrayList<Usuario>();
	
	@Before
	public void setUp() {
		personas= DAOFactory.getUsuarioDAO().findAll();
	}
	
	@After
	public void tearDown() {
		personas.clear();
	}
	
	@Test
	public void pruebaDeLecturadeTipoDeAtraccion() {
		assertEquals(TipoDeAtraccion.DEGUSTACION, personas.get(0).getAtraccionFavorita());
		assertEquals(TipoDeAtraccion.AVENTURA, personas.get(1).getAtraccionFavorita());
		assertEquals(TipoDeAtraccion.PAISAJE, personas.get(2).getAtraccionFavorita());
		assertEquals(TipoDeAtraccion.PAISAJE, personas.get(3).getAtraccionFavorita());
	}
	
	@Test
	public void usuarioToString() {
		assertEquals("Sam tiene 36 monedas, 8 horas libres y su tipo de atraccion favorito es degustacion", personas.get(0).userInfo());
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
		Atraccion atraccion1=new Atraccion("La Comarca", 3, 6.5, 150, TipoDeAtraccion.DEGUSTACION);
		personas.get(0).agregarAtraccion(atraccion1);	//Sam con 8 horas toma La Comarca de 6.5 horas.
		assertEquals(1.5, personas.get(0).getTiempoDisponible(),0);
		
		Atraccion atraccion2= new Atraccion("Minas Tirith", 5, 2.5, 25, TipoDeAtraccion.PAISAJE);
		Atraccion atraccion3= new Atraccion("Abismo de Helm", 5, 2f, 15, TipoDeAtraccion.PAISAJE);
		Atraccion atraccion4= new Atraccion("Rivendel", 10, 5f, 20, TipoDeAtraccion.PAISAJE);
		
		List<Atraccion> atracciones= new ArrayList<Atraccion>();
		atracciones.add(atraccion2);
		atracciones.add(atraccion3);
		atracciones.add(atraccion4);
		
		PromoAxB promocion= new PromoAxB(atracciones);
		for(Atraccion atraccion: promocion.getAtraccionesEnPromocion()) {
			personas.get(4).agregarAtraccion(atraccion);			//Boromir con 50 horas toma Minas Tirith, Abismo de Helm, Rivendel de 9.5 horas.
		}
		assertEquals(40.5, personas.get(4).getTiempoDisponible(), 0);		
	}
	
	@Test
	public void pagaCorrectamente() {
		Atraccion atraccion1= new Atraccion("La Comarca", 3, 6.5, 150, TipoDeAtraccion.DEGUSTACION);
		personas.get(0).pagar(atraccion1);		//Sam con 36 monedas paga La Comarca a 3 monedas.
		assertEquals(33, personas.get(0).getPresupuesto());
		
		Atraccion atraccion2= new Atraccion("Minas Tirith", 5, 2.5, 25, TipoDeAtraccion.PAISAJE);
		Atraccion atraccion3= new Atraccion("Erebor", 12, 3, 32, TipoDeAtraccion.PAISAJE);
		
		List<Atraccion> atraccionesPromo= new ArrayList<Atraccion>();
		atraccionesPromo.add(atraccion2);
		atraccionesPromo.add(atraccion3);
		
		PromoAbsoluta promocion= new PromoAbsoluta(atraccionesPromo, 8);
		
		personas.get(3).pagar(promocion);		//Galadriel con 120 monedas paga Minas Tirith y Erebor a 8 monedas.
		assertEquals(112, personas.get(3).getPresupuesto());
	}
}