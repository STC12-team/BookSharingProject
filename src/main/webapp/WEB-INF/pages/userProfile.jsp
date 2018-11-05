<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:default title="Профиль пользователя">
    <jsp:body>
        <h1>Profile page</h1>

        <c:if test="${not empty errorMessage}">
            <div class="alert alert-warning">
                    ${errorMessage}
            </div>
        </c:if>

        <p>${userDetails}</p>
    </jsp:body>
</t:default>