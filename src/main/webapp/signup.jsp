<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inscription</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2>Créer un compte</h2>
    <form method="post" action="signup">
        <div class="mb-3">
            <label for="name" class="form-label">Nom d'utilisateur</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Mot de passe</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>
        <button type="submit" class="btn btn-success">S'inscrire</button>
        <a href="login" class="btn btn-link">Déjà un compte ? Se connecter</a>
    </form>
</div>
</body>
</html>
