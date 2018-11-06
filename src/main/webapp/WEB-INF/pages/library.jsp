<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:default title="Библиотека">
    <jsp:body>
        <style>
            .btn {
                margin-bottom: 30px;
            }
        </style>
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-3">
                    <h2>Категории</h2>
                    <ul class="nav nav-pills flex-column">
                        <li class="nav-item"><a class="nav-link active" href="#section1">Наука, образование</a></li>
                        <li class="nav-item"><a class="nav-link" href="#section2">Классика</a></li>
                        <li class="nav-item"><a class="nav-link" href="#section3">Приключения</a></li>
                        <li class="nav-item"><a class="nav-link" href="#section3">Проза</a></li>
                        <li class="nav-item"><a class="nav-link" href="#section3">Справочная литература</a></li>
                        <li class="nav-item"><a class="nav-link" href="#section3">Триллеры</a></li>
                        <li class="nav-item"><a class="nav-link" href="#section3">Ужасы</a></li>
                        <li class="nav-item"><a class="nav-link" href="#section3">Фантастика</a></li>
                        <li class="nav-item"><a class="nav-link" href="#section3">Философия</a></li>
                    </ul>
                    <br>
                </div>
                <div class="col-sm-9">
                    <div class="row">
                        <div class="col-sm-4">
                            <div class="btn-group">
                                <button type="button" class="btn btn-primary btn-sm dropdown-toggle"
                                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Сортировка
                                </button>
                                <div class="dropdown-menu">
                                    <a class="dropdown-item" href="#">Название</a>
                                    <a class="dropdown-item" href="#">Автор</a>
                                    <a class="dropdown-item" href="#">Дата добавления</a>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="btn-group">
                                <button type="button" class="btn btn-primary btn-sm dropdown-toggle"
                                        data-toggle="dropdown"
                                        aria-haspopup="true" aria-expanded="false">
                                    Отображение
                                </button>
                                <div class="dropdown-menu">
                                    <a class="dropdown-item" href="#">Все книги</a>
                                    <a class="dropdown-item" href="#">Свободные</a>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <button type="button" class="btn btn-primary btn-sm">Лучшие книги</button>
                        </div>
                    </div>
                    <div class="card-columns">
                        <div class="card">
                            <img class="card-img-top" src="/images/132x185_no_picture.png" alt="Книга">
                            <div class="card-body">
                                <h5 class="card-title">Название книги</h5>
                            </div>
                        </div>
                        <div class="card">
                            <img class="card-img-top" src="/images/132x185_no_picture.png" alt="Книга">
                            <div class="card-body">
                                <h5 class="card-title text-truncate">Название книги Название книги</h5>
                            </div>
                        </div>
                        <div class="card">
                            <img class="card-img-top" src="/images/132x185_no_picture.png" alt="Книга">
                            <div class="card-body">
                                <h5 class="card-title text-truncate">Название книги Название книги Название книги</h5>
                            </div>
                        </div>
                        <div class="card">
                            <img class="card-img-top" src="/images/132x185_no_picture.png" alt="Книга">
                            <div class="card-body">
                                <h5 class="card-title text-truncate">Название книги Название книги Название книги
                                    Название книги</h5>
                            </div>
                        </div>
                        <div class="card">
                            <img class="card-img-top" src="/images/132x185_no_picture.png" alt="Книга">
                            <div class="card-body">
                                <h5 class="card-title text-truncate">Название книги Название книги Название книги
                                    Название книги Название книги</h5>
                            </div>
                        </div>
                        <div class="card">
                            <img class="card-img-top" src="/images/132x185_no_picture.png" alt="Книга">
                            <div class="card-body">
                                <h5 class="card-title text-truncate">Название книги Название книги Название книги
                                    Название книги Название книги Название книги</h5>
                            </div>
                        </div>
                        <div class="card">
                            <img class="card-img-top" src="/images/132x185_no_picture.png" alt="Книга">
                            <div class="card-body">
                                <h5 class="card-text text-truncate">Название книги Название книги Название книги
                                    Название книги Название книги Название книги Название книги</h5>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:default>