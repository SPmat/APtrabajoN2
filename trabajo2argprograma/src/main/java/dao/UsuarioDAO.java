package dao;

import java.sql.ResultSet;

import model.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario>{

	public Usuario findByNombre(String nombre);
	public Usuario toUsuario(ResultSet resultado);
}
