<%@tag description="Menu skeleton" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container-fluid">
        <%--Название проекта--%>
        <a class="navbar-brand" href="/library">BookSharing</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <%--Главное меню--%>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/about">О проекте<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/library">Каталог книг<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Мои книги<span class="sr-only">(current)</span></a>
                </li>
            </ul>
            <%--Форма авторизации, регистрации...--%>
            <t:authorization/>
        </div>
    </div>
</nav>
<%--
<div class="navbar navbar-expand-lg navbar-dark bg-dark">
    <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
            <a class="nav-link" href="/about">О проекте<span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/userProfile">Профиль пользователя</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/addBookByUser">Добавить книгу пользователя</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/catalog">Каталог книг</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/bookEditions">Книжные издания</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/addBookEdition">Добавить книжное издание</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/takenBooks">Взятые книги</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/login">Войти<span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/register">Зарегистрироваться</a>
        </li>
    </ul>
</div>
--%>