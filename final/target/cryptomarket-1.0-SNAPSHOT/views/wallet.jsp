<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>




    <c:url value="/login.html?command=logout" var="logoutUrl"/>
    <c:url value="/login.html?command=towallet" var="walletUrl"/>
    <c:url value="/login.html?command=tocabinet" var="tocabinet"/>
    <c:url value="/login.html?command=tomarket" var="marketUrl"/>
    <c:url value="/login.html?command=todeposit" var="todepositUrl"/>
    <c:url value="/login.html?command=towithdraw" var="towithdrawUrl"/>
    <c:url value="/login.html?command=rejectransaction&identity=" var="rejectransactionUrl"/>
    <c:url value="/login.html?command=toorders" var="toorderUrl"/>

    <head>

        <style>
            <%@ include file="/css/style.css" %>
        </style>

    </head>
    <body>




   <H2>${usertext} ${user.userName}</H2>





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

   <div class="container"  style="background-color:#f1f1f1;  left: 20%;">
    <table>

            <tr> <td>Bitcoin</td> <td>${wallet.btc}</td>
                <td>
                    <FORM action="${todepositUrl}+&coin=BTC" method="post">
                        <BUTTON type="submit">${deposit}</BUTTON>
                    </FORM>
                </td>

                <td>
                    <FORM action="${towithdrawUrl}+&coin=BTC" method="post">
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
                    <FORM action="${towithdrawUrl}+&coin=ETH" method="post">
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
                    <FORM action="${towithdrawUrl}+&coin=USDT" method="post">
                        <BUTTON type="submit">${withdraw}</BUTTON>
                    </FORM>
                </td>

            </tr>

    </table>
   </div>


   <div class="container"  style="background-color:#f1f1f1 ;  left: 60%;">
    <table>
        <c:forEach var="transaction" items="${transactions}" >

            <c:set var="status">${transaction.status}</c:set>
            <c:set var="type">${transaction.type}</c:set>

            <tr> <td>${transaction.coin}</td> <td>${transaction.amount}</td> <td>${transactionType[type]}</td> <td>${transactionStatus[status]}</td>
           <%-- <tr> <td>${transaction.coin}</td> <td>${transaction.amount}</td> <td>${transaction.type}</td> <td>${transaction.status}</td>--%>

                <c:if test = "${transaction.status == 'pending'}">

                <td >
                    <FORM action="${rejectransactionUrl}+${transaction.identity}+&from=wallet" method="post">
                        <BUTTON type="submit" >${cancel}</BUTTON>
                    </FORM>
                </td>

                </c:if>

            </tr>
        </c:forEach>
    </table>
   </div>>

    </body>




</html>