<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:default title="Catalog">
    <jsp:body>
        <h1>Welcome to secure area!</h1>
        <p class="lead">${user}</p>
    </jsp:body>
</t:default>