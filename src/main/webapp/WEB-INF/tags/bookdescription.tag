<%@tag description="Book list skeleton" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="book" required="true" rtexprvalue="true" type="ru.innopolis.stc12.booksharing.entitys.Book" %>
<form action="/addBookByUser/addBook" method="get" role="presentation">
    <div class="row">
        <div class="col-md-11">
            <h2>${book.name}</h2>
            <p>Автор: ${book.author}</p>
            <p>ISBN: ${book.isbn}</p>
        </div>
        <div class="col-md-1">
            <button type="submit" name="addBook" value="${book.isbn}" class="btn btn-info">
                <span>Добавить</span>
            </button>
        </div>
    </div>
</form>