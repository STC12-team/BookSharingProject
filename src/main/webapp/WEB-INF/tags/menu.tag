<%@tag description="Menu skeleton" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark mb-4">
    <div class="container-fluid">
        <%--Название проекта--%>
        <a class="navbar-brand" href="${pageContext.request.contextPath}/library">
            <spring:message code="nav.brand"/>
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>

        <ul class="navbar-nav mr-auto" id="collapsibleNavbar">
            <%--Главное меню--%>
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/about">
                        <spring:message code="nav.about"/>
                        <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/library">
                        <spring:message code="nav.library"/>
                        <span class="sr-only">(current)</span></a>
                </li>
                <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/myBooks">
                            <spring:message code="nav.myBooks"/>
                            <span class="sr-only">(current)</span></a>
                    </li>
                </sec:authorize>
            </ul>
        </ul>
            <%--Переключение языка--%>
            <t:language/>
            <%--Форма авторизации, регистрации...--%>
            <t:authorization/>
    </div>
</nav>