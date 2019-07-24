<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
    <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%--  <link rel="stylesheet" type="text/css" href="css/style.css">--%>

    <style>
        <%@ include file="/css/style.css" %>
    </style>


</head>

<body>
    <c:url value="/login.html?command=registration" var="regUrl"/>

    <div class="container"  style="background-color:#f1f1f1">
    <FORM action="${regUrl}" method="post">
         <LABEL for="username">${username}</LABEL>
        <INPUT type="text" id="username" name="username">

        <LABEL for="name">${name}</LABEL>
        <INPUT type="text" id="name" name="name">

        <LABEL for="surname">${surname}</LABEL>
        <INPUT type="text" id="surname" name="surname">

        <LABEL for="password">${password}</LABEL>
        <INPUT type="text" id="password" name="password">



        <BUTTON type="submit">${approve}</BUTTON>
    </FORM>


        <c:set var="message">${registrationmessage}</c:set>
        <H2 style="color: #222222">${registrationFailed[message]}</H2>
    </div>

</body>

</html>