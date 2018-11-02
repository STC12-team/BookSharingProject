<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:default title="Букшеринг">
    <jsp:body>
        <form class="form-horizontal" action="/addBookEditionUrl" method="post">

            <!-- Title field -->
            <div class="row">
                <div class="col-md-9">
                    <div class="form-group has-danger">
                        <label class="sr-only" for="bookEditionTitle">Title</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon" style="width: 2.6rem"><span class="fa fa-book"></span></div>
                            <input type="text" name="bookEditionTitle" class="form-control" id="bookEditionTitle"
                                   placeholder="Название книги" required autofocus>
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
                        <label class="sr-only" for="bookEditionDescription">Description</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon" style="width: 2.6rem"><span class="fa fa-book"></span></div>
                            <input type="text" name="bookEditionDescription" class="form-control" id="bookEditionDescription"
                                   placeholder="Краткое описание" required autofocus>
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
                        <label class="sr-only" for="bookEditionPublisher">Publisher</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon" style="width: 2.6rem"><span class="fa fa-book"></span></div>
                            <input type="text" name="bookEditionPublisher" class="form-control"
                                   id="bookEditionPublisher"
                                   placeholder="Издание" required autofocus>
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
                        Добавить книжное издание в базу
                    </button>
                </div>
            </div>
        </form>
    </jsp:body>
</t:default>