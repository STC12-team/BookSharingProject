<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:default title="Библиотека">

    <jsp:body>
        <t:search/>
        <div class="container-fluid">
            <div class="row">
                    <%--Категории--%>
                <div class="col-sm-3">
                    <h2><spring:message code="cat.categories"/></h2>
                    <div class="list-group">
                        <a href="#" class="list-group-item list-group-item-action active"><spring:message
                                code="cat.ScienceEducation"/></a>
                        <a href="#" class="list-group-item list-group-item-action"><spring:message
                                code="cat.classic"/></a>
                        <a href="#" class="list-group-item list-group-item-action"><spring:message
                                code="cat.adventure"/></a>
                        <a href="#" class="list-group-item list-group-item-action"><spring:message
                                code="cat.prose"/></a>
                        <a href="#" class="list-group-item list-group-item-action"><spring:message
                                code="cat.referenceBooks"/></a>
                        <a href="#" class="list-group-item list-group-item-action"><spring:message
                                code="cat.thrillers"/></a>
                        <a href="#" class="list-group-item list-group-item-action"><spring:message
                                code="cat.horrors"/></a>
                        <a href="#" class="list-group-item list-group-item-action"><spring:message
                                code="cat.fantasy"/></a>
                        <a href="#" class="list-group-item list-group-item-action"><spring:message
                                code="cat.philosophy"/></a>
                    </div>
                </div>

                <div class="col-sm-9">
                        <%--Фильтры--%>
                    <div class="btn-toolbar" role="toolbar" aria-label="Toolbar">
                        <div class="btn-group mr-2 mb-4" role="group" aria-label="Sort group">
                            <button type="button" id="sortGroup" class="btn btn-primary btn-sm dropdown-toggle"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <spring:message code="filter.sorting"/>
                            </button>
                            <div class="dropdown-menu" aria-labelledby="sortGroup">
                                <a class="dropdown-item" href="#"><spring:message code="filter.title"/></a>
                                <a class="dropdown-item" href="#"><spring:message code="filter.author"/></a>
                                <a class="dropdown-item" href="#"><spring:message code="filter.dateAdded"/></a>
                            </div>
                        </div>
                        <div class="btn-group mr-2 mb-4" role="group" aria-label="View group">
                            <button type="button" id="viewGroup" class="btn btn-primary btn-sm dropdown-toggle"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <spring:message code="filter.display"/>
                            </button>
                            <div class="dropdown-menu" aria-labelledby="viewGroup">
                                <a class="dropdown-item" href="#"><spring:message code="filter.allBooks"/></a>
                                <a class="dropdown-item" href="#"><spring:message code="filter.free"/></a>
                            </div>
                        </div>
                        <div class="btn-group mr-2 mb-4" role="group" aria-label="Best book group">
                            <button type="button" class="btn btn-primary btn-sm"><spring:message
                                    code="filter.topBooks"/></button>
                        </div>
                        <div class="btn-group mr-2 mb-4" role="group" aria-label="Best book group">
                            <button type="button" class="btn btn-primary btn-sm"><spring:message
                                    code="filter.newArrivals"/></button>
                        </div>
                    </div>
                        <%--Список книг--%>
                    <c:choose>
                        <c:when test="${not empty bookEditionList}">
                            <div class="card-columns text-center">
                                <c:forEach var="bookEdition" items="${bookEditionList}">
                                    <div class="card">
                                        <a href="/bookEditionDesc/${bookEdition.id}">
                                            <img class="card-img-top" src="/images/no_cover.png"
                                                 alt="${bookEdition.title}">
                                        </a>
                                        <div class="card-body text-truncate" data-toogle="tooltip" data-placement="top"
                                             title="${bookEdition.title}">
                                            <a href="/bookEditionDesc/${bookEdition.id}"
                                               class="card-link">${bookEdition.title}</a>
                                        </div>
                                        <div class="card-body">
                                            <p>
                                                <span class="badge badge-info"><spring:message code="library.all"/>: <c:out value="${bookEditionsAll * bookEdition.id}"/></span>
                                                <span class="badge badge-info"><spring:message code="library.free"/>: <c:out value="${bookEditionsFree * bookEdition.id}"/></span>
                                                <span class="badge badge-info"><spring:message code="library.queue"/>: <c:out value="${bookEditionsQueue * bookEdition.id}"/></span>
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
                </div>
            </div>
        </div>
    </jsp:body>
</t:default>