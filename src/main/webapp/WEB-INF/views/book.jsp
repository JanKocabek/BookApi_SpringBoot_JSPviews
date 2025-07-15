<%--
  Created by IntelliJ IDEA.
  User: sehes
  Date: 7/15/2025
  Time: 2:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Book</title>
</head>
<body>
<h1>Books</h1>
<%--@elvariable id="book" type="cz.kocabek.bookapispringbootmysql.model.Book"--%>
<ul>
    <li> Tile: ${book.title}<br/>
        Author: ${book.author}<br/>
        ISBN: ${book.isbn}<br/>
    </li>
</ul>
</body>
</html>
