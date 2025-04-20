<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entities.Produit" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Panier</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2>Mon Panier</h2>

    <a href="Controlleur" class="btn btn-secondary mb-3">Retour à la liste des produits</a>

    <c:choose>
        <c:when test="${empty cart}">
            <p>Aucun produit dans le panier.</p>
        </c:when>
        <c:otherwise>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Prix</th>
                    <th>Quantité</th>
                    <th>Total</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="p" items="${cart}">
                    <tr>
                        <td>${p.id}</td>
                        <td>${p.nom}</td>
                        <td>${p.prix}</td>
                        <td>${p.orderedQuantity}</td>
                        <td>${p.getTotalPrice()} €</td>
                        <td>
                            <a href="Controlleur?action=removeFromCart&id=${p.id}" class="btn btn-danger btn-sm">Retirer</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
