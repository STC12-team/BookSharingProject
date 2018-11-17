<%@tag description="Authorization skeleton" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<ul class="nav navbar-nav ml-auto">
    <li class="dropdown order-1">
        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
            <spring:message code="nav.lang"/>
        </a>
        <ul class="dropdown-menu dropdown-menu-right mt-2">
            <li class="nav-link mr-sm-2">
                <a class="lang__link-en active" href="${pageContext.request.contextPath}?lang=en">English</a>
            </li>
            <li class="nav-link mr-sm-2">
                <a class="lang__link-ru active" href="${pageContext.request.contextPath}?lang=ru">Русский</a>
            </li>
        </ul>
    </li>
</ul>