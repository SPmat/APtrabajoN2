package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import model.*;

public class PromocionDAOImpl implements PromocionDAO{

	public List<Promocion> findAll() {
		try {
		String sql = "SELECT * FROM promociones";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		List<Promocion> promociones = new LinkedList<Promocion>();
		while (resultados.next()) {
			promociones.add(toPromocion(resultados));
		}

		return promociones;
		} catch(Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	public int countAll() {
		try {
			String sql = "SELECT count(1) AS 'total' FROM promociones";
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
	
	public int insert(Promocion promocion) {
		try {
			String sql;
			if(promocion.getAtraccionesEnPromocion().size()< 3) {
				sql = "INSERT INTO promociones (tipo_promo, valor_promo, id_atr1, id_atr2)"
						+ "VALUES ((SELECT tp.ID FROM tipos_promocion tp LEFT JOIN promociones p ON p.tipo_promo=tp.ID WHERE tp.tipo LIKE ? ),"
						+ " ? ,(SELECT ID FROM atracciones WHERE nombre LIKE ? ), (SELECT ID FROM atracciones WHERE nombre LIKE ? ))";
			} else {
				sql = "INSERT INTO promociones (tipo_promo, valor_promo, id_atr1, id_atr2, id_atr3) "
						+ "VALUES ((SELECT tp.ID FROM tipos_promocion tp LEFT JOIN promociones p ON p.tipo_promo=tp.ID WHERE tp.tipo LIKE ? ),"
						+ " ? ,(SELECT ID FROM atracciones WHERE nombre LIKE ? ), (SELECT ID FROM atracciones WHERE nombre LIKE ? ),"
						+ "(SELECT ID FROM atracciones WHERE nombre LIKE ? ))";
			}
			
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, promocion.getTipo().toString());
			statement.setInt(2, promocion.getValorPromo());
			statement.setString(3, promocion.getAtraccionesEnPromocion().get(0).getNombre());
			statement.setString(4, promocion.getAtraccionesEnPromocion().get(1).getNombre());
			if(promocion.getAtraccionesEnPromocion().size()>2) {
				statement.setString(5, promocion.getAtraccionesEnPromocion().get(2).getNombre());
			}
			int rows = statement.executeUpdate();

			return rows;
		} catch(Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	public int update(Promocion promocion) {
		//no hay nada que actualizar
		return 0;
	}
	
	public Integer findByAtraccionesList(List<Atraccion> atracciones) {
		try {
			String sql;
			if(atracciones.size()< 3) {
				sql="SELECT ID FROM promociones WHERE id_atr1= (SELECT ID FROM atracciones WHERE nombre LIKE ? ) AND "
						+ "id_atr2=(SELECT ID FROM atracciones WHERE nombre LIKE ? )";
			} else {
				sql="SELECT ID FROM promociones WHERE id_atr1= (SELECT ID FROM atracciones WHERE nombre LIKE ? ) AND "
						+ "id_atr2=(SELECT ID FROM atracciones WHERE nombre LIKE ? ) AND "
						+ "id_atr3=(SELECT ID FROM atracciones WHERE nombre LIKE ? )";
			}
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, atracciones.get(0).getNombre());
			statement.setString(2, atracciones.get(1).getNombre());
			if(atracciones.size()>2) {
				statement.setString(3, atracciones.get(2).getNombre());
			}
			ResultSet resultados = statement.executeQuery();

			Integer resultadoProcesado=null;

			if (resultados.next()) {
				resultadoProcesado = resultados.getInt(1);
			}

			return resultadoProcesado;
		} catch(Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	public Promocion findById(Integer id) {
		try {
			String sql="SELECT * FROM promociones WHERE id= ? ";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			Promocion resultadoProcesado=null;

			if (resultados.next()) {
				resultadoProcesado = toPromocion(resultados);
			}

			return resultadoProcesado;
			
		} catch(Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	public int delete(Promocion promocion) {
		try {
			String sql = "DELETE FROM promociones WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1,this.findByAtraccionesList(promocion.getAtraccionesEnPromocion()));
			int rows = statement.executeUpdate();

			return rows;
		} catch(Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	public Promocion toPromocion(ResultSet resultado) {
		try {
			//si hay una tercera atraccion la tomo, si no no
			//selecciona los nombres de atraccion de los id que se le pasen,
			//y obtiene el tipo de promo a partir del id de la promocion que se le pase.
			String sql;
			if(resultado.getString("id_atr3") != null) {
				sql="SELECT (SELECT nombre FROM atracciones WHERE id= ? ) AS 'atr1',"
						+ "(SELECT nombre FROM atracciones WHERE id= ? ) AS 'atr2',"
						+ "(SELECT nombre FROM atracciones WHERE id= ? ) AS 'atr3', tp.tipo AS 'tipopromocion' FROM promociones p "
						+ "JOIN tipos_promocion tp ON tp.ID = p.tipo_promo WHERE p.id= ? ";
			} else {
				sql= "SELECT (SELECT nombre FROM atracciones WHERE id= ? ) AS 'atr1',(SELECT nombre FROM atracciones WHERE id= ? ) "
						+ "AS 'atr2',tp.tipo AS 'tipopromocion' "
						+ "FROM promociones p JOIN tipos_promocion tp ON tp.ID = p.tipo_promo WHERE p.id= ?  ";
			}
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, resultado.getInt("id_atr1"));
			statement.setInt(2, resultado.getInt("id_atr2"));
			//mismo if que antes. El orden cambia dependiendo de si hay una atraccion mas o no
			if(resultado.getString("id_atr3") != null) {
				statement.setInt(3, resultado.getInt("id_atr3"));
				statement.setInt(4, resultado.getInt("id"));
			} else {
				statement.setInt(3, resultado.getInt("id"));
			}
			ResultSet resultadoDos = statement.executeQuery();
			//los guardo en variables
			//convierto los string en Atraccion
			Atraccion atr1= DAOFactory.getAtraccionDAO().findByNombre(resultadoDos.getString("atr1"));
			Atraccion atr2= DAOFactory.getAtraccionDAO().findByNombre(resultadoDos.getString("atr2"));
			//las agrego a una lista para poder pasarlas como par�metro
			List<Atraccion> atracciones= new ArrayList<Atraccion>();
			atracciones.add(atr1); 
			atracciones.add(atr2);
			
			//busco el valor de la promocion en el resultado que fue pasado por parametro al metodo
			sql="SELECT valor_promo FROM promociones WHERE id= ? ";
			statement = conn.prepareStatement(sql);
			statement.setInt(1, resultado.getInt("id"));
			ResultSet resultadoTres = statement.executeQuery();
			int valorPromo= resultadoTres.getInt("valor_promo");
			
			TipoDePromo tipoPromocion= TipoDePromo.valueOf(resultadoDos.getString("tipopromocion"));
			
			Promocion retorno;
			switch(tipoPromocion) {
				case ABSOLUTA:
					//dependiendo de si tiene 3 atracciones para agregar, la agrega o no
					if(resultado.getString("id_atr3") != null) {
						Atraccion atr3= DAOFactory.getAtraccionDAO().findByNombre(resultadoDos.getString("atr3"));
						atracciones.add(atr3);
					}
					retorno= new PromoAbsoluta(atracciones, valorPromo);
					break;
				case PORCENTUAL:
					if(resultado.getString("id_atr3") != null) {
						Atraccion atr3= DAOFactory.getAtraccionDAO().findByNombre(resultadoDos.getString("atr3"));
						atracciones.add(atr3);
					}
					retorno= new PromoPorcentual(atracciones, valorPromo);
					break;
				case AxB:
					if(resultado.getString("id_atr3") != null) {
						Atraccion atr3= DAOFactory.getAtraccionDAO().findByNombre(resultadoDos.getString("atr3"));
						atracciones.add(atr3);
					}
					retorno= new PromoAxB(atracciones);
					break;
				case DEFAULT:
					retorno= null;
					break;
				default:
					retorno= null;
			}
			return retorno;
		} catch(Exception e) {
			
			throw new MissingDataException(e);
		}
	}
}
