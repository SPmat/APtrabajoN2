package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import model.Atraccion;
import model.TipoDeAtraccion;

public class AtraccionDAOImpl implements AtraccionDAO{
	
	public List<Atraccion> findAll() {
		try {
		String sql = "SELECT * FROM atracciones";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		List<Atraccion> atracciones = new LinkedList<Atraccion>();
		while (resultados.next()) {
			atracciones.add(toAtraccion(resultados));
		}
		return atracciones;
		} catch(Exception e) {
			throw new MissingDataException(e);
		} 
	}
	
	public int countAll() {
		try {
			String sql = "SELECT count(1) AS 'total' FROM atracciones";
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
	
	public int insert(Atraccion atraccion) {
		try {
			String sql = "INSERT INTO atracciones (nombre,valor,tiempo,usos_disponibles,ID_tipo_atraccion) VALUES (?, ?, ?, ?, (SELECT ID FROM tipos_atraccion WHERE tipo_de_atraccion LIKE ?))";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, atraccion.getNombre());
			statement.setInt(2, atraccion.getValor());
			statement.setDouble(3, atraccion.getTiempoDeUso());
			statement.setInt(4, atraccion.getUsosDisponibles());
			statement.setString(5, atraccion.getNombreTipo());
			int rows = statement.executeUpdate();
			
			return rows;
		} catch(Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	public int update(Atraccion atraccion) {
		try {
			String sql = "UPDATE atracciones SET usos_disponibles = ? WHERE nombre = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, atraccion.getUsosDisponibles());
			statement.setString(2, atraccion.getNombre());
			int rows = statement.executeUpdate();
			
			return rows;
		} catch(Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	public int delete(Atraccion atraccion) {
		try {
			String sql = "DELETE FROM atracciones WHERE nombre = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, atraccion.getNombre());
			int rows = statement.executeUpdate();
			
			return rows;
		} catch(Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	public Atraccion findByNombre(String nombre) {
		try {
			String sql = "SELECT * FROM atracciones WHERE nombre = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombre);
			ResultSet resultados = statement.executeQuery();

			Atraccion atraccion = null;

			if (resultados.next()) {
				atraccion = toAtraccion(resultados);
			}
			
			return atraccion;
		} catch(Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	
	public Atraccion findByID(int ID) {
		try {
			String sql = "SELECT * FROM atracciones WHERE ID  = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, ID);
			ResultSet resultados = statement.executeQuery();

			Atraccion atraccion = null;

			if (resultados.next()) {
				atraccion = toAtraccion(resultados);
			}
			
			return atraccion;
		} catch(Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	
	public int findIDByNombre(String nombre) {
		try {
			String sql = "SELECT ID FROM atracciones WHERE nombre = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombre);
			ResultSet resultados = statement.executeQuery();



			return resultados.getInt("ID");
		} catch(Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	public Atraccion toAtraccion(ResultSet resultado) {
		try {
			//primero obtengo el valor de tipo de atraccion para pasarlo a String, ya que en la tabla es una FK
			String sql = "SELECT tipo_de_atraccion FROM tipos_atraccion ta JOIN atracciones a ON ta.id=a.ID_tipo_atraccion WHERE a.id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, resultado.getInt("ID"));
			ResultSet resultadoDos = statement.executeQuery();
			//lo guardo en variable
			String tipoAtraccion= resultadoDos.getString("tipo_de_atraccion");
			
			//retorno el objeto
			return new Atraccion(resultado.getString("nombre"), resultado.getInt("valor"), resultado.getDouble("tiempo"), 
					resultado.getInt("usos_disponibles"), TipoDeAtraccion.valueOf(tipoAtraccion));
			
		} catch(Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	public void restaurar() {
		try {

			Connection conn = ConnectionProvider.getConnection();
			int[] cupos= {
					6, 		//moria
					25, 	//minas tirith
					150, 	//la comarca
					4,		//mordor
					15,		//abismo de helm
					30, 	//lothlorien
					32,		//erebor
					12, 	//bosque negro
					20,		//rivendel
					10, 	//isengard
					25,		//edoras
					3,		//fangorn
					10,		//gondor
					7,		//harad
					30		//palargir
			};
			for(int i=0; i<cupos.length; i++) {
				String sql = "UPDATE atracciones SET usos_disponibles= ? WHERE id= ? ";
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, cupos[i]);				
				statement.setInt(2, i+1);				//el id se mueve a 1 numero mas que el recorrido del for ya que empieza en 1
				statement.executeUpdate();
			}
			
		} catch(Exception e) {
			throw new MissingDataException(e);
		}
	}
}
