package dao;

import java.sql.ResultSet;
import java.util.List;

import model.Atraccion;
import model.Itinerario;
import model.Usuario;

public interface  ItinerarioDAO extends GenericDAO<Itinerario>{
	
	public List<Itinerario> findAll();
	public int countAll();
	public int insert(Usuario usuario, List<Atraccion> atr);
	public Itinerario toItinerario(ResultSet resultado);

}
