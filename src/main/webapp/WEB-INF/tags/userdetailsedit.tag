<%@tag description="User details template" pageEncoding="UTF-8" %>
<%@attribute name="userDetails" required="true" type="ru.innopolis.stc12.booksharing.model.dao.entity.UserDetails" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="row">
    <div class="col">
        <c:if test="${not empty userDetails.userPicUrl}">
            <img src="${userDetails.userPicUrl}" class="img-fluid" alt="">
        </c:if>
        <c:if test="${empty userDetails.userPicUrl}">
            <img src="https://via.placeholder.com/250" class="img-fluid" alt="">
        </c:if>
    </div>
    <div class="col">
        <form class="form-horizontal" role="form" method="POST" action="/userEdit" enctype="multipart/form-data">
            <div class="row">
                <div class="col-md-9">
                    <div class="form-group has-danger">
                        <label class="sr-only" for="firstName"><spring:message code="form.labelName"/></label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <input type="text" name="firstName" class="form-control" id="firstName"
                                   placeholder="${userDetails.firstName}" autofocus>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-control-feedback">
                            <span class="text-danger align-middle">
                                <!-- firstName Error -->
                            </span>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-9">
                    <div class="form-group has-danger">
                        <label class="sr-only" for="lastName"><spring:message code="form.labelLastname"/></label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <input type="text" name="lastName" class="form-control" id="lastName"
                                   placeholder="${userDetails.lastName}">
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-control-feedback">
                            <span class="text-danger align-middle">
                                <!-- lastName Error -->
                            </span>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-9">
                    <div class="form-group has-danger">
                        <label class="sr-only" for="surname"><spring:message code="form.labelSurname"/></label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <input type="text" name="surname" class="form-control" id="surname"
                                   placeholder="${userDetails.surname}">
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-control-feedback">
                            <span class="text-danger align-middle">
                                <!-- surname Error -->
                            </span>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-9">
                    <div class="form-group has-danger">
                        <label class="sr-only" for="file"><spring:message code="form.photoUpload"/></label>
                        <div class="custom-file">
                            <input type="file" id="file" name="uploadFile">
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-control-feedback">
                            <span class="text-danger align-middle">
                                <!-- file Error -->
                            </span>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-9">
                    <div class="form-group has-danger">
                        <label class="sr-only" for="email"><spring:message code="form.labelEmail"/></label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <input type="email" name="email" class="form-control" id="email"
                                   placeholder="${userDetails.getUser().email}" disabled>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-control-feedback">
                            <span class="text-danger align-middle">
                                <!-- email Error -->
                            </span>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-9">
                    <div class="form-group has-danger">
                        <label class="sr-only" for="password"><spring:message code="form.labelPassword"/></label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <input type="password" name="password" class="form-control" id="password"
                                   placeholder="<spring:message code="form.newPassword"/>" disabled>
                        </div> <!-- TODO: implement it -->
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-control-feedback">
                            <span class="text-danger align-middle">
                                <!-- password Error -->
                            </span>
                    </div>
                </div>
            </div>
            <div class="row" style="padding-top: 1rem">
                <div class="col-md-9">
                    <button type="submit" class="btn btn-success float-right" value="<spring:message code="form.saveChanges"/>"><span class="fa fa-sign-in"></span>
                        Сохранить изменения
                    </button>
                </div>
            </div>
        </form>
    </div>

</div>