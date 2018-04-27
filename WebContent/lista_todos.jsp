<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="Model.Pais" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listar Paises</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<!-- Barra superior com os menus de navegação -->
<c:import url="Menu.jsp"/>
<!-- Container Principal -->

<div class="container">
  <h2>Todos Paises</h2>     
    
  <table class="table">
    <thead>
      <tr>
        <th>Nome</th>
        <th>Area</th>
        <th>Populacao</th>
        <th>Deletar</th>
        <th>Alterar</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${arrayPais}" var="pais">
     <form action="ManterPais.do" method="post">  
      <tr>
	        <input input type="hidden" class="form-control" name="id" value="${pais.id}">
	        <td><input class="form-control" name="nome" value="${pais.nome}"></td>
	        <td><input class="form-control" name="area" value="${pais.area}"></td>
	        <td><input class="form-control" name="populacao" value="${pais.populacao}"></td>
	        <td><button type="submit" class="btn btn-default" name="acao" value="alterarPais">Alterar Pais</button></td>
	 		<td><button type="submit" class="btn btn-default" name="acao" value="excluirPais">Excluir Pais</button></td>
      </tr>
       </form>
       </c:forEach>
    </tbody>
  </table>
 
</div>




</body>
</html>