<%@tag description="Book list skeleton" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="bookEdition" required="true" rtexprvalue="true"
             type="ru.innopolis.stc12.booksharing.model.pojo.BookEdition" %>
<%@attribute name="countBookCopy" required="true" rtexprvalue="true" type="java.lang.Integer" %>
<%@attribute name="countBookCopyInStatusFree" required="true" rtexprvalue="true" type="java.lang.Integer" %>
<%@attribute name="userCountInQueue" required="true" rtexprvalue="true" type="java.lang.Integer" %>

<%--Book edition info--%>
<div class="card mb-3">
    <div class="row">
        <div class="col-sm-3">
            <img class="card-img-left" src="/images/no_cover_max.png" alt="${bookEdition.title}">
        </div>
        <div class="col-sm-9 px-3">
            <div class="card-block px-3">
                <h4 class="card-title">${bookEdition.title}</h4>
                <p class="card-text">ISBN: ${bookEdition.isbn}</p>
                <p class="card-text">Издательство: ${bookEdition.publisher.name}</p>
                <p class="card-text">Год издания: ${bookEdition.yearOfPublication}</p>
                <a href="#" class="btn btn-primary">Взять</a>
                <a href="#" class="btn btn-primary">Встать в очередь</a>
            </div>
        </div>
    </div>
    <div class="card-footer">
        <div class="row">
            <div class="col-sm-4">
                <p class="card-text">Количество копий - ${countBookCopy}</p>
            </div>
            <div class="col-sm-4">
                <p class="card-text">Количество свободных книг - ${countBookCopyInStatusFree}</p>
            </div>
            <div class="col-sm-4">
                <p class="card-text">Количество человек в очереди - ${userCountInQueue}</p>
            </div>
        </div>
    </div>
</div>
<%--Book edition description--%>
<div class="card bg-light mb-3">
    <div class="card-header">Описание</div>
    <div class="card-body">
        <p class="card-text">${bookEdition.description}</p>
    </div>
</div>
<%--Book edition review--%>
<div class="card bg-light mb-3">
    <div class="card-header">Отзывы</div>
    <div class="card-body">
        <p class="card-text">Отзывов пока нет.</p>
    </div>
</div>

