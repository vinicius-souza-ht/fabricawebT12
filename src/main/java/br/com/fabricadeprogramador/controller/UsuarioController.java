package br.com.fabricadeprogramador.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet("/usucontroller.do")
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsuarioController() {
		System.out.println("Chamando o construtor do Servlet !");
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Iniciando o Servlet !");
	}

	@Override
	public void destroy() {
		System.out.println("Finalizando o Servlet !");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			System.out.println("Requisição pelo método GET !");
			
			Usuario usuario = new Usuario();
			
			usuario.setNome(request.getParameter("nome"));
			usuario.setLogin(request.getParameter("login"));
			usuario.setSenha(request.getParameter("senha"));
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			
			usuarioDAO.salvar(usuario);

			response.getWriter().print("Salvo com Sucesso!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// doGet(request, response);
		System.out.println("Requisição pelo método POST !");
	}

}
