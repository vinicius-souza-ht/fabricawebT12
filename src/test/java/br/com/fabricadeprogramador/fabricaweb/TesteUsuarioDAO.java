package br.com.fabricadeprogramador.fabricaweb;

import java.util.List;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

public class TesteUsuarioDAO {

	public static void main(String[] args) {
		// testeCadastrar();
		// testeAlterar();
		// testeExcluir();
		// testeSavar();
		// testeBuscarPorId();
		testeBuscarTodos();
	}

	private static void testeCadastrar() {
		Usuario usuario = new Usuario();

		usuario.setNome("Maria");
		usuario.setLogin("mari");
		usuario.setSenha("123");

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.cadastrar(usuario);
	}

	private static void testeAlterar() {
		Usuario usuario = new Usuario();

		usuario.setId(3);
		usuario.setNome("Maria das Neves");
		usuario.setLogin("mariN");
		usuario.setSenha("1234");

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.alterar(usuario);
	}

	private static void testeExcluir() {
		Usuario usuario = new Usuario();

		usuario.setId(3);

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.excluir(usuario);
	}

	private static void testeSavar() {
		Usuario usuario = new Usuario();

		usuario.setId(4);
		usuario.setNome("Carlos Alberto");
		usuario.setLogin("carA");
		usuario.setSenha("1234");

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
	}

	private static void testeBuscarPorId() {

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuRetornado = usuarioDAO.buscarPorId(4);

		if (usuRetornado != null) {
			System.out.println(usuRetornado);
		}
	}

	private static void testeBuscarTodos() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		List<Usuario> lista = usuarioDAO.buscarTodos();

		for (Usuario usuario : lista) {
			System.out.println(usuario);
		}
	}

}
