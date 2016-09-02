package br.com.fabricadeprogramador.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;

public class UsuarioDAO {

	Connection conn = ConexaoFactory.getConnection();
	
	public void cadastrar(Usuario usuario) {

		String sql = "INSERT INTO usuario (nome, login, senha) VALUES (?, ?, MD5(?))";

		try (PreparedStatement preparador = conn.prepareStatement(sql)) {

			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getLogin());
			preparador.setString(3, usuario.getSenha());

			preparador.execute();

			System.out.println("Usuário cadastrado com sucesso!");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void alterar(Usuario usuario) {

		String sql = "UPDATE usuario SET nome = ?, login = ?, senha = MD5(?) WHERE id = ?";

		try (PreparedStatement preparador = conn.prepareStatement(sql)) {

			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getLogin());
			preparador.setString(3, usuario.getSenha());
			preparador.setInt(4, usuario.getId());

			preparador.execute();

			System.out.println("Usuário alterado com sucesso!");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void excluir(Usuario usuario) {

		String sql = "DELETE FROM usuario WHERE id = ?";

		try (PreparedStatement preparador = conn.prepareStatement(sql)) {

			preparador.setInt(1, usuario.getId());

			preparador.execute();

			System.out.println("Usuário excluído com sucesso!");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void salvar(Usuario usuario) {

		if (usuario.getId() != null && usuario.getId() > 0) {
			alterar(usuario);
		} else {
			cadastrar(usuario);
		}
	}

	public Usuario buscarPorId(Integer id) {

		Usuario usuRetorno = null;

		String sql = "SELECT * FROM usuario WHERE id = ?";

		try (PreparedStatement preparador = conn.prepareStatement(sql)) {

			preparador.setInt(1, id);

			ResultSet resultado = preparador.executeQuery();

			if (resultado.next()) {
				usuRetorno = new Usuario();
				usuRetorno.setId(resultado.getInt("id"));
				usuRetorno.setNome(resultado.getString("nome"));
				usuRetorno.setLogin(resultado.getString("login"));
				usuRetorno.setSenha(resultado.getString("senha"));
			}

			System.out.println("Usuário encontrado com sucesso!");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return usuRetorno;
	}

	public List<Usuario> buscarTodos() {
		List<Usuario> listaRetorno = new ArrayList<>();
		Usuario usuRetorno = null;

		String sql = "SELECT * FROM usuario";

		try (PreparedStatement preparador = conn.prepareStatement(sql)) {

			ResultSet resultado = preparador.executeQuery();

			while (resultado.next()) {
				usuRetorno = new Usuario();
				usuRetorno.setId(resultado.getInt("id"));
				usuRetorno.setNome(resultado.getString("nome"));
				usuRetorno.setLogin(resultado.getString("login"));
				usuRetorno.setSenha(resultado.getString("senha"));
				
				listaRetorno.add(usuRetorno);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return listaRetorno;
	}
	
	public Usuario autenticar(Usuario usuConsulta){
		
		Usuario usuRetorno = null;
		
		String sql = "SELECT * FROM usuario WHERE login = ? AND senha = MD5(?)";
		
		try (PreparedStatement preparador = conn.prepareStatement(sql)){
			
			preparador.setString(1, usuConsulta.getLogin());
			preparador.setString(2, usuConsulta.getSenha());
			
			ResultSet resultado = preparador.executeQuery();
			
			if(resultado.next()){
				usuRetorno = new Usuario();
				usuRetorno.setId(resultado.getInt("id"));
				usuRetorno.setNome(resultado.getString("nome"));
				usuRetorno.setLogin(resultado.getString("login"));
				usuRetorno.setSenha(resultado.getString("senha"));
				
				System.out.println("Usuário Autenticado !");
			}
			
		} catch(SQLException e){
			throw new RuntimeException(e);
		}
		
		return usuRetorno;
	}

}
