<%@tag description="Authorization skeleton" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<sec:authorize access="isAnonymous()">
    <ul class="nav navbar-nav">
        <li class="dropdown order-1">
            <button type="button" class="btn btn-primary btn-sm dropdown-toggle"
                    data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="false">
                <spring:message code="nav.login"/>
            </button>
            <ul class="dropdown-menu mt-2" style="width: 300px">
                <li class="px-3 py-2">
                    <form class="form" role="form" method="POST"
                          action="${pageContext.request.contextPath}/j_username_security_check">
                        <div class="form-group">
                            <label for="j_username"><spring:message code="nav.loginLabel"/></label>
                            <input type="text" class="form-control" id="j_username" name="j_username"
                                   placeholder="<spring:message code="auth.userField"/>">
                        </div>
                        <div class="form-group">
                            <label for="j_password"><spring:message code="nav.passLabel"/></label>
                            <input type="password" class="form-control" id="j_password" name="j_password"
                                   placeholder="<spring:message code="auth.passwordField"/>">
                        </div>
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input" id="dropdownCheck2">
                            <label class="form-check-label" for="dropdownCheck2">
                                <spring:message code="nav.rememberMe"/>
                            </label>
                            <br>
                        </div>
                        <button type="submit" class="btn btn-primary w-100"><spring:message code="form.loginButton"/></button>
                        <a href="#" class="btn btn-outline-warning btn-sm w-100" style="margin-top: 20px">
                            <spring:message code="nav.forgotPass"/>
                        </a>
                        <a href="${pageContext.request.contextPath}/register"
                           class="btn btn-outline-warning btn-sm w-100" style="margin-top: 5px">
                            <spring:message code="nav.register"/>
                        </a>
                    </form>
                </li>
            </ul>
        </li>
    </ul>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <div class="dropdown">
        <button type="button" class="btn btn-primary btn-sm text-center dropdown-toggle" data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="false">
            <sec:authentication property="principal.username"/>
        </button>
        <div class="dropdown-menu">
            <a class="dropdown-item" href="${pageContext.request.contextPath}/userProfile">
                <spring:message code="nav.profile"/>
            </a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="${pageContext.request.contextPath}/static/j_spring_security_logout">
                <spring:message code="nav.logout"/>
            </a>
        </div>
    </div>
</sec:authorize>
