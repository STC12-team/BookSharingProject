<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:default title="Book sharing">
    <jsp:body>
        <form action="/addBookEditionUrl" method="post">
            <input type="text" name="bookEditionTitle" placeholder="title"><br/>
            <input type="text" name="bookEditionDescription" placeholder="description"><br/>
            <input type="text" name="bookEditionIsbn" placeholder="isbn"><br/>
            <input type="submit">
        </form>
    </jsp:body>
</t:default>