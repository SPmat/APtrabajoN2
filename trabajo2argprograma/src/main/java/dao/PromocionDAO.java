package dao;

import java.sql.ResultSet;
import java.util.List;

import model.Atraccion;
import model.Promocion;

public interface PromocionDAO{
	
	//debe tener las atracciones importadas porque el toPromocion lo tiene
	public List<Promocion> findAll(List<Atraccion> atraccionesImportadas);
	public int countAll();
	public int insert(Promocion promocion);
	public void updateAtracciones(Promocion promocion);
	public Integer findByAtraccionesList(List<Atraccion> atracciones); 	//usado en los test
	public Promocion findById(Integer id, List<Atraccion> atraccionesImportadas);
	public int delete(Promocion promocion);
	//el siguiente metodo requiere de una lista de atracciones para tratar con los mismos objetos
	//en lugar de crear otros
	public Promocion toPromocion(ResultSet resultados, List<Atraccion> atraccionesImportadas);
}
