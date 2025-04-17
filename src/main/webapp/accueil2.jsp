<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Search</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>
<% 
%>

<div class="container">
    <form method="get" action="Controlleur">
        <div class="input-group mb-3">
            <input type="text" class="form-control" name="mc" placeholder="Rechercher produit" value="<%= "${mc}" %>">
            <button type="submit" class="btn btn-primary">Rechercher</button>
        </div>
    </form>

    <div class="mb-3">
        <a href="addProduct.jsp" class="btn btn-success">Ajouter un produit</a>
    </div>

<table class="table">
    <tr>
        <th>Id</th>
        <th>Nom</th>
        <th>Prix</th>
        <th>Quantité</th>
        <th>Actions</th>
    </tr>


	
    <c:forEach items="${products}" var="p">
    
        <tr>
            <td>${p.id}</td>
            <td>${p.nom}</td>
            <td>${p.prix}</td>
            <td>${p.quantite}</td>
            <td>
                <a href="Controlleur?action=edit&id=${p.id }" class="btn btn-warning btn-sm">Edit</a>
                <a href="Controlleur?action=delete&id=${p.id }" class="btn btn-danger btn-sm">Delete</a>
            </td>
        </tr>
        
        </c:forEach>
     </table>


</div>
</body>
</html>
