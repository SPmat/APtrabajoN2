package dao;

import java.sql.ResultSet;
import java.util.List;

import model.Atraccion;

public interface AtraccionDAO extends GenericDAO<Atraccion>{
	
	public Atraccion findByNombre(String nombre);
	public Atraccion toAtraccion(ResultSet resultado);
	public List<Atraccion> findAll();
}
