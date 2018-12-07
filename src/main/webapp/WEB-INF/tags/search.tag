<%@tag description="Search skeleton" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<form class="form mb-4" action="${pageContext.request.contextPath}/library" method="get" id="searchBooksForm"
      role="form">
    <div class="row">
        <div class="col-sm-11">
            <input type="search" name="searchValue" id="searchValue" class="form-control mr-sm-2" required="true"
                   aria-label="Search" placeholder="<spring:message code="form.isbn"/>"/>
        </div>
        <div class="col-sm-1">
            <button type="submit" class="btn btn-outline-primary"><spring:message code="form.searchButton"/></button>
        </div>
    </div>
</form>
