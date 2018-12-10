<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:default title="Каталог Изданий Книг">
    <jsp:body>
        <table class="table">
            <TR>
                <TD>ID</TD>
                <TD>TITLE</TD>
                <TD>DESCRIPTION</TD>
                <TD>ISBN</TD>
            </TR>
            <c:forEach var="book" items="${bookEditions}">

                <TR>
                    <TD>${book.id}</TD>
                    <TD>${book.title}</TD>
                    <TD>${book.description}</TD>
                    <TD>${book.isbn}</TD>
                </TR>
            </c:forEach>
        </table>
    </jsp:body>
</t:default>