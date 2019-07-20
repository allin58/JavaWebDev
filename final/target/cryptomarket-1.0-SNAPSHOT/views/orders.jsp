<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html title="Вход в систему">
    <H2>заявки</H2>
    ${user.role}

    <c:url value="/login.html?command=logout" var="logoutUrl"/>
    <c:url value="/login.html?command=towallet" var="walletUrl"/>
    <c:url value="/login.html?command=tocabinet" var="tocabinet"/>
    <c:url value="/login.html?command=tomarket" var="marketUrl"/>
    <c:url value="/login.html?command=toorders" var="toorderUrl"/>
    <c:url value="/login.html?command=rejectorder&orderid=" var="rejectorderUrl"/>


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
<c:forEach var="order" items ="${orders}"    >

    <tr> <td>${order.pair}</td> <td>${order.amount}</td> <td>${order.price}</td> <td>${order.type}</td> <td>${order.state}</td>
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

</html>