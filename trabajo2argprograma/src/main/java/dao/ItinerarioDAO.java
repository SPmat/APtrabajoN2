package dao;

import java.sql.ResultSet;
import model.Itinerario;
import model.Usuario;

public interface  ItinerarioDAO extends GenericDAO<Itinerario>{
	
	public int cargarItinerarioUser(Usuario usuario);
	public Itinerario toItinerario(ResultSet resultado);

}
