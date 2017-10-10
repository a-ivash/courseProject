<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="../includes/i18n.jsp"%>
    <%@include file="../includes/links.jsp"%>
    <title>
        <fmt:message key="createServicePage.title" bundle="${labels}"/>
    </title>
    <jsp:useBean id="service" class="project.model.services.Service" scope="request"/>
</head>
<body>

<c:set var="postAction" value="${ service == null ? '/createService' : '/updateService'}"/>


<div class="container">
    <div class="col-md-6 col-md-offset-3">
        <h1><fmt:message key="createServicePage.title" bundle="${labels}"/></h1>
        <form action="${postAction}" method="post">
            <input type="hidden" name="action" value="submit">

            <c:if test="${service != null}">
                <input type="hidden" name="serviceId" value="${service.id}">
            </c:if>

            <fmt:message key="createServicePage.serviceName" bundle="${labels}"/>: <br/>
            <input type="text" name="serviceName" class="form-control" value="${service.serviceName}"> <br/>

            <fmt:message key="createServicePage.serviceDescription" bundle="${labels}"/>: <br/>
            <textarea name="serviceDescription" cols="30" rows="10" class="form-control">${service.serviceDescription}</textarea> <br/>

            <fmt:message key="createServicePage.paymentType" bundle="${labels}"/>: <br/>
            <select name="paymentType" class="form-control" selected="${service.paymentType}">
                <option value="once">
                    <fmt:message key="createServicePage.paymentTypeOnce" bundle="${labels}"/>
                </option>
                <option value="monthly">
                    <fmt:message key="createServicePage.paymentTypeMonthly" bundle="${labels}"/>
                </option>
            </select> <br/>

            <fmt:message key="createServicePage.serviceCost" bundle="${labels}"/>: <br/>
            <input type="text" name="servicePrice" class="form-control" value="${service.servicePrice}"> <br/>

            <button type="submit" class="btn btn-info btn-block">
                <fmt:message key="createServicePage.createServiceButton" bundle="${labels}"/>
            </button>
        </form>
    </div>
</div>
</body>
</html>
