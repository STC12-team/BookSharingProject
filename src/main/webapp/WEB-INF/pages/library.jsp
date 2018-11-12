<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:default title="Библиотека">

    <jsp:body>
        <t:search/>
        <div class="container-fluid">
            <div class="row">
                    <%--Категории--%>
                <div class="col-sm-3">
                    <h2>Категории</h2>
                    <div class="list-group">
                        <a href="#" class="list-group-item list-group-item-action active">Наука, образование</a>
                        <a href="#" class="list-group-item list-group-item-action">Классика</a>
                        <a href="#" class="list-group-item list-group-item-action">Приключения</a>
                        <a href="#" class="list-group-item list-group-item-action">Проза</a>
                        <a href="#" class="list-group-item list-group-item-action disabled">Справочная литература</a>
                        <a href="#" class="list-group-item list-group-item-action">Триллеры</a>
                        <a href="#" class="list-group-item list-group-item-action">Ужасы</a>
                        <a href="#" class="list-group-item list-group-item-action">Фантастика</a>
                        <a href="#" class="list-group-item list-group-item-action">Философия</a>
                    </div>
                </div>

                <div class="col-sm-9">
                        <%--Фильтры--%>
                    <div class="btn-toolbar" role="toolbar" aria-label="Toolbar">
                        <div class="btn-group mr-2 mb-4" role="group" aria-label="Sort group">
                            <button type="button" id="sortGroup" class="btn btn-primary btn-sm dropdown-toggle"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Сортировка
                            </button>
                            <div class="dropdown-menu" aria-labelledby="sortGroup">
                                <a class="dropdown-item" href="#">Название</a>
                                <a class="dropdown-item" href="#">Автор</a>
                                <a class="dropdown-item" href="#">Дата добавления</a>
                            </div>
                        </div>
                        <div class="btn-group mr-2 mb-4" role="group" aria-label="View group">
                            <button type="button" id="viewGroup" class="btn btn-primary btn-sm dropdown-toggle"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Отображение
                            </button>
                            <div class="dropdown-menu" aria-labelledby="viewGroup">
                                <a class="dropdown-item" href="#">Все книги</a>
                                <a class="dropdown-item" href="#">Свободные</a>
                            </div>
                        </div>
                        <div class="btn-group mr-2 mb-4" role="group" aria-label="Best book group">
                            <button type="button" class="btn btn-primary btn-sm">Лучшие книги</button>
                        </div>
                        <div class="btn-group mr-2 mb-4" role="group" aria-label="Best book group">
                            <button type="button" class="btn btn-primary btn-sm">Новые поступления</button>
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
                                    </div>
                                </c:forEach>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="alert alert-warning" role="alert">
                                По Вашему запросу книг не найдено!
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </jsp:body>
</t:default>