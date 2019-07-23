<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<H2>${usertext} ${user.userName}</H2>

    <c:url value="/login.html?command=logout" var="logoutUrl"/>
    <c:url value="/login.html?command=towallet" var="walletUrl"/>
    <c:url value="/login.html?command=tocabinet" var="tocabinet"/>
    <c:url value="/login.html?command=tomarket" var="marketUrl"/>
    <c:url value="/login.html?command=toorders" var="toorderUrl"/>
    <c:url value="/login.html?command=rejectorder&orderid=" var="rejectorderUrl"/>
    <head>

        <style>
            <%@ include file="/css/style.css" %>
        </style>

    </head>



    <body>

    <div class="container"  style="background-color:#f1f1f1;  width: 100%; top: 5%; left: 0%">


        <FORM action="${marketUrl}" method="post" style="float: left; padding: 12px 20px; ">
            <BUTTON type="submit">${market}</BUTTON>
        </FORM>


        <FORM action="${walletUrl}" method="post" style="float: left;  padding: 12px 20px;"  >
            <BUTTON type="submit">${mywallet}</BUTTON>
        </FORM>

        <FORM action="${toorderUrl}" method="post" style="float: left;padding: 12px 20px; ">
            <BUTTON type="submit" >${myorders}</BUTTON>
        </FORM>

        <FORM action="${logoutUrl}" method="post" style="float: left; padding: 12px 20px; ">
            <BUTTON type="submit">${logout}</BUTTON>
        </FORM>


    </div>


    <div class="container"  style="background-color:#f1f1f1;  left: 35%; overflow: auto;">
    <table >
<c:forEach var="order" items ="${orders}"    >



    <c:set var="state">${order.state}</c:set>


    <tr> <td>${order.pair}</td> <td>${order.amount}</td> <td>${order.price}</td> <td>${order.type}</td> <td>${orderState[state]}</td>

        <c:if test = "${order.state == 'active'}">
        <td>
            <FORM action="${rejectorderUrl}+${order.identity}" method="post">
                <BUTTON type="submit">${cancel}</BUTTON>
            </FORM>
        </td>
        </c:if>


    </tr>




</c:forEach>
    </table>


        </div>
    </body>




</html>