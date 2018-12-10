<%@tag description="Book holder skeleton" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@attribute name="bookQueue" required="true" rtexprvalue="true"
             type="ru.innopolis.stc12.booksharing.model.dao.entity.BookQueue" %>

<%--Book copy info--%>
<div class="card mb-3">
    <div class="row">
        <div class="col-sm-3">
            <img class="card-img-left" src="/images/no_cover_max.png"
                 alt="${bookQueue.bookEdition.title}">
        </div>
        <div class="col-sm-9 px-3">
            <div class="card-block px-3">
                <h4 class="card-title">${bookQueue.bookEdition.title}</h4>
                <p class="card-text">
                    <spring:message code="bookList.isbnTitle"/>: ${bookQueue.bookEdition.isbn}
                </p>
                <p class="card-text">
                    <spring:message code="bookList.publisherTitle"/>: ${bookQueue.bookEdition.publisher.name}
                </p>
                <p class="card-text">
                    <spring:message code="bookList.publishYearTitle"/>: ${bookQueue.bookEdition.yearOfPublication}
                </p>
                <p class="card-text">
                    <spring:message code="bookDesc.getInQueue"/>: ${bookQueue.addedAt}
                </p>
                <p class="card-text badge badge-info">
                    <c:if test="${bookQueue.status == 'WAIT'}">
                        <spring:message code="queue.statusWait"/>
                    </c:if>
                    <c:if test="${bookQueue.status == 'GETTING'}">
                        <spring:message code="queue.statusGetting"/>
                    </c:if>
                    <c:if test="${bookQueue.status == 'DELAY'}">
                        <spring:message code="queue.statusDelay"/>
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
        <p class="card-text">${bookQueue.bookEdition.description}</p>
    </div>
</div>