<%@tag description="Book list skeleton" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="bookEditionList" required="true" rtexprvalue="true" type="java.util.List" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<form action="/addBookByUser/chooseBook" method="get" role="presentation">
    <div class="row">
        <c:forEach var="bookEdition" items="${bookEditionList}">
            <div class="col-md-6">
                <h2>${bookEdition.title}</h2>
                <p><spring:message code="bookList.isbnTitle"/>: ${bookEdition.isbn}</p>
                <p><spring:message code="bookList.publisherTitle"/>: ${bookEdition.publisher.name}</p>
                <p><spring:message code="bookList.publishYearTitle"/>: ${bookEdition.yearOfPublication}</p>
                <p><spring:message code="bookList.descriptionTitle"/>: ${bookEdition.description}</p>
                <button type="submit" name="chooseBook" value="${bookEdition.isbn}" class="btn btn-info">
                    <span><spring:message code="bookList.chooseButton"/></span>
                </button>
            </div>
        </c:forEach>
    </div>
</form>