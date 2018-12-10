<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:default title="Взятые книги">
    <jsp:body>
        <div class="container-fluid">
            <div class="row">
                    <%--Menu for personal information--%>
                <div class="col-sm-3">
                    <h2><spring:message code="personal.menu"/></h2>
                    <div class="list-group">
                        <a href="${pageContext.request.contextPath}/myBooks"
                           class="list-group-item list-group-item-action active">
                            <spring:message code="personal.menuItemMyBooks"/></a>
                        <a href="${pageContext.request.contextPath}/takenBooks"
                           class="list-group-item list-group-item-action">
                            <spring:message code="personal.menuItemReadBooks"/></a>
                        <a href="${pageContext.request.contextPath}/queueBooks"
                           class="list-group-item list-group-item-action">
                            <spring:message code="personal.menuItemQueueBooks"/></a>
                    </div>
                </div>

                <div class="col-sm-9">
                        <%--Book list--%>
                    <c:if test="${not empty takenBooksList}">
                        <t:bookholderslist takenBooks="${takenBooksList}"/>
                    </c:if>

                    <c:if test="${not empty bookCopyList}">
                        <t:bookcopieslist bookCopies="${bookCopyList}"/>
                    </c:if>

                    <c:if test="${not empty bookQueueList}">
                        <t:bookqueuelist bookQueues="${bookQueueList}"/>
                    </c:if>

                    <c:if test="${not empty message}">
                        <div class="alert alert-success">
                                ${message}
                        </div>
                    </c:if>

                    <c:if test="${not empty transfer_message}">
                        <div class="alert alert-success">
                                ${transfer_message}
                        </div>
                    </c:if>

                    <c:if test="${not empty user}">
                        <t:userCard user="${user}"/>
                    </c:if>

                </div>
            </div>
        </div>

    </jsp:body>
</t:default>