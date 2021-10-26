package dao;

import java.sql.ResultSet;
import java.util.List;

import model.Atraccion;
import model.Promocion;

public interface PromocionDAO extends GenericDAO<Promocion>{
	
	public Integer findByAtraccionesList(List<Atraccion> atracciones); 	//no se si servira
	public Promocion findById(Integer id);
	public Promocion toPromocion(ResultSet resultados, List<Atraccion> atraccionesImportadas);
}
