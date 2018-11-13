<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:default title="Букшеринг">
    <jsp:body>
        <h2>Ошибка</h2>
        <div class="alert alert-danger" role="alert">
                ${exception.getMessage()}
        </div>
    </jsp:body>
</t:default>