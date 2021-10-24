package turismo_en_tierra_media;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.DAOFactory;
import dao.PromocionDAO;
import jdbc.ConnectionProvider;
import model.*;

public class PromocionDAOTest {

	//NOTA: los metodos que dicen "2" y "3" refieren a que se prueba con promociones
	//de 2 y 3 atracciones, respectivamente.
	
	PromocionDAO factory;
	@Before
	public void setUp() {
		factory= DAOFactory.getPromocionDAO();
	}

	@After
	public void tearDown(){
		factory=null;
	}

	@Test
	public void contarTest() {
		assertEquals(20, factory.countAll());
	}
	
	@Test
	public void buscarPorAtracciones2() {
		Atraccion erebor= new Atraccion("Erebor", 12, 3, 32, TipoDeAtraccion.PAISAJE);
		Atraccion helm= new Atraccion("Abismo de Helm", 5, 2, 15, TipoDeAtraccion.PAISAJE);
		
		List<Atraccion> atracciones= new ArrayList<Atraccion>();
		atracciones.add(erebor);
		atracciones.add(helm);
		
		assertEquals((Integer)18, factory.findByAtraccionesList(atracciones));
	}
	
	@Test
	public void buscarPorAtracciones3() {
		Atraccion erebor= new Atraccion("Erebor", 12, 3, 32, TipoDeAtraccion.PAISAJE);
		Atraccion helm= new Atraccion("Abismo de Helm", 5, 2, 15, TipoDeAtraccion.PAISAJE);
		Atraccion harad= new Atraccion("Harad",3,1,7, TipoDeAtraccion.PAISAJE);
		
		List<Atraccion> atracciones= new ArrayList<Atraccion>();
		atracciones.add(erebor);
		atracciones.add(helm);
		atracciones.add(harad);
		
		assertEquals((Integer)20, factory.findByAtraccionesList(atracciones));
	}
	
	@Test
	public void buscarPorIdYAtracciones() {
		Atraccion erebor= new Atraccion("Erebor", 12, 3, 32, TipoDeAtraccion.PAISAJE);
		Atraccion helm= new Atraccion("Abismo de Helm", 5, 2, 15, TipoDeAtraccion.PAISAJE);
		Atraccion harad= new Atraccion("Harad",3,1,7, TipoDeAtraccion.PAISAJE);
		
		List<Atraccion> atracciones= new ArrayList<Atraccion>();
		atracciones.add(erebor);
		atracciones.add(helm);
		atracciones.add(harad);
		
		PromoAxB promocion= new PromoAxB(atracciones);
		assertEquals(promocion, factory.findById(20));
		assertEquals(promocion, factory.findById(factory.findByAtraccionesList(atracciones)));
	}
	
	@Test
	public void insertarYEliminar2() {
		Atraccion erebor= new Atraccion("Erebor", 12, 3, 32, TipoDeAtraccion.PAISAJE);
		Atraccion harad= new Atraccion("Harad",3,1,7, TipoDeAtraccion.PAISAJE);
		
		List<Atraccion> atracciones= new ArrayList<Atraccion>();
		atracciones.add(erebor);
		atracciones.add(harad);
		
		PromoAxB promocion= new PromoAxB(atracciones);
		factory.insert(promocion);
		assertEquals(promocion, factory.findById(factory.findByAtraccionesList(atracciones)));
		
		factory.delete(promocion);
		//no se pone el findById porque espero null y al buscar el id de null tira error
		assertEquals(null, factory.findByAtraccionesList(atracciones));
	}
	
	@Test
	public void insertarYEliminar3() {
		Atraccion lothlorien= new Atraccion("Lothlorien", 35, 1, 30, TipoDeAtraccion.DEGUSTACION);
		Atraccion edoras= new Atraccion("Edoras", 4, 3, 25, TipoDeAtraccion.DEGUSTACION);
		Atraccion comarca= new Atraccion("La comarca", 3, 6.5, 150, TipoDeAtraccion.DEGUSTACION);
		
		List<Atraccion> atracciones= new ArrayList<Atraccion>();
		atracciones.add(lothlorien);
		atracciones.add(edoras);
		atracciones.add(comarca);
		
		PromoAbsoluta promocion= new PromoAbsoluta(atracciones, 50);
		factory.insert(promocion);
		assertEquals(promocion, factory.findById(factory.findByAtraccionesList(atracciones)));
		
		factory.delete(promocion);
		//no se pone el findById porque espero null y al buscar el id de null tira error
		assertEquals(null, factory.findByAtraccionesList(atracciones));
	}
	
	@Test
	public void rsAPromocion2() {
		try {
			String sql = "SELECT * FROM promociones WHERE id=11";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();
			
			Atraccion lothlorien= new Atraccion("Lothlorien", 35, 1, 30, TipoDeAtraccion.DEGUSTACION);
			Atraccion edoras= new Atraccion("Edoras", 4, 3, 25, TipoDeAtraccion.DEGUSTACION);
			Atraccion gondor= new Atraccion("Gondor", 20, 3, 10, TipoDeAtraccion.DEGUSTACION);
			
			List<Atraccion> atracciones= new ArrayList<Atraccion>();
			atracciones.add(lothlorien);
			atracciones.add(edoras);
			atracciones.add(gondor);
			
			PromoPorcentual promocion= new PromoPorcentual(atracciones,15);
			
			assertEquals(promocion, factory.toPromocion(resultado));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void rsAPromocion3() {
			try {
				String sql = "SELECT * FROM promociones WHERE id=20";
				Connection conn = ConnectionProvider.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet resultado = statement.executeQuery();
				
				Atraccion erebor= new Atraccion("Erebor", 12, 3, 32, TipoDeAtraccion.PAISAJE);
				Atraccion helm= new Atraccion("Abismo de Helm", 5, 2, 15, TipoDeAtraccion.PAISAJE);
				Atraccion harad= new Atraccion("Harad",3,1,7, TipoDeAtraccion.PAISAJE);
				
				List<Atraccion> atracciones= new ArrayList<Atraccion>();
				atracciones.add(erebor);
				atracciones.add(helm);
				atracciones.add(harad);
				
				PromoAxB promocion= new PromoAxB(atracciones);
				
				assertEquals(promocion, factory.toPromocion(resultado));
			} catch(Exception e) {
				e.printStackTrace();
			}
	}

}
