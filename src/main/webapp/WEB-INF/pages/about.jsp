<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:default title="Букшеринг">
    <jsp:body>
        <div class="main flex-fill">
        <!-- Introduction Row -->
        <h1 class="my-4">Букшеринг
            <small> - это круто!</small>

        <a class="github-button" href="https://github.com/STC12-team" aria-label="Follow @STC12-team on GitHub">Follow @STC12-team</a>
        <a class="github-button" href="https://github.com/STC12-team/BookSharingProject/subscription" data-icon="octicon-eye" aria-label="Watch STC12-team/BookSharingProject on GitHub">Watch</a>
        <a class="github-button" href="https://github.com/STC12-team/BookSharingProject" data-icon="octicon-star" aria-label="Star STC12-team/BookSharingProject on GitHub">Star</a>
        <a class="github-button" href="https://github.com/STC12-team/BookSharingProject/fork" data-icon="octicon-repo-forked" aria-label="Fork STC12-team/BookSharingProject on GitHub">Fork</a>
        </h1>

        <p class="lead">${aboutText}</p>
        <a id="en" href="?lang=en">English</a> <%-- TODO remove links from there --%>
        <a id="ru" href="?lang=ru">Russian</a>
        <%--Можно выводить сообщение напрямую в шаблоне--%>
        <%--<p><spring:message code="message.about"/></p>--%>

        <!-- Team Members Row -->
        <div class="row">
            <div class="col-lg-12">
                <h2 class="my-4">Наша команда</h2>
            </div>
            <div class="col-lg-4 col-sm-6 text-center mb-4">
                <img class="rounded-circle img-fluid d-block mx-auto" style="width: 200px; height: 200px;" src="https://avatars0.githubusercontent.com/u/42582906?s=400&v=4" alt="">
                <h3>Сергей Вдовин</h3>
                <p class="text-muted small"><small>Java full stack developer</small></p>
                <p>What does this team member to? Keep it short! This is also a great spot for social links!</p>
            </div>
            <div class="col-lg-4 col-sm-6 text-center mb-4">
                <img class="rounded-circle img-fluid d-block mx-auto" style="width: 200px; height: 200px;" src="https://avatars3.githubusercontent.com/u/30622502?s=460&v=4" alt="">
                <h3>Александр Бойко</h3>
                <p class="text-muted">Java full stack developer</p>
                <p>What does this team member to? Keep it short! This is also a great spot for social links!</p>
            </div>
            <div class="col-lg-4 col-sm-6 text-center mb-4">
                <img class="rounded-circle img-fluid d-block mx-auto" style="width: 200px; height: 200px;" src="https://avatars2.githubusercontent.com/u/21145755?s=460&v=4" alt="">
                <h3>Ленар Шагиев</h3>
                <p class="text-muted">Java full stack developer</p>
                <p>What does this team member to? Keep it short! This is also a great spot for social links!</p>
            </div>
            <div class="col-lg-4 col-sm-6 text-center mb-4">
                <img class="rounded-circle img-fluid d-block mx-auto" style="width: 200px; height: 200px;" src="https://avatars3.githubusercontent.com/u/42567523?s=460&v=4" alt="">
                <h3>Раиль Муниров</h3>
                <p class="text-muted">Java full stack developer</p>
                <p>What does this team member to? Keep it short! This is also a great spot for social links!</p>
            </div>
            <div class="col-lg-4 col-sm-6 text-center mb-4">
                <img class="rounded-circle img-fluid d-block mx-auto" style="width: 200px; height: 200px;" src="https://avatars2.githubusercontent.com/u/2053703?s=460&v=4" alt="">
                <h3>Артем Зиновьев</h3>
                <p class="text-muted">Java full stack developer</p>
                <p>What does this team member to? Keep it short! This is also a great spot for social links!</p>
            </div>
        </div>

        <!-- User agreement Row TODO: maybe popup? -->
        <div class="row">
            <h3>Here will be user agreement</h3>
            <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the
                industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type
                and scrambled it to make a type specimen book. It has survived not only five centuries, but also the
                leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s
                with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop
                publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
        </div>
        </div>

        <!-- Footer Row -->
        <footer class="footer">
            <hr />
            <div class="float-right">
                <a href="https://github.com/STC12-team"> STC12-team</a>&nbsp;&copy;&nbsp;2018
            </div>
        </footer>
    </jsp:body>
</t:default>