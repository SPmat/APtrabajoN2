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
import dao.PromocionDAOImpl;
import jdbc.ConnectionProvider;
import model.Atraccion;
import model.PromoAbsoluta;
import model.PromoAxB;
import model.PromoPorcentual;
import model.TipoDeAtraccion;

public class PromocionDAOTest {

	//NOTA: los metodos que dicen "2" y "3" refieren a que se prueba con promociones
	//de 2 y 3 atracciones, respectivamente.
	
	
//TODO pasar atracciones a lista
	
	PromocionDAOImpl factory;
	List<Atraccion> atracciones= new ArrayList<Atraccion>();
	@Before
	public void setUp() {
		factory= DAOFactory.getPromocionDAO();
		atracciones= DAOFactory.getAtraccionDAO().findAll();
	}

	@After
	public void tearDown(){
		factory=null;
		atracciones=null;
	}

	@Test
	public void contarTest() {
		assertEquals(20, factory.countAll());
	}
	
	@Test
	public void buscarPorAtracciones2() {
		Atraccion erebor= new Atraccion("Erebor", 12, 3, 32, TipoDeAtraccion.PAISAJE);
		Atraccion helm= new Atraccion("Abismo de Helm", 5, 2, 15, TipoDeAtraccion.PAISAJE);
		
		List<Atraccion> atraccionesL= new ArrayList<Atraccion>();
		atraccionesL.add(erebor);
		atraccionesL.add(helm);
		
		assertEquals((Integer)18, factory.findByAtraccionesList(atraccionesL));
	}
	
	@Test
	public void buscarPorAtracciones3() {
		Atraccion erebor= new Atraccion("Erebor", 12, 3, 32, TipoDeAtraccion.PAISAJE);
		Atraccion helm= new Atraccion("Abismo de Helm", 5, 2, 15, TipoDeAtraccion.PAISAJE);
		Atraccion harad= new Atraccion("Harad",3,1,7, TipoDeAtraccion.PAISAJE);
		
		List<Atraccion> atraccionesL= new ArrayList<Atraccion>();
		atraccionesL.add(erebor);
		atraccionesL.add(helm);
		atraccionesL.add(harad);
		
		assertEquals((Integer)20, factory.findByAtraccionesList(atraccionesL));
	}
	
	@Test
	public void buscarPorIdYAtracciones() {
		Atraccion erebor= new Atraccion("Erebor", 12, 3, 32, TipoDeAtraccion.PAISAJE);
		Atraccion helm= new Atraccion("Abismo de Helm", 5, 2, 15, TipoDeAtraccion.PAISAJE);
		Atraccion harad= new Atraccion("Harad",3,1,7, TipoDeAtraccion.PAISAJE);
		
		List<Atraccion> atraccionesL= new ArrayList<Atraccion>();
		atraccionesL.add(erebor);
		atraccionesL.add(helm);
		atraccionesL.add(harad);
		
		PromoAxB promocion= new PromoAxB(atraccionesL);
		assertEquals(promocion, factory.findById((Integer)20, atracciones));
		assertEquals(promocion, factory.findById(factory.findByAtraccionesList(atraccionesL), atracciones));
	}
	
	@Test
	public void insertarYEliminar2() {
		Atraccion erebor= new Atraccion("Erebor", 12, 3, 32, TipoDeAtraccion.PAISAJE);
		Atraccion harad= new Atraccion("Harad",3,1,7, TipoDeAtraccion.PAISAJE);
		
		List<Atraccion> atraccionesL= new ArrayList<Atraccion>();
		atraccionesL.add(erebor);
		atraccionesL.add(harad);
		
		PromoAxB promocion= new PromoAxB(atraccionesL);
		factory.insert(promocion);
		assertEquals(promocion, factory.findById(factory.findByAtraccionesList(atraccionesL),atracciones));
		
		factory.delete(promocion);
		//no se pone el findById porque espero null y al buscar el id de null tira error
		assertEquals(null, factory.findByAtraccionesList(atraccionesL));
	}
	
	@Test
	public void insertarYEliminar3() {
		Atraccion lothlorien= new Atraccion("Lothlorien", 35, 1, 30, TipoDeAtraccion.DEGUSTACION);
		Atraccion edoras= new Atraccion("Edoras", 4, 3, 25, TipoDeAtraccion.DEGUSTACION);
		Atraccion comarca= new Atraccion("La comarca", 3, 6.5, 150, TipoDeAtraccion.DEGUSTACION);
		
		List<Atraccion> atraccionesL= new ArrayList<Atraccion>();
		atraccionesL.add(lothlorien);
		atraccionesL.add(edoras);
		atraccionesL.add(comarca);
		
		PromoAbsoluta promocion= new PromoAbsoluta(atraccionesL, 50);
		factory.insert(promocion);
		assertEquals(promocion, factory.findById(factory.findByAtraccionesList(atraccionesL), atracciones));
		
		factory.delete(promocion);
		//no se pone el findById porque espero null y al buscar el id de null tira error
		assertEquals(null, factory.findByAtraccionesList(atraccionesL));
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
			
			List<Atraccion> atraccionesL= new ArrayList<Atraccion>();
			atraccionesL.add(lothlorien);
			atraccionesL.add(edoras);
			atraccionesL.add(gondor);
			
			PromoPorcentual promocion= new PromoPorcentual(atraccionesL,15);
			
			assertEquals(promocion, factory.toPromocion(resultado, atracciones));
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
				
				List<Atraccion> atraccionesL= new ArrayList<Atraccion>();
				atraccionesL.add(erebor);
				atraccionesL.add(helm);
				atraccionesL.add(harad);
				
				PromoAxB promocion= new PromoAxB(atraccionesL);
				
				assertEquals(promocion, factory.toPromocion(resultado, atracciones));
			} catch(Exception e) {
				e.printStackTrace();
			}
	}

}
