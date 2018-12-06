<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:default title="Букшеринг">
    <jsp:body>
        <form class="form-horizontal" action="/addBookEditionUrl" method="post">

            <!-- Title field -->
            <div class="row">
                <div class="col-md-9">
                    <div class="form-group has-danger">
                        <label class="sr-only" for="bookEditionTitle"><spring:message code="bookEditions.bookTitleLabel"/></label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon" style="width: 2.6rem"><span class="fa fa-book"></span></div>
                            <input type="text" name="bookEditionTitle" class="form-control" id="bookEditionTitle"
                                   placeholder="<spring:message code="bookEditions.bookTitle"/>" required autofocus>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-control-feedback">
                        <span class="text-danger align-middle">
                            <!-- bookEditionTitle error message here -->
                        </span>
                    </div>
                </div>
            </div>

            <!-- Description field -->
            <div class="row">
                <div class="col-md-9">
                    <div class="form-group has-danger">
                        <label class="sr-only" for="bookEditionDescription"><spring:message code="bookEditions.bookShortDescriptionLabel"/></label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon" style="width: 2.6rem"><span class="fa fa-book"></span></div>
                            <input type="text" name="bookEditionDescription" class="form-control" id="bookEditionDescription"
                                   placeholder="<spring:message code="bookEditions.bookShortDescription"/>" required autofocus>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-control-feedback">
                        <span class="text-danger align-middle">
                            <!-- bookEditionDescription error message here -->
                        </span>
                    </div>
                </div>
            </div>

            <!-- Publisher field -->
            <div class="row">
                <div class="col-md-9">
                    <div class="form-group has-danger">
                        <label class="sr-only" for="bookEditionPublisher"><spring:message code="bookEditions.bookPublisherLabel"/></label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon" style="width: 2.6rem"><span class="fa fa-book"></span></div>
                            <input type="text" name="bookEditionPublisher" class="form-control"
                                   id="bookEditionPublisher"
                                   placeholder="<spring:message code="bookEditions.bookPublisher"/>" required autofocus>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-control-feedback">
                        <span class="text-danger align-middle">
                            <!-- bookEditionDescription error message here -->
                        </span>
                    </div>
                </div>
            </div>

            <!-- ISBN field -->
            <div class="row">
                <div class="col-md-9">
                    <div class="form-group has-danger">
                        <label class="sr-only" for="bookEditionIsbn">ISBN</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon" style="width: 2.6rem"><span class="fa fa-check"></span></div>
                            <input type="text" name="bookEditionIsbn" class="form-control" id="bookEditionIsbn"
                                   placeholder="ISBN" required autofocus>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-control-feedback">
                        <span class="text-danger align-middle">
                            <!-- bookEditionIsbn error message here -->
                        </span>
                    </div>
                </div>
            </div>
            <div class="row" style="padding-top: 1rem">
                <div class="col-md-9">
                    <button type="submit" class="btn btn-primary float-right" value="Add">
                        <spring:message code="bookEditions.bookAddButton"/>
                    </button>
                </div>
            </div>
        </form>
    </jsp:body>
</t:default>