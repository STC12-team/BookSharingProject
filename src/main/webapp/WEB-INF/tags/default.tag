<%@tag description="Default page skeleton" pageEncoding="UTF-8" %>
<%@attribute name="scripts" fragment="true" %>
<%@attribute name="title" required="true" rtexprvalue="true" type="java.lang.String" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta http-equiv=Content-Type content="text/html;charset=UTF-8">
    <title>"${title}"</title>
    <script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
    <spring:url value="/resources/themeWinter/js/snow-it.js" var="snow"/>
    <script src="${snow}"></script>

    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <style>
        .footer {
            margin: 1.5rem;
        }
    </style>
</head>
<body>
<script type="text/javascript">
    $.fn.snowit({
        flakeColor: '#fff', total: 100, minSize: 30,
        maxSize: 50,
    });
</script>

<div class="jumbotron text-center" style="margin-bottom:0">
    <h1><spring:message code="message.title"/></h1>
    <p><spring:message code="message.welcome"/></p>
</div>
<div id="body">
    <div class="container">
        <t:menu/>
        <div class="wrapper d-flex flex-column">
            <jsp:doBody/>
        </div>
    </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
<script async defer src="https://buttons.github.io/buttons.js"></script>
<div id="scripts">
    <jsp:invoke fragment="scripts"/>
</div>
</html>