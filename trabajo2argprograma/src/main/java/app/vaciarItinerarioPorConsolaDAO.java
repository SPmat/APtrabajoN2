package app;

import dao.ItinerarioDAOImpl;

public class vaciarItinerarioPorConsolaDAO {
	
	public static void main(String[] args) {

		ItinerarioDAOImpl itinerarios = new ItinerarioDAOImpl();
		
		itinerarios.restaurar();
	}

}
