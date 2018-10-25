<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:default title="Login page">
    <jsp:body>
        <h1>Please log in</h1>
        <p class="lead">${usersList}</p>

        <form action="/j_username_security_check" method="post">
            <c:if test="${not empty loginError}">
                <H3>Неверный логин, или пароль</H3>
            </c:if>
            <h2>Вход в систему</h2>
            <input type="text" name="j_username" placeholder="Input your login" required><br>
            <input type="password" name="j_password" placeholder="Input your password" required>
            <input type="submit" value="Login">
        </form>
    </jsp:body>
</t:default>