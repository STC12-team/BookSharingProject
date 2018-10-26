<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:default title="Book sharing">
    <jsp:body>
        <form action="/j_username_security_check" method="post">
            <c:if test="${not empty loginError}">
                <H3>Неверный логин, или пароль</H3>
            </c:if>
            <h2>Вход в систему</h2>
            <input type="text" name="j_username" placeholder="Введите логин" required><br>
            <input type="password" name="j_password" placeholder="Введите пароль" required>
            <input type="submit" value="Login">
        </form>
    </jsp:body>
</t:default>