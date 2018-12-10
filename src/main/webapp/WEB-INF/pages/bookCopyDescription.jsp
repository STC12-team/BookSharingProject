<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:default title="Описание книги">
    <jsp:body>
        <t:bookcopydescription bookCopy="${bookCopy}"/>
    </jsp:body>
</t:default>