<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:default title="Профиль пользователя">
    <jsp:body>
        <c:if test="${not empty profileEditResultMessage}">
            <div class="alert alert-success">
                    ${profileEditResultMessage}
            </div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-warning">
                    ${errorMessage}
            </div>
        </c:if>

        <c:choose>
            <c:when test="${userDetails != null}">
                <t:userdetail userDetails="${userDetails}"/>
            </c:when>
        </c:choose>
    </jsp:body>
</t:default>