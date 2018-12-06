<%@tag description="Book list skeleton" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="takenBooks" required="true" rtexprvalue="true" type="java.util.List" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<form action="/takenBooks/readEnd" method="get" role="presentation">
    <div class="row">
        <c:forEach var="book" items="${takenBooks}">
            <div class="col-md-6">
                <h2>${book.bookCopy.bookEdition.title}</h2>
                <p><spring:message code="bookList.isbnTitle"/>: ${book.bookCopy.bookEdition.isbn}</p>
                <p><spring:message code="bookList.publisherTitle"/>: ${book.bookCopy.bookEdition.publisher.name}</p>
                <p><spring:message code="bookList.publishYearTitle"/>: ${book.bookCopy.bookEdition.yearOfPublication}</p>
                <p><spring:message code="bookList.descriptionTitle"/>: ${book.bookCopy.bookEdition.description}</p>
                <p><spring:message code="bookList.readStartDate"/>: ${book.getAt}</p>
                <c:choose>
                    <c:when test="${empty book.gaveAt}">
                        <c:if test="${book.bookCopy.status == 'BUSY'}">
                            <button type="submit" name="bookCopyId" value="${book.bookCopy.id}" class="btn btn-info">
                                <span><spring:message code="book.statusFinished"/></span>
                            </button>
                        </c:if>
                        <c:if test="${book.bookCopy.status == 'FREE'}">
                            <button type="submit" name="handed" value="${book.bookCopy.id}" class="btn btn-info">
                                <span><spring:message code="book.statusShare"/></span>
                            </button>
                        </c:if>
                    </c:when>
                    <c:otherwise>
                        <p><spring:message code="bookList.readFinishDate"/>: ${book.gaveAt}</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:forEach>
    </div>
</form>