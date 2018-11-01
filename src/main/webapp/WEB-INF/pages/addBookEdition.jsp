<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:default title="Букшеринг">
    <jsp:body>
        <form class="form-horizontal" action="/addBookEditionUrl" method="post">
            <div class="row">
                <div class="col-md-9">
                    <div class="form-group has-danger">
                        <label class="sr-only" for="bookEditionTitle">Title</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon" style="width: 2.6rem"><span class="fa fa-book"></span></div>
                            <input type="text" name="bookEditionTitle" class="form-control" id="bookEditionTitle"
                                   placeholder="title" required autofocus>
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
            <div class="row">
                <div class="col-md-9">
                    <div class="form-group has-danger">
                        <label class="sr-only" for="bookEditionDescription">Description</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon" style="width: 2.6rem"><span class="fa fa-book"></span></div>
                            <input type="text" name="bookEditionDescription" class="form-control" id="bookEditionDescription"
                                   placeholder="description" required autofocus>
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
            <div class="row">
                <div class="col-md-9">
                    <div class="form-group has-danger">
                        <label class="sr-only" for="bookEditionIsbn">ISBN</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon" style="width: 2.6rem"><span class="fa fa-check"></span></div>
                            <input type="text" name="bookEditionIsbn" class="form-control" id="bookEditionIsbn"
                                   placeholder="isbn" required autofocus>
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
                        Add Book Edition
                    </button>
                </div>
            </div>
        </form>
    </jsp:body>
</t:default>