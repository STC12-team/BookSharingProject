<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:default title="Авторизация">
    <jsp:body>
        <form class="form-horizontal" role="form" method="POST" action="/login">
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <h2>Вход для зарегистрированных пользователей</h2>
                    <hr>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <div class="form-group has-danger">
                        <label class="sr-only" for="login">Login</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon" style="width: 2.6rem"><span class="fa fa-at"></span></div>
                            <input type="text" name="login" class="form-control" id="login"
                                   placeholder="Введите имя пользователя" required autofocus>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-control-feedback">
                        <span class="text-danger align-middle">
                                ${loginError}
                        </span>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="sr-only" for="password">Password</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon" style="width: 2.6rem"><span class="fa fa-key"></span></div>
                            <input type="password" name="password" class="form-control" id="password"
                                   placeholder="Введите пароль" required>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-control-feedback">
                        <span class="text-danger align-middle">
                        <!-- password error message here -->
                        </span>
                    </div>
                </div>
            </div>
            <div class="row" style="padding-top: 1rem">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <button type="submit" class="btn btn-success"><span class="fa fa-sign-in"></span>Войти</button>
                </div>
            </div>
        </form>
    </jsp:body>
</t:default>