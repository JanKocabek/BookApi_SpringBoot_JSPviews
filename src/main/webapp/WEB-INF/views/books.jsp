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
    <title>Books</title>
</head>
<body>
<h1>Books</h1>
<%--@elvariable id="books" type="java.util.List<cz.kocabek.bookapispringbootmysql.model.Book>"--%>
<ul>
    <c:forEach items="${books}" var="items">
        <li> Tile: ${items.title}<br/>
            Author: ${items.author}<br/>
            ISBN: ${items.isbn}<br/>
        </li>
        <br/>
    </c:forEach>
</ul>
</body>
</html>
