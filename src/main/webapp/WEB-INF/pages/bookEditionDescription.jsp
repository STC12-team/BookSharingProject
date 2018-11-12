<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:default title="Описание книги">
    <jsp:body>
        <t:bookdescription bookEdition="${bookEdition}"
                           countBookCopy="${countBookCopy}"
                           countBookCopyInStatusFree="${countBookCopyInStatusFree}"
                           userCountInQueue="${userCountInQueue}"
        />
    </jsp:body>
</t:default>