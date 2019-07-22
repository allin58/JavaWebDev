<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>


    <style>
        <%@ include file="/css/style.css" %>
    </style>


</head>

<body>
    <H2>${administrator} ${user.userName}</H2>


    <c:url value="/login.html?command=logout" var="logoutUrl"/>
    <c:url value="/login.html?command=towallet" var="walletUrl"/>
    <c:url value="/login.html?command=tocabinet" var="tocabinet"/>
    <c:url value="/login.html?command=tomarket" var="marketUrl"/>
       <c:url value="/login.html?command=approvetransaction&identity=" var="approvteransactionUrl"/>
    <c:url value="/login.html?command=rejectransaction&identity=" var="rejectransactionUrl"/>
    <c:url value="/login.html?command=toorders" var="toorderUrl"/>


    <div class="container"  style="background-color:#f1f1f1; position: fixed; width: 100%; background: white;
    top: 5%;  left: 0%;">

    <FORM action="${logoutUrl}" method="post">
        <BUTTON type="submit" style="width: 10%;">${logout} </BUTTON>
    </FORM>

      </div>

    <div class="container"  style="background-color:#f1f1f1">
    <table>
        <c:forEach var="transaction" items="${transactionData}" >

            <c:set var="type">${transaction.type}</c:set>
            <tr> <td>${transaction.user}</td> <td>${transaction.coin}</td> <td>${transaction.amount}</td> <td>${transactionType[type]}</td>
        <%--    <tr> <td>${transaction.user}</td> <td>${transaction.coin}</td> <td>${transaction.amount}</td> <td>${transaction.type}</td>--%>

                <td>
                    <FORM action="${approvteransactionUrl}+${transaction.identity}" method="post">
                        <BUTTON type="submit">${approve}</BUTTON>
                    </FORM>
                </td>

                <td>
                    <FORM action="${rejectransactionUrl}+${transaction.identity}+&from=admin" method="post">
                        <BUTTON type="submit">${reject}</BUTTON>
                    </FORM>
                </td>

            </tr>
        </c:forEach>
    </table>
    </div>

    </body>
</html>