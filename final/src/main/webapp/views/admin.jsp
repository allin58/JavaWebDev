<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html title="Вход в систему">
    <H2>${administrator}</H2>
    ${user.userName}

    <c:url value="/login.html?command=logout" var="logoutUrl"/>
    <c:url value="/login.html?command=towallet" var="walletUrl"/>
    <c:url value="/login.html?command=tocabinet" var="tocabinet"/>
    <c:url value="/login.html?command=tomarket" var="marketUrl"/>
       <c:url value="/login.html?command=approvetransaction&identity=" var="approvteransactionUrl"/>
    <c:url value="/login.html?command=rejectransaction&identity=" var="rejectransactionUrl"/>
    <c:url value="/login.html?command=toorders" var="toorderUrl"/>



    <FORM action="${logoutUrl}" method="post">
        <BUTTON type="submit">${logout} </BUTTON>
    </FORM>


  <%--  <c:if test = "${user.role != 'user'}">
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
--%>


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

</html>