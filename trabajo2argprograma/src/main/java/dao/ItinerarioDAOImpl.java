package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import model.Atraccion;
import model.Itinerario;
import model.TipoDeAtraccion;
import model.Usuario;


public class ItinerarioDAOImpl implements ItinerarioDAO {
	
	UsuarioDAOImpl unUser = new UsuarioDAOImpl();
	AtraccionDAOImpl unaAtraccion= new AtraccionDAOImpl();
	
	public List<Itinerario> findAll() {
		try {
		String sql = "SELECT * FROM itinerarios";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		List<Itinerario> itinerarios = new LinkedList<Itinerario>();
		
		while (resultados.next()) {
			itinerarios.add(toItinerario(resultados));
		}

		return itinerarios;
		
		} catch(Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int countAll() {
		try {
			String sql = "SELECT count(1) AS 'total' FROM itinerarios";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("total");

			return total;
		} catch(Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	

	public int cargarItinerarioUser(Usuario usuario) {
		
		//this.encontrarIDPromo(usuario);
		
		try {
			String sql = "INSERT INTO itinerarios (id_usuario, id_atr) VALUES (?, ?)";
			
			int rows=0;
			


			for (Atraccion cadaAtraccion: usuario.getItinerario()) {
				
				Connection conn = ConnectionProvider.getConnection();

				PreparedStatement statement = conn.prepareStatement(sql);
				
				statement.setInt(1, unUser.findIDByNombre(usuario.getNombre()));
				statement.setDouble(2, unaAtraccion.findIDByNombre(cadaAtraccion.getNombre()));
				rows+= statement.executeUpdate();


				}

			return rows;
		} catch(Exception e) {
			throw new MissingDataException(e);
		}
	}
	

	public Itinerario toItinerario(ResultSet resultado) {
		try {
			return new Itinerario(unaAtraccion.findByID(resultado.getInt("id_atr")), unUser.findByID(resultado.getInt("id_usuario")));
		} catch(Exception e) {
			throw new MissingDataException(e);
		}
	}



	public void borrarItinerario() throws SQLException {
		
		String sql = "DELETE FROM itinerarios";
			
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);

			statement.executeUpdate();
	}

	
	
	
	public int delete(Itinerario t) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(Itinerario t) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(Usuario usuario, List<Atraccion> atr) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int update(Itinerario t) {
		// TODO Auto-generated method stub
		return 0;
	}




}
