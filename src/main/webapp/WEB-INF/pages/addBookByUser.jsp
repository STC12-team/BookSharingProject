<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:default title="Book sharing">
    <jsp:body>
        <form action="/addBookByUser/searchBook" method="get" id="searchBooksForm" role="form">
            <div class="row">
                <div class="form-group col-md-11">
                    <input type="text" name="searchValue" id="searchValue" class="form-control" required="true"
                           placeholder="<spring:message code="form.isbn"/>"/>
                </div>
                <div class="col-md-1">
                    <button type="submit" class="btn btn-info">
                        <span class="glyphicon glyphicon-search"></span> <spring:message code="form.searchButton"/>
                    </button>

                </div>
            </div>
        </form>
        <c:if test="${not empty message}">
            <div class="alert alert-success">
                    ${message}
            </div>
        </c:if>
        <c:choose>
            <c:when test="${not empty bookEditionList}">
                <t:booklist bookEditionList="${bookEditionList}"/>
            </c:when>
            <c:when test="${not empty bookEdition}">
                <t:bookdescription bookEdition="${bookEdition}"/>
            </c:when>
            <c:when test="${not empty showSendRequestForm && showSendRequestForm == 'true'}">
                <form action="/addBookByUser/sendRequest" method="post" role="form" data-toggle="validator">
                    <h2><spring:message code="addBookRequest.send"/></h2>
                    <div class="form-group col-xs-4">
                        <label for="bookName" class="control-label col-xs-4"><spring:message code="bookRequest.title"/>:</label>
                        <input type="text" name="bookName" id="bookName" class="form-control" required="true"/>
                        <label for="bookAuthor" class="control-label col-xs-4"><spring:message code="bookRequest.author"/>:</label>
                        <input type="text" name="bookAuthor" id="bookAuthor" class="form-control" required="true"/>
                        <label for="bookIsbn" class="control-label col-xs-4">ISBN:</label>
                        <input type="text" name="bookIsbn" id="bookIsbn" class="form-control" required="true"/>
                        <br>
                        <br>
                        <button type="submit" class="btn btn-primary btn-md">Отправить</button>
                    </div>
                </form>
            </c:when>
        </c:choose>
    </jsp:body>
</t:default>