<%@tag description="Book list skeleton" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="bookEdition" required="true" rtexprvalue="true"
             type="ru.innopolis.stc12.booksharing.model.pojo.BookEdition" %>
<form action="/addBookByUser/addBook" method="get" role="presentation">
    <div class="row">
        <div class="col-md-11">
            <h2>${bookEdition.title}</h2>
            <p>ISBN: ${bookEdition.isbn}</p>
            <p>Издательство: ${bookEdition.publisher.name}</p>
            <p>Год издания: ${bookEdition.yearOfPublication}</p>
            <p>Описание: ${bookEdition.description}</p>
        </div>
        <div class="col-md-1">
            <button type="submit" name="addBook" value="${bookEdition.isbn}" class="btn btn-info">
                <span>Добавить</span>
            </button>
        </div>
    </div>
</form>