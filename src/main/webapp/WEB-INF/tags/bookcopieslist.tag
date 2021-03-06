<%@tag description="Book list skeleton" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@attribute name="bookCopies" required="true" rtexprvalue="true" type="java.util.List" %>

<%--Book list--%>
<c:choose>
    <c:when test="${not empty bookCopies}">
        <div class="card-columns text-center">
            <c:forEach var="book" items="${bookCopies}">
                <div class="card">
                    <a href="/bookCopyDesc/${book.id}">
                        <img class="card-img-top" src="/images/no_cover.png"
                             alt="${book.bookEdition.title}">
                    </a>
                    <div class="card-body text-truncate" data-toogle="tooltip" data-placement="top"
                         title="${book.bookEdition.title}">
                        <a href="/bookCopyDesc/${book.id}"
                           class="card-link">${book.bookEdition.title}</a>
                    </div>
                    <div class="card-body">
                        <p>
                            <c:choose>
                                <c:when test="${book.status == 'BUSY'}">
                                    <span class="badge badge-info"><spring:message code="book.statusBusy"/></span>
                                </c:when>
                                <c:when test="${book.status == 'FREE'}">
                                    <span class="badge badge-info"><spring:message code="book.statusFree"/></span>
                                </c:when>
                            </c:choose>
                        </p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:when>
    <c:otherwise>
        <div class="alert alert-warning" role="alert">
            <spring:message code="message.noBooksFound"/>
        </div>
    </c:otherwise>
</c:choose>