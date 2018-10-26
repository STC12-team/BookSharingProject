<%@tag description="Book list skeleton" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="bookList" required="true" rtexprvalue="true" type="java.util.List" %>
<form action="/addBookByUser/chooseBook" method="get" role="presentation">
    <div class="row">
        <c:forEach var="book" items="${bookList}">
            <div class="col-md-4">
                <h2>${book.name}</h2>
                <p>Автор: ${book.author}</p>
                <p>ISBN: ${book.isbn}</p>
                <button type="submit" name="chooseBook" value="${book.isbn}" class="btn btn-info">
                    <span>Выбрать</span>
                </button>
            </div>
        </c:forEach>
    </div>
</form>