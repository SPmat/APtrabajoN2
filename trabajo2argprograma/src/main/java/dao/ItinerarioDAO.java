package dao;

import java.sql.ResultSet;
import java.util.List;

import model.Atraccion;
import model.Itinerario;
import model.Usuario;

public interface  ItinerarioDAO{
	
	public List<Itinerario> findAll();
	public int countAll();
	public int cargarAtraccion(Atraccion atraccion, Usuario usuario);
	public Itinerario toItinerario(ResultSet resultado);

}
