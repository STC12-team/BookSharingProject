<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:default title="Login page">
    <jsp:body>
        <form action="/j_username_security_check" method="post">
            <c:if test="${not empty loginError}">
                <div class="alert alert-danger" role="alert">
                    Неверный логин, или пароль
                </div>
            </c:if>
            <h2>Вход в систему</h2>
            <div>
                <input type="text" class="form-control" name="j_username" placeholder="Input your login" required><br>
            </div>
            <div>
                <input type="password" class="form-control" name="j_password" placeholder="Input your password"
                       required><br>
            </div>
            <div>
                <input type="submit" class="btn btn-primary" value="Login">
            </div>
        </form>
    </jsp:body>
</t:default>