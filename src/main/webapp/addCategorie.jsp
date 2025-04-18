<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${isEdit ? "Modifier une Catégorie" : "Ajouter une Catégorie"}</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            padding: 30px;
        }

        .form-container {
            background: white;
            padding: 20px;
            width: 400px;
            margin: auto;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            color: #343a40;
        }

        label {
            display: block;
            margin-top: 15px;
        }

        input[type="text"] {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            box-sizing: border-box;
        }

        .error {
            color: red;
            margin-top: 10px;
            text-align: center;
        }

        .btn {
            margin-top: 20px;
            background-color: #007bff;
            color: white;
            padding: 10px;
            width: 100%;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .btn:hover {
            background-color: #0056b3;
        }

        .back-link {
            display: block;
            margin-top: 15px;
            text-align: center;
        }
    </style>
</head>
<body>

<div class="form-container">
    <h2>${isEdit ? "Modifier une Catégorie" : "Ajouter une Catégorie"}</h2>

    <form action="categorie" method="post">
        <c:if test="${isEdit}">
            <input type="hidden" name="id" value="${categorieToEdit.id}"/>
        </c:if>

        <label for="nom">Nom de la Catégorie:</label>
        <input type="text" id="nom" name="nom" value="${categorieToEdit.nom != null ? categorieToEdit.nom : ''}" required/>

        <c:if test="${param.error == 'missing'}">
            <div class="error">Le nom de la catégorie est obligatoire.</div>
        </c:if>

        <button type="submit" class="btn">${isEdit ? "Mettre à jour" : "Ajouter"}</button>
    </form>

    <a class="back-link" href="categorie">← Retour à la liste des catégories</a>
</div>

</body>
</html>
