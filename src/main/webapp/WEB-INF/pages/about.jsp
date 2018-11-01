<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:default title="Букшеринг">
    <jsp:body>
        <h1>Букшеринг</h1>
        <p class="lead">${aboutText}</p>

        <c:if test="${username != null}">
            <h3>Welcome ${username}</h3>
        </c:if>
        <br />
        <a class="btn btn-lg btn-primary" href="index.jsp" role="button">Back</a>
    </jsp:body>
</t:default>