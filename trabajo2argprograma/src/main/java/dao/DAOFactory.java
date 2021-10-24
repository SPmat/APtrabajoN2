package dao;

public class DAOFactory {
	
	public static AtraccionDAO getAtraccionDAO() {
		return new AtraccionDAOImpl();
	}
	
	public static UsuarioDAO getUsuarioDAO() {
		return new UsuarioDAOImpl();
	}
	
	public static PromocionDAO getPromocionDAO() {
		return new PromocionDAOImpl();
	}
	
	public static ItinerarioDAO getItinerarioDAO() {
		return new ItinerarioDAOImpl();
	}
}
