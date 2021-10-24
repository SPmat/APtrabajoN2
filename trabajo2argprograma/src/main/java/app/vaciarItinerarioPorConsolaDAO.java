package app;

import java.sql.SQLException;

import dao.ItinerarioDAOImpl;

public class vaciarItinerarioPorConsolaDAO {
	
	public static void main(String[] args) throws SQLException {

		ItinerarioDAOImpl itinerarios = new ItinerarioDAOImpl();
		
		itinerarios.borrarItinerario();
	}

}
