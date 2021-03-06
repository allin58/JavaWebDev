<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html title="Вход в систему">


    <c:url value="/login.html?command=logout" var="logoutUrl"/>
    <c:url value="/login.html?command=towallet" var="walletUrl"/>
    <c:url value="/login.html?command=tocabinet" var="tocabinet"/>
    <c:url value="/login.html?command=tomarket" var="marketUrl"/>
    <c:url value="/login.html?command=togglepair&identity=" var="toggleUrl"/>
    <c:url value="/login.html?command=toorders" var="toorderUrl"/>


    <head>

        <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            <%@ include file="/css/style.css" %>
        </style>


    </head>

    <body>
    <H2>${sec} ${user.userName}</H2>

    <div class="container"  style="background-color:#f1f1f1; position: fixed; width: 100%; background: white;
    top: 5%;  left: 0%;">


    <FORM action="${logoutUrl}" method="post">
        <BUTTON type="submit" style="width: 10%;">${logout}</BUTTON>
    </FORM>

    </div>


    <div class="container"  style="background-color:#f1f1f1">
        <H2 style="color: black">${accessiblemarkets} </H2>

    <table>
        <c:forEach var="pair" items="${secData}" >
        <tr> <td>${pair.identity}</td> <td>${pair.pair}</td> <td>${pair.active}</td>
            <td>
            <FORM action="${toggleUrl}+${pair.identity}" method="post">
                <BUTTON type="submit">${toggle}</BUTTON>
            </FORM>
            </td>

        </tr>
        </c:forEach>
    </table>
    </div>
    </body>
</html>