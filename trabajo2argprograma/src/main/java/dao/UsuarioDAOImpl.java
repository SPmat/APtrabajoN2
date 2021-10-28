package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import model.TipoDeAtraccion;
import model.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO{

	public List<Usuario> findAll() {
		try {
		String sql = "SELECT * FROM usuarios";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		List<Usuario> usuarios = new LinkedList<Usuario>();
		while (resultados.next()) {
			usuarios.add(toUsuario(resultados));
		}

		return usuarios;
		} catch(Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int countAll() {
		try {
			String sql = "SELECT count(1) AS 'total' FROM usuarios";
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

	public int insert(Usuario usuario) {
		try {
			String sql = "INSERT INTO usuarios (nombre, monedas, tiempo_libre, ID_tipo_favorito) VALUES (?, ?, ?, (SELECT ID FROM tipos_atraccion WHERE tipo_de_atraccion LIKE ?))";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, usuario.getNombre());
			statement.setInt(2, usuario.getPresupuesto());
			statement.setDouble(3, usuario.getTiempoDisponible());
			statement.setString(4, usuario.getAtraccionFavorita().getNombreDeTipo());
			int rows = statement.executeUpdate();
			
			return rows;
		} catch(Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int update(Usuario usuario) {
		try {
			String sql = "UPDATE usuarios SET monedas = ?, tiempo_libre = ? WHERE nombre = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, usuario.getPresupuesto());
			statement.setDouble(2, usuario.getTiempoDisponible());
			statement.setString(3, usuario.getNombre());
			int rows = statement.executeUpdate();

			
			return rows;
		} catch(Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int delete(Usuario usuario) {
		try {
			String sql = "DELETE FROM usuarios WHERE nombre = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, usuario.getNombre());
			int rows = statement.executeUpdate();

			
			return rows;
		} catch(Exception e) {
			throw new MissingDataException(e);
		}
	}

	public Usuario findByNombre(String nombre) {
		try {
			String sql = "SELECT * FROM usuarios WHERE nombre = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombre);
			ResultSet resultados = statement.executeQuery();

			Usuario usuario = null;

			if (resultados.next()) {
				usuario = toUsuario(resultados);
			}
			return usuario;
		} catch(Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	
	public Usuario findByID(int ID) {
		try {
			String sql = "SELECT * FROM usuarios WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, ID);
			ResultSet resultados = statement.executeQuery();

			Usuario usuario = null;

			if (resultados.next()) {
				usuario = toUsuario(resultados);
			}
			
			return usuario;
		} catch(Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	
	public int findIDByNombre(String nombre) {
		try {
			String sql = "SELECT ID FROM usuarios WHERE nombre = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombre);
			ResultSet resultados = statement.executeQuery();

			

			return resultados.getInt("ID");
		} catch(Exception e) {
			throw new MissingDataException(e);
		}
	}

	public Usuario toUsuario(ResultSet resultado) {
		try {
			//primero obtengo el valor de tipo de atraccion para pasarlo a String, ya que en la tabla es una FK
			String sql = "SELECT tipo_de_atraccion FROM tipos_atraccion a JOIN usuarios u ON a.id=u.ID_tipo_favorito WHERE u.id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, resultado.getInt("ID"));
			ResultSet resultadoDos = statement.executeQuery();
			//lo guardo en variable
			String tipoAtraccion= resultadoDos.getString("tipo_de_atraccion");
			//retorno el objeto
			return new Usuario(resultado.getString("nombre"), TipoDeAtraccion.valueOf(tipoAtraccion),
					resultado.getInt("monedas"), resultado.getFloat("tiempo_libre"));
		} catch(Exception e) {
			throw new MissingDataException(e);
		}
	}
	
}
