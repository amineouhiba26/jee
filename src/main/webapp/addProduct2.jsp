<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${isEdit != null && isEdit ? 'Modifier produit' : 'Ajouter un produit'}</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>
<%@ include file="header.html" %>

<div class="container">
    <h2 class="mt-3">${isEdit != null && isEdit ? 'Modifier produit' : 'Ajouter un produit'}</h2>
    <form method="post" action="ajouter">
        <div class="mb-3">
            <label for="nom" class="form-label">Nom</label>
            <input type="text" class="form-control" id="nom" name="nom" required 
                   value="${produit.nom }" 
                   placeholder="Nom du produit">
        </div>
        <div class="mb-3">
            <label for="prix" class="form-label">Prix</label>
            <input type="number" class="form-control" id="prix" name="prix" required 
                   value="${produit.prix }" 
                   placeholder="Prix du produit">
        </div>
        <div class="mb-3">
            <label for="quantite" class="form-label">Quantité</label>
            <input type="number" class="form-control" id="quantite" name="quantite" required 
                   value="${produit.quantite }" 
                   placeholder="Quantité du produit">
        </div>
        <button type="submit" class="btn btn-primary">
            ${productToEdit != null ? 'Mettre à jour' : 'Ajouter'}
        </button>
        <input type="hidden" name="id" value="${produit.id }">
    </form>
</div>

</body>
</html>
