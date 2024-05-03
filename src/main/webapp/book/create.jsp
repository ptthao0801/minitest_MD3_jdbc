<%--
  Created by IntelliJ IDEA.
  User: pttha
  Date: 2024-05-03
  Time: 9:40 a.m.
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
        <a href="books?action=books">List All Books</a>
    </h2>
</div>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>Add New Book</h2>
            </caption>
            <tr>
                <th>Book Name:</th>
                <td>
                    <input type="text" name="name" id="name">
                </td>
            </tr>
            <tr>
                <th>Author:</th>
                <td>
                    <input type="text" name="author" id="author">
                </td>
            </tr>
            <tr>
                <th>Price: </th>
                <td>
                    <input type="number" name="price" id="price">
                </td>
            </tr>
            <tr>
                <th>Image:</th>
                <td>
                    <input type="text" name="img" id="img">
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
