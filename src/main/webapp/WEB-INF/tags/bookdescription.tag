<%@tag description="Book list skeleton" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@attribute name="bookEdition" required="true" rtexprvalue="true"
             type="ru.innopolis.stc12.booksharing.model.dao.entity.BookEdition" %>
<%@attribute name="countBookCopy" required="true" rtexprvalue="true" type="java.lang.Integer" %>
<%@attribute name="countBookCopyInStatusFree" required="true" rtexprvalue="true" type="java.lang.Integer" %>
<%@attribute name="userCountInQueue" required="true" rtexprvalue="true" type="java.lang.Integer" %>
<%@attribute name="placeInQueue" required="true" rtexprvalue="true" type="java.lang.Integer" %>

<%--Book edition info--%>
<div class="card mb-3">
    <div class="row">
        <div class="col-sm-3">
            <img class="card-img-left" src="/images/no_cover_max.png" alt="${bookEdition.title}">
        </div>
        <div class="col-sm-9 px-3">
            <div class="card-block px-3">
                <h4 class="card-title">${bookEdition.title}</h4>
                <p class="card-text"><spring:message code="bookDesc.isbn"/>: ${bookEdition.isbn}</p>
                <p class="card-text"><spring:message code="bookDesc.publisher"/>: ${bookEdition.publisher.name}</p>
                <p class="card-text"><spring:message code="bookDesc.year"/>: ${bookEdition.yearOfPublication}</p>
                <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER')">
                    <c:choose>
                        <c:when test="${placeInQueue == 0}">
                            <a href="#" class="btn btn-primary"><spring:message code="bookDesc.getInQueue"/></a>
                        </c:when>
                        <c:when test="${countBookCopyInStatusFree >= placeInQueue}">
                            <a href="#" class="btn btn-primary"><spring:message code="bookDesc.take"/></a>
                        </c:when>
                        <c:otherwise>
                            <div class="alert alert-primary" role="alert" style="width: 300px">
                                <spring:message code="bookDesc.placeInQueue"/> - ${placeInQueue}
                            </div>
                        </c:otherwise>
                    </c:choose>
                </sec:authorize>
            </div>
        </div>
    </div>
    <div class="card-footer">
        <div class="row">
            <div class="col-sm-4">
                <p class="card-text"><spring:message code="bookDesc.copies"/> - ${countBookCopy}</p>
            </div>
            <div class="col-sm-4">
                <p class="card-text"><spring:message code="bookDesc.freeBooks"/> - ${countBookCopyInStatusFree}</p>
            </div>
            <div class="col-sm-4">
                <p class="card-text"><spring:message code="bookDesc.manInQueue"/> - ${userCountInQueue}</p>
            </div>
        </div>
    </div>
</div>
<%--Book edition description--%>
<div class="card bg-light mb-3">
    <div class="card-header"><spring:message code="bookDesc.description"/></div>
    <div class="card-body">
        <p class="card-text">${bookEdition.description}</p>
    </div>
</div>
<%--Book edition review--%>
<div class="card bg-light mb-3">
    <div class="card-header"><spring:message code="bookDesc.reviews"/></div>
    <div class="card-body">
        <p class="card-text"><spring:message code="message.noReviews"/>.</p>
    </div>
</div>

