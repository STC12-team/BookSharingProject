<%@tag description="Book list skeleton" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@attribute name="bookCopies" required="true" rtexprvalue="true" type="java.util.List" %>
<form action="/catalog" method="get" role="presentation">
    <div class="row">
        <c:forEach var="bookCopy" items="${bookCopies}">
            <div class="col-md-6">
                <h2>${bookCopy.bookEdition.title}</h2>
                <p><spring:message code="bookList.isbnTitle"/>: ${bookCopy.bookEdition.isbn}</p>
                <p><spring:message code="bookList.publisherTitle"/>: ${bookCopy.bookEdition.publisher.name}</p>
                <p><spring:message code="bookList.publishYearTitle"/>: ${bookCopy.bookEdition.yearOfPublication}</p>
                <p><spring:message code="bookList.descriptionTitle"/>: ${bookCopy.bookEdition.description}</p>
                <button type="submit" name="chooseBook" value="${bookCopy.id}" class="btn btn-info">
                    <span><spring:message code="bookList.chooseButton"/></span>
                </button>
            </div>
        </c:forEach>
    </div>
</form>