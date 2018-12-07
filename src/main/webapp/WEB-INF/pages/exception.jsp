<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:default title="Букшеринг">
    <jsp:body>
        <h2><spring:message code="exception.title"/></h2>
        <div class="alert alert-danger" role="alert">
                ${exception.getMessage()}
        </div>
    </jsp:body>
</t:default>