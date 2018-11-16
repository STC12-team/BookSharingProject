<%@tag description="Authorization skeleton" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<ul class="nav navbar-nav">
    <li class="dropdown order-1">
        <button type="button" class="btn btn-primary btn-sm dropdown-toggle"
                data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="false">
            <spring:message code="nav.login"/>
        </button>
        <ul class="dropdown-menu dropdown-menu-right mt-2" style="width: 300px">
            <li class="px-3 py-2">
                <form class="form" role="form">
                    <div class="form-group">
                        <label for="userName">
                            <spring:message code="nav.loginLabel"/></label>
                        <input type="text" class="form-control" id="userName"
                               placeholder="email@example.com">
                    </div>
                    <div class="form-group">
                        <label for="userPassword">
                            <spring:message code="nav.passLabel"/></label>
                        <input type="password" class="form-control" id="userPassword"
                               placeholder="Password">
                    </div>
                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" id="dropdownCheck2">
                        <label class="form-check-label" for="dropdownCheck2">
                            <spring:message code="nav.rememberMe"/>
                        </label>
                        <br>
                    </div>
                    <button type="submit" class="btn btn-primary w-100">
                        <spring:message code="nav.login"/>
                    </button>
                    <button type="submit" class="btn btn-outline-warning btn-sm w-100"
                            style="margin-top: 20px">
                        <spring:message code="nav.forgotPass"/>
                    </button>
                    <button type="submit" class="btn btn-outline-warning btn-sm w-100"
                            style="margin-top: 5px">
                        <spring:message code="nav.register"/>
                    </button>
                </form>
            </li>
        </ul>
    </li>
</ul>