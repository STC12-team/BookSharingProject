<%@tag description="Default page skeleton" pageEncoding="UTF-8" %>
<%@attribute name="scripts" fragment="true" %>
<%@attribute name="title" required="true" rtexprvalue="true" type="java.lang.String" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta http-equiv=Content-Type content="text/html;charset=UTF-8">
    <title>"${title}"</title>
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<div class="navbar navbar-expand-lg navbar-dark bg-dark">
    <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
            <a class="nav-link" href="/about">О проекте<span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/catalog">Каталог книг</a>
        </li>
        <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER')">
            <li class="nav-item">
                <a class="nav-link" href="/login?logout">Выйти</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/userProfile">Профиль</a>
            </li>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <li class="nav-item">
                <a class="nav-link" href="/bookEditions">Книжные издания</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/addBookEdition">Добавить книжное издание</a>
            </li>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_USER')">
            <li class="nav-item">
                <a class="nav-link" href="/addBookByUser">Добавить книгу пользователя</a>
            </li>
        </sec:authorize>
        <sec:authorize access="!hasRole('ROLE_ADMIN') and !hasRole('ROLE_USER')">
            <li class="nav-item">
                <a class="nav-link" href="/login">Войти</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/register">Зарегистрироваться</a>
            </li>
        </sec:authorize>
    </ul>
</div>
<div id="body">
    <div class="container">
        <div class="wrapper" style="margin-top: 5rem;">
            <jsp:doBody/>
        </div>
    </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.bundle.min.js"
        integrity="sha384-pjaaA8dDz/5BgdFUPX6M/9SUZv4d12SUPF0axWc+VRZkx5xU3daN+lYb49+Ax+Tl"
        crossorigin="anonymous"></script>
<div id="scripts">
    <jsp:invoke fragment="scripts"/>
</div>
</html>