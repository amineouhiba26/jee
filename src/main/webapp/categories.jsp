<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Liste des Catégories</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f1f3f5;
            padding: 40px;
        }

        .container {
            max-width: 800px;
            margin: auto;
            background: white;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 6px 15px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #007bff;
            color: white;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        .btn {
            padding: 6px 14px;
            border: none;
            border-radius: 4px;
            text-decoration: none;
            color: white;
            cursor: pointer;
            font-size: 14px;
        }

        .btn-edit {
            background-color: #28a745;
        }

        .btn-delete {
            background-color: #dc3545;
        }

        .btn-add {
            background-color: #007bff;
            display: inline-block;
            margin-bottom: 20px;
            padding: 10px 20px;
            font-size: 15px;
        }

        .actions {
            display: flex;
            gap: 10px;
        }

    </style>
</head>
<body>

<div class="container">
    <h2>Liste des Catégoriess</h2>

    <a href="addCategorie.jsp" class="btn btn-add">+ Ajouter une Catégorie</a>
    <a href="Controlleur?action=addProduct">Ajouter un produit</a>


    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="cat" items="${categories}">
            <tr>
                <td>${cat.id}</td>
                <td>${cat.nom}</td>
                <td class="actions">
                    <a class="btn btn-edit" href="categorie?action=edit&id=${cat.id}">Modifier</a>
                    <a class="btn btn-delete" href="categorie?action=delete&id=${cat.id}"
                       onclick="return confirm('Voulez-vous vraiment supprimer cette catégorie ?');">
                        Supprimer
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>



    </table>
</div>
<div class="mb-3">
</div>


</body>
</html>
