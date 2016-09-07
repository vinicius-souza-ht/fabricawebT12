<%@page import="br.com.fabricadeprogramador.persistencia.entidade.Usuario"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista de Usuários</title>
</head>
<body>

<%
	//Capturando a lista do request
	List<Usuario> lista = (List<Usuario>)request.getAttribute("lista");
%>

<table border="1">
	<tr bgcolor="#eaeaea">
		<td>ID</td>
		<td>NOME</td>
		<td>LOGIN</td>
		<td>SENHA</td>
	</tr>
	
	<%for (Usuario usuario : lista) {%>
	<tr>
		<td><%= usuario.getId()%></td>
		<td><%= usuario.getNome()%></td>
		<td><%= usuario.getLogin()%></td>
		<td><%= usuario.getSenha()%></td>
	</tr>
	
	<%}%>
	
</table>
</body>
</html>