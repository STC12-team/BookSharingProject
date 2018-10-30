<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:default title="Каталог">
    <jsp:body>
        <h1>Вы в каталоге ${pageContext.request.userPrincipal.name} </h1>
        <p class="lead">${user}</p>

        <table class="table">
            <TR>
                <TD>ID</TD>
                <TD>TITLE</TD>
                <TD>PUBLISHER</TD>
                <TD>YEAR OF PUBLICATION</TD>
                <TD>DESCRIPTION</TD>
                <TD>ISBN</TD>
                <TD>OWNER</TD>
            </TR>
            <c:forEach var="bookCopy" items="${bookCopies}">

                <TR>
                    <TD>${bookCopy.id}</TD>
                    <TD>${bookCopy.bookEdition.title}</TD>
                    <TD>${bookCopy.bookEdition.publisher.name}</TD>
                    <TD>${bookCopy.bookEdition.yearOfPublication}</TD>
                    <TD>${bookCopy.bookEdition.description}</TD>
                    <TD>${bookCopy.bookEdition.isbn}</TD>
                    <TD>${bookCopy.user.login}</TD>
                </TR>
            </c:forEach>
        </table>
    </jsp:body>
</t:default>