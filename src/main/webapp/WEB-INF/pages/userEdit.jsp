<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:default title="Редактирование профиля">
    <jsp:body>
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-warning">
                    ${errorMessage}
            </div>
        </c:if>

        <c:choose>
            <c:when test="${userDetails != null}">
                <t:userdetailsedit userDetails="${userDetails}"/>
            </c:when>
        </c:choose>
    </jsp:body>
</t:default>