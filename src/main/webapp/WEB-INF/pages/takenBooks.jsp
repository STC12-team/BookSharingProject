<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:default title="Взятые книги">
    <jsp:body>
        <c:if test="${not empty message}">
            <div class="alert alert-success">
                    ${message}
            </div>
        </c:if>
        <t:bookholderslist takenBooks="${takenBooksList}"/>
        <c:if test="${not empty transfer_message}">
            <div class="alert alert-success">
                    ${transfer_message}
            </div>
        </c:if>
        <c:if test="${not empty user}">
            <h2>${user.login}</h2>
            <p>Телефон</p>
            <p>Email</p>
        </c:if>
    </jsp:body>
</t:default>