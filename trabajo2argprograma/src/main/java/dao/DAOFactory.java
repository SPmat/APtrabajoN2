package dao;

public class DAOFactory {
	
	public static AtraccionDAOImpl getAtraccionDAO() {
		return new AtraccionDAOImpl();
	}
	
	public static UsuarioDAOImpl getUsuarioDAO() {
		return new UsuarioDAOImpl();
	}
	
	public static PromocionDAOImpl getPromocionDAO() {
		return new PromocionDAOImpl();
	}
	
	public static ItinerarioDAOImpl getItinerarioDAO() {
		return new ItinerarioDAOImpl();
	}
}
