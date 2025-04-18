<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>${isEdit ? 'Modifier' : 'Ajouter'} un Produit</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h2>${isEdit ? 'Modifier' : 'Ajouter'} un Produit</h2>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">
                ${error == 'missing' ? 'Veuillez remplir tous les champs' : 'Format invalide pour le prix ou la quantité'}
        </div>
    </c:if>

    <form method="post" action="Controlleur">
        <input type="hidden" name="id" value="${productToEdit.id}">

        <div class="mb-3">
            <label for="nom" class="form-label">Nom</label>
            <input type="text" class="form-control" id="nom" name="nom"
                   value="${productToEdit.nom}" required>
        </div>

        <div class="mb-3">
            <label for="prix" class="form-label">Prix</label>
            <input type="number" step="0.01" class="form-control" id="prix" name="prix"
                   value="${productToEdit.prix}" required>
        </div>

        <div class="mb-3">
            <label for="quantite" class="form-label">Quantité</label>
            <input type="number" class="form-control" id="quantite" name="quantite"
                   value="${productToEdit.quantite}" required>
        </div>

        <div class="mb-3">
            <label for="categorieId" class="form-label">Catégorie</label>
            <select class="form-select" id="categorieId" name="categorieId" required>
                <option value="">Sélectionnez une catégorie</option>
                <c:forEach items="${categories}" var="categorie">
                    <option value="${categorie.id}"
                        ${productToEdit != null && productToEdit.categorie.id == categorie.id ? 'selected' : ''}>
                            ${categorie.nom}
                    </option>
                </c:forEach>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">${isEdit ? 'Modifier' : 'Ajouter'}</button>
        <a href="Controlleur" class="btn btn-secondary">Annuler</a>
        <a href="addCategorie.jsp"
           class="btn btn-secondary">Ajouter category
        </a>

    </form>
</div>

</body>
</html>
