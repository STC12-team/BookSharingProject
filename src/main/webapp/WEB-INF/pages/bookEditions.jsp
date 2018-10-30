<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:default title="Букшеринг">
    <jsp:body>
        <table class="table">
            <TR>
                <TD>ID</TD>
                <TD>TITLE</TD>
                <TD>DESCRIPTION</TD>
                <TD>ISBN</TD>
            </TR>
            <c:forEach var="bookEdition" items="${bookEditions}">

                <TR>
                    <TD>${bookEdition.id}</TD>
                    <TD>${bookEdition.title}</TD>
                    <TD>${bookEdition.description}</TD>
                    <TD>${bookEdition.isbn}</TD>
                </TR>
            </c:forEach>
        </table>
    </jsp:body>
</t:default>