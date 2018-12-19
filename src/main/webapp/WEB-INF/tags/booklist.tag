<%@tag description="Book list skeleton" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="bookEditionList" required="true" rtexprvalue="true" type="java.util.List" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<form action="/addBookByUser/addBook" method="get" role="presentation">
    <div class="row">
        <c:forEach var="book" items="${bookEditionList}">
            <div class="col-md-6">
                <h2>${book.title}</h2>
                <p><spring:message code="bookList.isbnTitle"/>: ${book.isbn}</p>
                <p><spring:message code="bookList.publisherTitle"/>: ${book.publisher.name}</p>
                <p><spring:message code="bookList.publishYearTitle"/>: ${book.yearOfPublication}</p>
                <p><spring:message code="bookList.descriptionTitle"/>: ${book.description}</p>
                <button type="submit" name="addBook" value="${book.isbn}" class="btn btn-info">
                    <span><spring:message code="bookList.chooseButton"/></span>
                </button>
            </div>
        </c:forEach>
    </div>
</form>