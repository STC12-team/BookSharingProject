<%@tag description="Book list skeleton" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="bookCopies" required="true" rtexprvalue="true" type="java.util.List" %>
<form action="/catalog" method="get" role="presentation">
    <div class="row">
        <c:forEach var="bookCopy" items="${bookCopies}">
            <div class="col-md-6">
                <h2>${bookCopy.bookEdition.title}</h2>
                <p>ISBN: ${bookCopy.bookEdition.isbn}</p>
                <p>Издательство: ${bookCopy.bookEdition.publisher.name}</p>
                <p>Год издания: ${bookCopy.bookEdition.yearOfPublication}</p>
                <p>Описание: ${bookCopy.bookEdition.description}</p>
                <button type="submit" name="chooseBook" value="${bookCopy.id}" class="btn btn-info">
                    <span>Выбрать</span>
                </button>
            </div>
        </c:forEach>
    </div>
</form>