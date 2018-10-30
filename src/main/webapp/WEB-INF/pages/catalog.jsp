<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:default title="Каталог">
    <jsp:body>
        <h1>Вы в каталоге ${pageContext.request.userPrincipal.name} </h1>
        <p class="lead">${user}</p>

        <c:if test="${not empty message}">
            <div class="alert alert-warning">
                    ${message}
            </div>
        </c:if>
        <c:choose>
            <c:when test="${not empty bookCopies}">
                <t:bookcopieslist bookCopies="${bookCopies}"/>
            </c:when>
        </c:choose>
    </jsp:body>
</t:default>