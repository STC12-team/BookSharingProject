<%@tag description="Authorization skeleton" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="nav navbar-nav ml-auto">
    <li class="dropdown order-1">
        <button type="button" class="btn btn-primary btn-sm dropdown-toggle"
                data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="false">
            Войти
        </button>
        <ul class="dropdown-menu dropdown-menu-right mt-2" style="width: 300px">
            <li class="px-3 py-2">
                <form class="form" role="form">
                    <div class="form-group">
                        <label for="userName">Логин</label>
                        <input type="text" class="form-control" id="userName"
                               placeholder="email@example.com">
                    </div>
                    <div class="form-group">
                        <label for="userPassword">Пароль</label>
                        <input type="password" class="form-control" id="userPassword"
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