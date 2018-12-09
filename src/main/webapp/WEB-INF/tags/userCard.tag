<%@tag description="Book holder skeleton" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@attribute name="user" required="true" rtexprvalue="true"
             type="ru.innopolis.stc12.booksharing.model.dao.entity.User" %>

<div class="card" style="width: 18rem;">
    <img class="card-img-top" src="/images/no_avatar.png" alt="User avatar">
    <div class="card-body">
        <h5 class="card-title">${user.login}</h5>
    </div>
    <ul class="list-group list-group-flush">
        <li class="list-group-item">${user.email}</li>
    </ul>
    <div class="card-body">
        <a href="#" class="card-link"><spring:message code="personal.writetouser"/></a>
    </div>
</div>