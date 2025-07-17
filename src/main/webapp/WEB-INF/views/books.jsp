<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Books</title>
    <link href="${pageContext.request.contextPath}/css/table.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>Books</h1>
<table>
    <thead>
    <tr>
        <th>Title</th>
        <th>ISBN</th>
        <th>Author</th>
        <th>Publisher</th>
        <th>Type</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <%--@elvariable id="books" type="java.util.List<cz.kocabek.bookapispringbootmysql.model.Book>"--%>
    <c:forEach items="${books}" var="items">
        <tr>
            <td>${items.title}</td>
            <td>${items.isbn}</td>
            <td>${items.author}</td>
            <td>${items.publisher}</td>
            <td>${items.type}</td>
            <td>
                <a class="btn" href="/books/${items.id}">Show book</a>
                <br>
                <a class="btn" href="/books/${items.id}/edit">Edit book</a>
                <br>
                <a class="btn" href="/books/${items.id}/delete">Delete book</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
