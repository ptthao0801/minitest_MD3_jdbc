<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pttha
  Date: 2024-05-03
  Time: 12:52 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book Management Application</title>
</head>
<body>
<div align="center">
    <h1>Book Management</h1>
    <h2>
        <a href="users?action=books">List All Books</a>
    </h2>
</div>
<div align="center">
    <form method="post">
        <table border = "1" cellpadding="5">
            <caption><h2>Edit Book</h2></caption>
            <c:if test ="${book != null}">
                <input type="hidden" name="id" value="<c:out value='${book.id}'/>">
            </c:if>
            <tr>
                <th>Book Name:</th>
                <td>
                    <input type="text" name="name" size="45" value="<c:out value='${book.name}'/>">
                </td>
            </tr>
            <tr>
                <th>Author: </th>
                <td>
                    <input type="text" name="author" size="45" value="<c:out value='${book.author}'/>">
                </td>
            </tr>
            <tr>
                <th>Price: </th>
                <td>
                    <input type="number" name="price" size="45" value="<c:out value='${book.price}'/>">
                </td>
            </tr>
            <tr>
                <th>Img: </th>
                <td>
                    <input type="text" name="img" size="45" value="<c:out value='${book.img}'/>">
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
