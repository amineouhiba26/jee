<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entities.Produit" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Liste des Produits</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h2>Liste des Produits</h2>

    <form method="get" action="Controlleur" class="mb-3">
        <div class="input-group">
            <input type="text" class="form-control" name="mc" placeholder="Mot-cl�"
                   value="${mc != null ? mc : ''}">
            <button class="btn btn-primary" type="submit">Rechercher</button>
        </div>
    </form>

    <div class="mb-3">
        <a href="addProduct.jsp" class="btn btn-success">Ajouter un produit</a>
    </div>

    <table class="table table-bordered table-striped">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Prix</th>
            <th>Quantit�</th>
            <th>Categorie</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${empty products}">
                <tr>
                    <td colspan="5" class="text-center">Aucun produit trouv�.</td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach var="p" items="${products}">
                    <tr>
                        <td>${p.id}</td>
                        <td>${p.nom}</td>
                        <td>${p.prix}</td>
                        <td>${p.quantite}</td>
                        <td>
                            <a href="Controlleur?action=edit&id=${p.id}" class="btn btn-warning btn-sm">Modifier</a>
                            <a href="Controlleur?action=delete&id=${p.id}" class="btn btn-danger btn-sm"
                               onclick="return confirm('�tes-vous s�r de vouloir supprimer ce produit ?');">Supprimer</a>
                        </td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
</div>

</body>
</html>
