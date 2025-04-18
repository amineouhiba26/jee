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
    <!-- Header with title and logout button -->
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>Liste des Produits</h2>
        <form action="logout" method="get">
            <button type="submit" class="btn btn-outline-danger">Déconnexion</button>
        </form>
<c:if
    test="${sessionScope.user.role.name !='ADMIN'}">
        <a href="Controlleur?action=viewCart" class="btn btn-outline-primary">Voir le panier</a>
</c:if>

    </div>

    <!-- Search form -->
    <form method="get" action="Controlleur" class="mb-3">
        <div class="input-group">
            <input type="text" class="form-control" name="mc" placeholder="Mot-clé"
                   value="${mc != null ? mc : ''}">
            <button class="btn btn-primary" type="submit">Rechercher</button>
        </div>
    </form>

    <c:if test="${sessionScope.user.role.name == 'ADMIN'}">
        <div class="mb-3">
            <a href="Controlleur?action=addProduct" class="btn btn-success">Ajouter un produit</a>
        </div>
    </c:if>

    <!-- Product list table -->
    <table class="table table-bordered table-striped">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Prix</th>
            <th>Quantité</th>
            <th>Categorie</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${empty products}">
                <tr>
                    <td colspan="6" class="text-center">Aucun produit trouvé.</td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach var="p" items="${products}">
                    <tr>
                        <td>${p.id}</td>
                        <td>${p.nom}</td>
                        <td>${p.prix}</td>
                        <td>${p.quantite}</td>
                        <td>${p.categorie.nom}</td>
                        <td>
                            <c:if
                                test="${sessionScope.user.role.name !='ADMIN'}">

                                <a href="Controlleur?action=addToCart&id=${p.id}" class="btn btn-info btn-sm">Ajouter au panier</a>
                            </c:if>

                            <c:if test="${sessionScope.user.role.name == 'ADMIN'}">

                                <a href="Controlleur?action=edit&id=${p.id}" class="btn btn-warning btn-sm">Modifier</a>
                                <a href="Controlleur?action=delete&id=${p.id}" class="btn btn-danger btn-sm"
                                   onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce produit ?');">Supprimer</a>
                            </c:if>

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
