<%@tag description="Book list skeleton" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="takenBooks" required="true" rtexprvalue="true" type="java.util.List" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--Book list--%>
<c:choose>
    <c:when test="${not empty takenBooks}">
        <div class="card-columns text-center">
            <c:forEach var="book" items="${takenBooks}">
                <div class="card">
                    <a href="/bookHolderDesc/${book.id}">
                        <img class="card-img-top" src="/images/no_cover.png"
                             alt="${book.bookCopy.bookEdition.title}">
                    </a>
                    <div class="card-body text-truncate" data-toogle="tooltip" data-placement="top"
                         title="${book.bookCopy.bookEdition.title}">
                        <a href="/bookHolderDesc/${book.id}"
                           class="card-link">${book.bookCopy.bookEdition.title}</a>
                    </div>
                    <div class="card-body">
                        <p>
                            <c:choose>
                                <c:when test="${empty book.gaveAt}">
                                    <span class="badge badge-info"><spring:message code="book.statusRead"/></span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge badge-info"><spring:message code="book.statusReadEnd"/></span>
                                </c:otherwise>
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
