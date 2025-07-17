<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@elvariable id="action" type="java.lang.String"--%>
<html>
<head>
    <title>Books</title>
</head>
<body>
<c:choose>
    <c:when test="${action == 'add'}">
        <h1>Adding new book</h1>
    </c:when>
    <c:otherwise>
        <h1>Edit book</h1>
    </c:otherwise>
</c:choose>

<%--@elvariable id="book" type="cz.kocabek.bookapispringbootmysql.model.Book"--%>
<form:form method="post" modelAttribute="book" action="/books/${action}">
    <form:hidden path="id"/>
    <fieldset>
        <label>
            Title:
            <form:input path="title"/>
        </label>
        <form:errors path="title"/>
        <br/>
        <label>
            Author:
            <form:input path="author"/>
        </label>
        <form:errors path="author"/>
        <br/>
        <label>
            ISBN:
            <form:input path="isbn"/>
        </label>
        <form:errors path="isbn"/>
        <br/>
        <label>
            publisher:
            <form:input path="publisher"/>
        </label>
        <form:errors path="publisher"/>
        <br/>
        <label>
            Type:
            <form:input path="type"/>
        </label>
        <form:errors path="type"/>
        <br/>
        <input type="submit" value="Save"/>
    </fieldset>
</form:form>
</body>
</html>
