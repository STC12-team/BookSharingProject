<%@tag description="Authorization skeleton" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAnonymous()">
    <ul class="nav navbar-nav ml-auto">
        <li class="dropdown order-1">
            <button type="button" class="btn btn-primary btn-sm dropdown-toggle"
                    data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="false">
                Войти
            </button>
            <ul class="dropdown-menu dropdown-menu-right mt-2" style="width: 300px">
                <li class="px-3 py-2">
                    <form class="form" role="form" method="POST" action="/j_username_security_check">
                        <div class="form-group">
                            <label for="j_username">Логин</label>
                            <input type="text" class="form-control" id="j_username" name="j_username"
                                   placeholder="User">
                        </div>
                        <div class="form-group">
                            <label for="j_password">Пароль</label>
                            <input type="password" class="form-control" id="j_password" name="j_password"
                                   placeholder="Password">
                        </div>
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input" id="dropdownCheck2">
                            <label class="form-check-label" for="dropdownCheck2">
                                Запомнить меня
                            </label>
                            <br>
                        </div>
                        <button type="submit" class="btn btn-primary w-100">Войти</button>
                        <button type="submit" class="btn btn-outline-warning btn-sm w-100"
                                style="margin-top: 20px">Забыл пароль
                        </button>
                        <button type="submit" class="btn btn-outline-warning btn-sm w-100"
                                style="margin-top: 5px">Зарегистрироваться
                        </button>
                    </form>
                </li>
            </ul>
        </li>
    </ul>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <div class="dropdown ml-auto">
        <button type="button" class="btn btn-primary btn-sm text-center dropdown-toggle" data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="false">
            <sec:authentication property="principal.username"/>
        </button>
        <div class="dropdown-menu">
            <a class="dropdown-item" href="#">Профиль</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="/static/j_spring_security_logout">Выйти</a>
        </div>
    </div>
</sec:authorize>
