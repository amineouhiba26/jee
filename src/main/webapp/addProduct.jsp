<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>${isEdit ? 'Modifier Produit' : 'Ajouter Produit'}</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h2>${isEdit ? 'Modifier un produit' : 'Ajouter un nouveau produit'}</h2>

    <form method="post" action="Controlleur">
        <c:if test="${productToEdit != null}">
            <input type="hidden" name="id" value="${productToEdit.id}" />
        </c:if>

        <div class="mb-3">
            <label for="nom" class="form-label">Nom</label>
            <input type="text" class="form-control" id="nom" name="nom" required
                   value="${productToEdit != null ? productToEdit.nom : ''}">
        </div>

        <div class="mb-3">
            <label for="prix" class="form-label">Prix</label>
            <input type="number" step="0.01" class="form-control" id="prix" name="prix" required
                   value="${productToEdit != null ? productToEdit.prix : ''}">
        </div>

        <div class="mb-3">
            <label for="quantite" class="form-label">Quantité</label>
            <input type="number" class="form-control" id="quantite" name="quantite" required
                   value="${productToEdit != null ? productToEdit.quantite : ''}">
        </div>

        <button type="submit" class="btn btn-primary">
            ${isEdit ? 'Mettre à jour' : 'Ajouter'}
        </button>
        <a href="Controlleur" class="btn btn-secondary">Retour</a>
    </form>
</div>
</body>
</html>
