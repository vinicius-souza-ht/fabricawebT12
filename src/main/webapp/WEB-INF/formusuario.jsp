<!DOCTYPE html>
<%@page
	import="br.com.fabricadeprogramador.persistencia.entidade.Usuario"%>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de Usuário</title>

<script>
	function validar() {
		camponome = document.frmusu.nome;

		if (camponome.value == "") {
			alert("Preencha o campo nome.");
			camponome.focus();
			return false;
		}

		return true;
	}
</script>
</head>
<body>
	<%
		Usuario usuAlt = (Usuario) request.getAttribute("usu");
	%>
	<%@include file="includes/menu.jsp" %>
	
	<form name="frmusu" method="post" action="usucontroller.do" onsubmit="return validar()">
		<input type="hidden" name="acao" value="salvar"> 
		
			Id: <input size="5" type="text" name="id" value="<%=usuAlt.getId()%>" readonly="readonly"> 
			Nome: <input type="text" name="nome" value="<%=usuAlt.getNome()%>"> 
			Login: <input type="text" name="login" value="<%=usuAlt.getLogin()%>"> 
			Senha: <input type="password" name="senha" value="<%=usuAlt.getSenha()%>">

		<input type="submit" value="Salvar">
	</form>
</body>
</html>