package dao;

import java.sql.ResultSet;

import model.Atraccion;
import model.Itinerario;
import model.Usuario;

public interface  ItinerarioDAO extends GenericDAO<Itinerario>{
	
	public int cargarAtraccion(Atraccion atraccion, Usuario usuario);
	public Itinerario toItinerario(ResultSet resultado);

}
