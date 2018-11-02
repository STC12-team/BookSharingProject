<%@tag description="Book list skeleton" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="takenBooks" required="true" rtexprvalue="true" type="java.util.List" %>
<form action="/takenBooks/readEnd" method="get" role="presentation">
    <div class="row">
        <c:forEach var="book" items="${takenBooks}">
            <div class="col-md-6">
                <h2>${book.bookCopy.bookEdition.title}</h2>
                <p>ISBN: ${book.bookCopy.bookEdition.isbn}</p>
                <p>Издательство: ${book.bookCopy.bookEdition.publisher.name}</p>
                <p>Год издания: ${book.bookCopy.bookEdition.yearOfPublication}</p>
                <p>Описание: ${book.bookCopy.bookEdition.description}</p>
                <p>Дата начала чтения: ${book.getAt}</p>
                <c:choose>
                    <c:when test="${empty book.gaveAt}">
                        <c:if test="${book.bookCopy.status == 'BUSY'}">
                            <button type="submit" name="bookCopyId" value="${book.bookCopy.id}" class="btn btn-info">
                                <span>Дочитал</span>
                            </button>
                        </c:if>
                        <c:if test="${book.bookCopy.status == 'FREE'}">
                            <button type="submit" name="handed" value="${book.bookCopy.id}" class="btn btn-info">
                                <span>Передал</span>
                            </button>
                        </c:if>
                    </c:when>
                    <c:otherwise>
                        <p>Дата окончания чтения: ${book.gaveAt}</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:forEach>
    </div>
</form>