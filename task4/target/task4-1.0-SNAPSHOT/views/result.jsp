<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html><head><title>Result Page</title></head>
<body>

<p> ${typeOfParser}  </p>


<table class="item-table">
    <tr>
        <th>${id}</th>
        <th>${name}</th>
        <th>${energy}</th>
        <th>${production}</th>
        <th>${type}</th>
        <th>${proteins}</th>
        <th>${fats}</th>
        <th>${carbohydrates}</th>
        <th>${typeOfChocolate}</th>
        <th>${water}</th>
        <th>${sugar}</th>
        <th>${fructose}</th>
        <th>${vanillin}</th>


    </tr>

    <c:forEach items="${candyList}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
           <td>${item.energy}</td>
           <td>${item.production}</td>

           <td>${item.type}</td>

           <td>${item.proteins}</td>
           <td>${item.fats}</td>
           <td>${item.carbohydrates}</td>

           <td>${item.typeOfChocolate}</td>
           <td>${item.water}</td>
           <td>${item.sugar}</td>
           <td>${item.fructose}</td>
           <td>${item.vanillin}</td>

        </tr>
    </c:forEach>

</table>




</body>
</html>