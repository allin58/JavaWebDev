<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html title="Вход в систему">
    <H2>Вход в систему</H2>

    <c:url value="/login.html?command=login" var="Url"/>

    <H2>${message}</H2>
    <FORM action="${Url}" method="post">
         <LABEL for="username">Имя пользователя:</LABEL>
        <INPUT type="text" id="username" name="username" value="${param.username}">
        <LABEL for="password">Пароль:</LABEL>
        <INPUT type="password" id="password" name="password">
        <BUTTON type="submit">Войти</BUTTON>
    </FORM>
</html>