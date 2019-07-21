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
    <c:url value="/login.html?command=setlimitorder" var="setlimitorderUrl"/>
    <c:url value="/login.html?command=executemarketorder" var="executemarketorderUrl"/>


<%--
    <c:if test = "${user.role != 'user'}">
        <FORM action="${tocabinet}" method="post">
            <BUTTON type="submit">${cabinet}</BUTTON>
        </FORM>
    </c:if>--%>

    <FORM action="${marketUrl}" method="post">
        <BUTTON type="submit">${market}</BUTTON>
    </FORM>



    <FORM action="${logoutUrl}" method="post">
        <BUTTON type="submit">${logout}</BUTTON>
    </FORM>

    <FORM action="${walletUrl}" method="post">
        <BUTTON type="submit">${mywallet}</BUTTON>
    </FORM>


    <FORM action="${toorderUrl}" method="post">
        <BUTTON type="submit">${myorders}</BUTTON>
    </FORM>

<hr>


<c:forEach var = "pair" items = "${activepairs}">
     <FORM action="${marketUrl}+${pair.pair}" method="post">
         <BUTTON type="submit">${pair.pair}</BUTTON>
     </FORM>


</c:forEach>
<hr>


<H2>лимитный ордер</H2>
${setlimitordermessage}
<FORM action="${setlimitorderUrl}" method="post">
    <LABEL for="price">Цена</LABEL>
    <INPUT type="text" id="price" name="price">

    <LABEL for="amount">Количество</LABEL>
    <INPUT type="text" id="amount" name="amount">

    <button type="submit" name="buybutton" value="buy">${buy}</button>
    <button type="submit" name="sellbutton" value="sell">${sell}</button>
</FORM>

<hr>
<H2>рыночный ордер</H2>
${executemarketordermessage}
<FORM action="${executemarketorderUrl}" method="post">


    <LABEL for="amountm">Количество</LABEL>
    <INPUT type="text" id="amountm" name="amount">

    <button type="submit" name="buybutton" value="buy">${buy}</button>
    <button type="submit" name="sellbutton" value="sell">${sell}</button>
</FORM>




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