<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add/Edit User</title>
</head>
<body>
<h1>${isEdit != null && isEdit ? 'Edit User' : 'Add New User'}</h1>

<form action="UserController" method="post">
    <input type="hidden" name="id" value="${userToEdit.id != null ? userToEdit.id : ''}">
    <label for="name">Name:</label>
    <input type="text" name="name" id="name" value="${userToEdit != null ? userToEdit.name : ''}" required><br><br>

    <label for="password">Password:</label>
    <input type="password" name="password" id="password" value="${userToEdit != null ? userToEdit.password : ''}" required><br><br>


    <input type="submit" value="${isEdit != null && isEdit ? 'Update User' : 'Add User'}">
</form>

<br>
<a href="UserController">Back to User List</a>

<c:if test="${param.error == 'missing'}">
    <p style="color:red;">All fields are required!</p>
</c:if>
</body>
</html>
