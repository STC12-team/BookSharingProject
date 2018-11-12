<%@tag description="User details template" pageEncoding="UTF-8" %>
<%@attribute name="userDetails" required="true" type="ru.innopolis.stc12.booksharing.model.pojo.UserDetails" %>

<div class="row">
    <div class="col">
        <img src="https://via.placeholder.com/250" class="img-fluid" alt="">
    </div>
    <div class="col">
        <form class="form-horizontal" role="form" method="POST" action="/userEdit">
            <div class="row">
                <div class="col-md-9">
                    <div class="form-group has-danger">
                        <label class="sr-only" for="firstName">Имя</label>
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
                        <label class="sr-only" for="lastName">Отчество</label>
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
                        <label class="sr-only" for="surname">Фамилия</label>
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
                        <label class="sr-only" for="email">Почта</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <input type="email" name="email" class="form-control" id="email"
                                   placeholder="${userDetails.email}" disabled>
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
                        <label class="sr-only" for="password">Пароль</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <input type="password" name="password" class="form-control" id="password"
                                   placeholder="Новый пароль" disabled>
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
                    <button type="submit" class="btn btn-success float-right" value="Сохранить изменения"><span class="fa fa-sign-in"></span>
                        Сохранить изменения
                    </button>
                </div>
            </div>
        </form>
    </div>

</div>