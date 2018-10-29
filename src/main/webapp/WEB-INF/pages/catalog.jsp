<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:default title="Каталог">
    <jsp:body>
        <h1>Вы в каталоге</h1>
        <p class="lead">${user}</p>
    </jsp:body>
</t:default>