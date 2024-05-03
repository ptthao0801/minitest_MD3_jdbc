<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pttha
  Date: 2024-05-03
  Time: 9:21 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book Management Application</title>
</head>
<body>
<div style="text-align: center;">
<h1>Book Management</h1>
<h2>
    <a href="/books?action=create">Add New Book</a>
</h2>
</div>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Books</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Author</th>
            <th>Price</th>
            <th>Img</th>
        </tr>
        <c:forEach var="book" items="${listBook}">
            <tr>
                <td><c:out value="${book.id}"/></td>
                <td><c:out value="${book.name}"/></td>
                <td><c:out value="${book.author}"/></td>
                <td><c:out value="${book.price}"/></td>
                <td><c:out value="${book.img}"/></td>
                <td>
                    <a href="/books?action=edit&id=${book.id}">Edit</a>
                    <a href="/books?action=delete&id=${book.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
