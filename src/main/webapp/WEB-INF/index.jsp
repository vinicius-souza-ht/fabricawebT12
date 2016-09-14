<%@page
	import="br.com.fabricadeprogramador.persistencia.entidade.Usuario"%>
<html>
<body>
	<%@include file="includes/menu.jsp"%>
	<%
		Usuario usuario = (Usuario) session.getAttribute("usuAutenticado");
	%>
	Bem Vindo,
	<%=usuario.getNome()%>
</body>
</html>