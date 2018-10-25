<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:default title="Book sharing">
    <jsp:body>
        <form action="/booksFound/searchBook" method="get" id="searchBooksForm" role="form">
            <div class="row">
                <div class="form-group col-md-10">
                    <input type="text" name="searchValue" id="searchValue" class="form-control" required="true"
                           placeholder="Введите ISBN, название или автора книги"/>
                </div>
                <div class="col-md-2">
                    <button type="submit" class="btn btn-info">
                        <span class="glyphicon glyphicon-search"></span> Поиск
                    </button>

                </div>
            </div>
        </form>
        <c:if test="${not empty message}">
            <div class="alert alert-success">
                    ${message}
            </div>
        </c:if>
        <c:choose>
            <c:when test="${not empty bookList}">
                <form action="/booksFound/chooseBook" method="get" role="presentation">
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
            </c:when>
        </c:choose>
    </jsp:body>
</t:default>