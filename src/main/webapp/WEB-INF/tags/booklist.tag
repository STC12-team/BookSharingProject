<%@tag description="Book list skeleton" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="bookEditionList" required="true" rtexprvalue="true" type="java.util.List" %>
<form action="/addBookByUser/chooseBook" method="get" role="presentation">
    <div class="row">
        <c:forEach var="bookEdition" items="${bookEditionList}">
            <div class="col-md-6">
                <h2>${bookEdition.title}</h2>
                <p>ISBN: ${bookEdition.isbn}</p>
                <p>Издательство: ${bookEdition.publisher.name}</p>
                <p>Год издания: ${bookEdition.yearOfPublication}</p>
                <p>Описание: ${bookEdition.description}</p>
                <button type="submit" name="chooseBook" value="${bookEdition.isbn}" class="btn btn-info">
                    <span>Выбрать</span>
                </button>
            </div>
        </c:forEach>
    </div>
</form>