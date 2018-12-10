<%@tag description="Book list skeleton" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@attribute name="bookQueues" required="true" rtexprvalue="true" type="java.util.List" %>

<%--Book list--%>
<c:choose>
    <c:when test="${not empty bookQueues}">
        <div class="card-columns text-center">
            <c:forEach var="book" items="${bookQueues}">
                <div class="card">
                    <a href="/bookQueueDesc/${book.id}">
                        <img class="card-img-top" src="/images/no_cover.png"
                             alt="${book.bookEdition.title}">
                    </a>
                    <div class="card-body text-truncate" data-toogle="tooltip" data-placement="top"
                         title="${book.bookEdition.title}">
                        <a href="/bookQueueDesc/${book.id}"
                           class="card-link">${book.bookEdition.title}</a>
                    </div>
                    <div class="card-body">
                        <p>
                            <c:choose>
                                <c:when test="${book.status == 'WAIT'}">
                                    <span class="badge badge-info"><spring:message code="queue.statusWait"/></span>
                                </c:when>
                                <c:when test="${book.status == 'GETTING'}">
                                    <span class="badge badge-info"><spring:message code="queue.statusGetting"/></span>
                                </c:when>
                                <c:when test="${book.status == 'DELAY'}">
                                    <span class="badge badge-info"><spring:message code="queue.statusDelay"/></span>
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