<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html title="Вход в систему">

    ${user.role}


    <c:url value="/login.html?command=logout" var="logoutUrl"/>
    <c:url value="/login.html?command=towallet" var="walletUrl"/>
    <c:url value="/login.html?command=tocabinet" var="tocabinet"/>
    <c:url value="/login.html?command=tomarket" var="marketUrl"/>
    <c:url value="/login.html?command=todeposit" var="todepositUrl"/>
    <c:url value="/login.html?command=towithdrow" var="towithdrowUrl"/>
    <c:url value="/login.html?command=rejectransaction&identity=" var="rejectransactionUrl"/>
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

    <table>

            <tr> <td>BTC</td> <td>${wallet.btc}</td>
                <td>
                    <FORM action="${todepositUrl}+&coin=BTC" method="post">
                        <BUTTON type="submit">пополнить</BUTTON>
                    </FORM>
                </td>

                <td>
                    <FORM action="${towithdrowUrl}+&coin=BTC" method="post">
                        <BUTTON type="submit">снять</BUTTON>
                    </FORM>
                </td>

            </tr>






            <tr> <td>ETH</td> <td>${wallet.eth}</td>
                <td>
                    <FORM action="${todepositUrl}+&coin=ETH" method="post">
                        <BUTTON type="submit">пополнить</BUTTON>
                    </FORM>
                </td>

                <td>
                    <FORM action="${towithdrowUrl}+&coin=ETH" method="post">
                        <BUTTON type="submit">снять</BUTTON>
                    </FORM>
                </td>

            </tr>
            <tr> <td>USDT</td> <td>${wallet.usdt}</td>

                <td>
                    <FORM action="${todepositUrl}+&coin=USDT" method="post">
                        <BUTTON type="submit">пополнить</BUTTON>
                    </FORM>
                </td>

                <td>
                    <FORM action="${towithdrowUrl}+&coin=USDT" method="post">
                        <BUTTON type="submit">снять</BUTTON>
                    </FORM>
                </td>

            </tr>

    </table>

    <hr>


    <table>
        <c:forEach var="transaction" items="${transactions}" >
            <tr> <td>${transaction.coin}</td> <td>${transaction.amount}</td> <td>${transaction.type}</td> <td>${transaction.status}</td>

                <c:if test = "${transaction.status == 'pending'}">
                <td>
                    <FORM action="${rejectransactionUrl}+${transaction.identity}+&from=wallet" method="post">
                        <BUTTON type="submit">отменить</BUTTON>
                    </FORM>
                </td>
                </c:if>

            </tr>
        </c:forEach>
    </table>







</html>