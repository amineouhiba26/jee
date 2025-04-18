<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Management</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <!-- Custom CSS -->
    <style>
        body {
            background-color: #f8f9fa;
            padding-top: 2rem;
        }
        .container {
            max-width: 1200px;
        }
        .table-container {
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
            padding: 2rem;
            margin-bottom: 2rem;
        }
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 2rem;
        }
        .action-btns a {
            margin-right: 10px;
        }
        .badge-admin {
            background-color: #6f42c1;
        }
        .badge-user {
            background-color: #20c997;
        }
        .table th {
            background-color: #343a40;
            color: white;
        }
        .btn-custom {
            min-width: 120px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h1 class="display-4 text-primary"><i class="fas fa-users-cog me-2"></i>User Management</h1>
        <a href="Controlleur" class="btn btn-secondary btn-custom">
            <i class="fas fa-arrow-left me-1"></i> Aller vers les produits
        </a>
    </div>

    <div class="table-container">
        <div class="d-flex justify-content-end mb-4">
            <a href="addUser.jsp" class="btn btn-success btn-custom">
                <i class="fas fa-user-plus me-1"></i> Add New User
            </a>
        </div>

        <div class="table-responsive">
            <table class="table table-hover align-middle">
                <thead class="table-dark">
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Name</th>
                    <th scope="col">Role</th>
                    <th scope="col" class="text-center">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.id}</td>
                        <td>
                            <div class="d-flex align-items-center">
                                <div class="avatar me-2">
                                    <i class="fas fa-user-circle fa-2x text-primary"></i>
                                </div>
                                <div>
                                    <strong>${user.name}</strong>
                                </div>
                            </div>
                        </td>
                        <td>
                                <span class="badge ${user.role != null && user.role.name.equals('Admin') ? 'badge-admin' : 'badge-user'} rounded-pill p-2">
                                        ${user.role != null ? user.role.name : 'No Role'}
                                </span>
                        </td>
                        <td class="text-center">
                            <div class="btn-group" role="group">
                                <a href="UserController?action=makeAdmin&id=${user.id}"
                                   class="btn btn-sm btn-outline-primary"
                                   title="Make Admin">
                                    <i class="fas fa-user-shield"></i>
                                </a>
                                <a href="UserController?action=delete&id=${user.id}"
                                   class="btn btn-sm btn-outline-danger"
                                   onclick="return confirm('Are you sure you want to delete this user?');"
                                   title="Delete">
                                    <i class="fas fa-trash-alt"></i>
                                </a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <div class="text-center text-muted mt-4">
        <p>© 2023 User Management System. All rights reserved.</p>
    </div>
</div>

<!-- Bootstrap JS Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
