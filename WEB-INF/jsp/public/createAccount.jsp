<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="../includes/i18n.jsp"%>
    <%@include file="../includes/links.jsp"%>
    <title>
        <fmt:message key="createAccountPage.title" bundle="${labels}"/>
    </title>
</head>
<body>
<%@include file="../includes/header.jsp"%>

<div class="container">
    <div class="col-md-6 col-md-offset-3">
        <!-- Error messages -->
        <c:if test="${not empty errorCreateAccountMessage}">
            <div class="alert alert-danger">
                    ${errorCreateAccountMessage}
            </div>
        </c:if>


        <c:if test="${not empty emailAlreadyInUse}">
            <div class="alert alert-danger">
                    ${emailAlreadyInUse}
            </div>
        </c:if>


        <form action="/register" method="post">
            <fmt:message bundle="${labels}" key="createAccountPage.firstName"/>:
            <input type="text" name="firstName" class="form-control" required/> <br/>

            <fmt:message bundle="${labels}" key="createAccountPage.lastName"/>:
            <input type="text" name="lastName" class="form-control" required/> <br/>

            <fmt:message bundle="${labels}" key="createAccountPage.email"/>:
            <input type="email" name="email" class="form-control" required/> <br/>

            <fmt:message bundle="${labels}" key="createAccountPage.streetName"/>:
            <input type="text" name="street" class="form-control" required/> <br/>

            <fmt:message bundle="${labels}" key="createAccountPage.building"/>:
            <input type="text" name="building" class="form-control" required/> <br/>

            <fmt:message bundle="${labels}" key="createAccountPage.apartments"/>:
            <input type="text" name="apartments" class="form-control" required/> <br/>

            <br>
            <button type="submit" class="btn btn-success btn-block">
                <fmt:message bundle="${labels}" key="createAccountPage.createAccountButton"/>
            </button>
        </form>
    </div>
</div>
</body>
</html>
