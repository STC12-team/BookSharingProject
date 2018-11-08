<%@tag description="User details template" pageEncoding="UTF-8" %>
<%@tag import="ru.innopolis.stc12.booksharing.model.pojo.UserDetails" %>
<%@attribute name="userDetails" required="true" type="ru.innopolis.stc12.booksharing.model.pojo.UserDetails" %>

<div class="row">
    <div class="col">
        <form action="/userEdit" method="get">
            <button type="submit" class="btn btn-info float-right">Редактировать</button>
        </form>
    </div>
</div>

<div class="row">
    <div class="col">
        <img src="https://via.placeholder.com/250" class="img-fluid" alt="">
    </div>
    <div class="col">
        <h3 class="display-4">${userDetails.firstName} ${userDetails.lastName} ${userDetails.surname}</h3>
        <p><span class="text-muted">Email:</span> ${userDetails.email}</p>
    </div>
</div>