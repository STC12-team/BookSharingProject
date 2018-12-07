<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:default title="Каталог">
    <jsp:body>
        <h1><spring:message code="bookCatalog.title"/> ${pageContext.request.userPrincipal.name} </h1>
        <p class="lead">${user}</p>

        <c:if test="${not empty errorMessage}">
            <div class="alert alert-warning">
                    ${errorMessage}
            </div>
        </c:if>
        <c:choose>
            <c:when test="${not empty bookCopies}">
                <t:bookcopieslist bookCopies="${bookCopies}"/>
            </c:when>
            <c:when test="${empty bookCopies}">
                <spring:message code="bookCatalog.noBooksFound"/>
            </c:when>
        </c:choose>
    </jsp:body>
</t:default>