package br.com.fabricadeprogramador.controller;

import java.io.IOException;
import java.util.List;

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
		String acao = request.getParameter("acao");
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		if (acao != null && !acao.isEmpty()) {
			if (acao.equals("exc")) {
				String id = request.getParameter("id");

				if (id != null && !id.isEmpty()) {
					usuario.setId(Integer.parseInt(id));
					Usuario usuExc = new Usuario();
					usuExc.setId(Integer.parseInt(id));

					usuarioDAO.excluir(usuExc);

					response.getWriter().print("Removido com Sucesso!");
				}
			} else if (acao.equals("lis")) {
				List<Usuario> lista = usuarioDAO.buscarTodos();

				request.setAttribute("lista", lista);

				request.getRequestDispatcher("WEB-INF/listausuarios.jsp").forward(request, response);
			} else if (acao.equals("alt")) {

				String id = request.getParameter("id");
				Usuario usuAlt = usuarioDAO.buscarPorId(Integer.parseInt(id));

				request.setAttribute("usu", usuAlt);

				request.getRequestDispatcher("WEB-INF/formusuario.jsp").forward(request, response);
			} else if (acao.equals("cad")) {

				Usuario usuCad = new Usuario();
				usuCad.setId(0);
				usuCad.setNome("");
				usuCad.setLogin("");
				usuCad.setSenha("");

				request.setAttribute("usu", usuCad);

				request.getRequestDispatcher("WEB-INF/formusuario.jsp").forward(request, response);
			}
		}

		// usuario.setNome(request.getParameter("nome"));
		// usuario.setLogin(request.getParameter("login"));
		// usuario.setSenha(request.getParameter("senha"));
		//
		//
		//
		// usuarioDAO.salvar(usuario);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Requisição pelo método POST !");

		String acao = request.getParameter("acao");

		if (acao != null && !acao.isEmpty()) {

			UsuarioDAO usuarioDAO = new UsuarioDAO();

			if (acao.equals("salvar")) {
				Usuario usuario = new Usuario();

				String id = request.getParameter("id");

				if (id != null && !id.isEmpty()) {
					usuario.setId(Integer.parseInt(id));
				}

				usuario.setNome(request.getParameter("nome"));
				usuario.setLogin(request.getParameter("login"));
				usuario.setSenha(request.getParameter("senha"));

				usuarioDAO.salvar(usuario);
				
				String msg = "<script>" + "alert('Salvo com sucesso!'); " + 
							 "location.href='usucontroller.do?acao=lis';" + 
						     "</script>";
				 response.getWriter().print(msg);

			} else if (acao.equals("exc")) {
				// Captura todos os id checados
				String ids[] = request.getParameterValues("id");

				for (String id : ids) {
					Usuario usuExcluir = new Usuario();
					usuExcluir.setId(Integer.parseInt(id));

					usuarioDAO.excluir(usuExcluir);
				}
				response.sendRedirect("usucontroller.do?acao=lis");
			}
		}

	}

}
