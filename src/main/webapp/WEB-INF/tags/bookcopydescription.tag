<%@tag description="Book holder skeleton" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@attribute name="bookCopy" required="true" rtexprvalue="true"
             type="ru.innopolis.stc12.booksharing.model.dao.entity.BookCopy" %>

<%--Book copy info--%>
<div class="card mb-3">
    <div class="row">
        <div class="col-sm-3">
            <img class="card-img-left" src="/images/no_cover_max.png"
                 alt="${bookCopy.bookEdition.title}">
        </div>
        <div class="col-sm-9 px-3">
            <div class="card-block px-3">
                <h4 class="card-title">${bookCopy.bookEdition.title}</h4>
                <p class="card-text"><spring:message
                        code="bookList.isbnTitle"/>: ${bookCopy.bookEdition.isbn}</p>
                <p class="card-text"><spring:message
                        code="bookList.publisherTitle"/>: ${bookCopy.bookEdition.publisher.name}</p>
                <p class="card-text"><spring:message
                        code="bookList.publishYearTitle"/>: ${bookCopy.bookEdition.yearOfPublication}</p>
                <p class="card-text badge badge-info">
                    <c:if test="${bookCopy.status == 'BUSY'}">
                        <spring:message code="book.statusBusy"/>
                    </c:if>
                    <c:if test="${bookCopy.status == 'FREE'}">
                        <spring:message code="book.statusFree"/>
                    </c:if>
                </p>
            </div>
        </div>
    </div>
</div>
<%--Book edition description--%>
<div class="card bg-light mb-3">
    <div class="card-header"><spring:message code="bookDesc.description"/></div>
    <div class="card-body">
        <p class="card-text">${bookCopy.bookEdition.description}</p>
    </div>
</div>