<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html title="Вход в систему">
${user.role}
    <H2>MARKET</H2>

    ${pair}

    <c:url value="/login.html?command=logout" var="logoutUrl"/>
    <c:url value="/login.html?command=towallet" var="walletUrl"/>
    <c:url value="/login.html?command=tocabinet" var="tocabinet"/>
    <c:url value="/login.html?command=tomarket&pair=" var="marketUrl"/>
    <c:url value="/login.html?command=toorders" var="toorderUrl"/>


    <c:if test = "${user.role != 'user'}">
        <FORM action="${tocabinet}" method="post">
            <BUTTON type="submit">в кабинет</BUTTON>
        </FORM>
    </c:if>

    <FORM action="${marketUrl}" method="post">
        <BUTTON type="submit">торговать</BUTTON>
    </FORM>



    <FORM action="${logoutUrl}" method="post">
        <BUTTON type="submit">выйти</BUTTON>
    </FORM>

    <FORM action="${walletUrl}" method="post">
        <BUTTON type="submit">кошелёк</BUTTON>
    </FORM>


    <FORM action="${toorderUrl}" method="post">
        <BUTTON type="submit">мои заявки</BUTTON>
    </FORM>

<hr>
<%--
    <FORM action="${marketUrl}+BTC-USDT" method="post">
        <BUTTON type="submit">BTC-USDT</BUTTON>
    </FORM>

    <FORM action="${marketUrl}+ETH-USDT" method="post">
        <BUTTON type="submit">ETH-USDT</BUTTON>
    </FORM>

    <FORM action="${marketUrl}+BTC-ETH" method="post">
        <BUTTON type="submit">BTC-ETH</BUTTON>
    </FORM>--%>

<c:forEach var = "pair" items = "${activepairs}">
     <FORM action="${marketUrl}+${pair.pair}" method="post">
         <BUTTON type="submit">${pair.pair}</BUTTON>
     </FORM>


</c:forEach>






<hr>
<h1>Ask</h1>
    <table>
        <tr> <td>цена</td> <td>объём</td> </tr>
    <c:forEach var = "ask" items = "${asklist}">
        <tr> <td>${ask.price}</td> <td>${ask.amount}</td> </tr>
      </c:forEach>
    </table>

    <hr>
    <h1>Bib</h1>
    <table>
        <tr> <td>цена</td> <td>объём</td> </tr>
    <c:forEach var = "bid" items = "${bidlist}">
    <tr> <td>${bid.price}</td> <td>${bid.amount}</td> </tr>
        </c:forEach>
    </table>
    <hr>

</html>