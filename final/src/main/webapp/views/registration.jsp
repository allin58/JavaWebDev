<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html title="Вход в систему">



    <c:url value="/login.html?command=registration" var="regUrl"/>
    <H2>${error}</H2>

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



</html>