<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html title="Вход в систему">
    <H2>MARKET</H2>
    ${user.role}

    <c:url value="/login.html?command=logout" var="Url"/>


    <FORM action="${Url}" method="post">
        <BUTTON type="submit">выйти</BUTTON>
    </FORM>

</html>