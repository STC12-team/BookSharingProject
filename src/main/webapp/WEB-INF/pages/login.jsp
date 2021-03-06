<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:default title="Авторизация">
    <jsp:body>
        <form class="form-horizontal" role="form" method="POST" action="/j_username_security_check">
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <h2><spring:message code="form.loginTitle"/></h2>
                    <hr>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <div class="form-group has-danger">
                        <label class="sr-only" for="j_username"><spring:message code="form.loginUserNameLabel"/></label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon" style="width: 2.6rem"><span class="fa fa-at"></span></div>
                            <input type="text" name="j_username" class="form-control" id="j_username"
                                   placeholder="<spring:message code="form.loginUserName"/>" required autofocus>
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
                        <label class="sr-only" for="j_password"><spring:message code="form.loginPasswordLabel"/></label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon" style="width: 2.6rem"><span class="fa fa-key"></span></div>
                            <input type="password" name="j_password" class="form-control" id="j_password"
                                   placeholder="<spring:message code="form.loginPassword"/>" required>
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
                    <button type="submit" class="btn btn-success" value="Login"><span class="fa fa-sign-in"></span>
                        <spring:message code="form.loginButton"/>
                    </button>
                </div>
            </div>
        </form>
    </jsp:body>
</t:default>