<%@tag description="User details template" pageEncoding="UTF-8" %>
<%@attribute name="userDetails" required="true" type="ru.innopolis.stc12.booksharing.model.dao.entity.UserDetails" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <div class="col">
        <form action="#" method="get">
            <button id="userEdit" type="button" data-toggle="modal" data-target="#confirm-submit" class="btn btn-info float-right"><spring:message code="form.editButton"/></button>
        </form>
    </div>
</div>

<div class="row">
    <div class="col">
        <c:if test="${not empty userDetails.userPicUrl}">
            <img src="${userDetails.userPicUrl}" class="img-fluid" alt="">
        </c:if>
        <c:if test="${empty userDetails.userPicUrl}">
            <img src="https://via.placeholder.com/250" class="img-fluid" alt="">
        </c:if>
    </div>
    <div class="col">
        <h3 class="display-4">${userDetails.firstName} ${userDetails.lastName} ${userDetails.surname}</h3>
        <p><span class="text-muted">Email:</span> ${userDetails.getUser().email}</p>
    </div>
</div>

<div class="modal fade" id="confirm-submit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <spring:message code="form.passwordConfirm"/>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/userConfirmation" method="POST" id="password__confirmation__form">
                    <input type="password" name="password__confirmation" class="form-control" id="password__confirmation"
                           placeholder="<spring:message code="form.passwordInput"/>" required>
                </form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="form.cancelButton"/></button>
                <a href="#" id="submit" class="btn btn-success success"><spring:message code="form.confirmButton"/></a>
            </div>
        </div>
    </div>
</div>