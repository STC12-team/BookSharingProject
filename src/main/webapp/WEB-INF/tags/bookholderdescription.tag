<%@tag description="Book holder skeleton" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@attribute name="bookHolder" required="true" rtexprvalue="true"
             type="ru.innopolis.stc12.booksharing.model.dao.entity.BookHolder" %>

<%--Book holder info--%>
<form action="${pageContext.request.contextPath}/takenBooks/readEnd" method="get" role="presentation">
    <div class="card mb-3">
        <div class="row">
            <div class="col-sm-3">
                <img class="card-img-left" src="/images/no_cover_max.png"
                     alt="${bookHolder.bookCopy.bookEdition.title}">
            </div>
            <div class="col-sm-9 px-3">
                <div class="card-block px-3">
                    <h4 class="card-title">${bookHolder.bookCopy.bookEdition.title}</h4>
                    <p class="card-text"><spring:message
                            code="bookList.isbnTitle"/>: ${bookHolder.bookCopy.bookEdition.isbn}</p>
                    <p class="card-text"><spring:message
                            code="bookList.publisherTitle"/>: ${bookHolder.bookCopy.bookEdition.publisher.name}</p>
                    <p class="card-text"><spring:message
                            code="bookList.publishYearTitle"/>: ${bookHolder.bookCopy.bookEdition.yearOfPublication}</p>
                    <p class="card-text"><spring:message code="bookList.readStartDate"/>: ${bookHolder.getAt}</p>
                    <c:choose>
                        <c:when test="${empty bookHolder.gaveAt}">
                            <c:if test="${bookHolder.bookCopy.status == 'BUSY'}">
                                <button type="submit" name="bookCopyId" value="${bookHolder.bookCopy.id}"
                                        class="btn btn-info">
                                    <span><spring:message code="book.statusFinished"/></span>
                                </button>
                            </c:if>
                            <c:if test="${bookHolder.bookCopy.status == 'FREE'}">
                                <button type="submit" name="bookCopyId" value="${bookHolder.bookCopy.id}"
                                        class="btn btn-info">
                                    <span><spring:message code="book.statusShare"/></span>
                                </button>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <p><spring:message code="bookList.readFinishDate"/>: ${bookHolder.gaveAt}</p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</form>
<%--Book edition description--%>
<div class="card bg-light mb-3">
    <div class="card-header"><spring:message code="bookDesc.description"/></div>
    <div class="card-body">
        <p class="card-text">${bookHolder.bookCopy.bookEdition.description}</p>
    </div>
</div>