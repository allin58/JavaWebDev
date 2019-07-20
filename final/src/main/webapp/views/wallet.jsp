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
        <BUTTON type="submit">${cabinet}</BUTTON>
    </FORM>
    </c:if>

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

    <table>

            <tr> <td>Bitcoin</td> <td>${wallet.btc}</td>
                <td>
                    <FORM action="${todepositUrl}+&coin=BTC" method="post">
                        <BUTTON type="submit">${deposit}</BUTTON>
                    </FORM>
                </td>

                <td>
                    <FORM action="${towithdrowUrl}+&coin=BTC" method="post">
                        <BUTTON type="submit">${withdraw}</BUTTON>
                    </FORM>
                </td>

            </tr>






            <tr> <td>Ethereum</td> <td>${wallet.eth}</td>
                <td>
                    <FORM action="${todepositUrl}+&coin=ETH" method="post">
                        <BUTTON type="submit">${deposit}</BUTTON>
                    </FORM>
                </td>

                <td>
                    <FORM action="${towithdrowUrl}+&coin=ETH" method="post">
                        <BUTTON type="submit">${withdraw}</BUTTON>
                    </FORM>
                </td>

            </tr>
            <tr> <td>Tether</td> <td>${wallet.usdt}</td>

                <td>
                    <FORM action="${todepositUrl}+&coin=USDT" method="post">
                        <BUTTON type="submit">${deposit}</BUTTON>
                    </FORM>
                </td>

                <td>
                    <FORM action="${towithdrowUrl}+&coin=USDT" method="post">
                        <BUTTON type="submit">${withdraw}</BUTTON>
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
                        <BUTTON type="submit">${cancel}</BUTTON>
                    </FORM>
                </td>
                </c:if>

            </tr>
        </c:forEach>
    </table>







</html>