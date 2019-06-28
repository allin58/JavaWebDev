<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html><head><title>JSP Timing</title></head>
<body>
<%--<h5>Счетчик времени от запуска приложения до нажатия кнопки</h5>--%>








<form action="upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file" />



        <select name="language">
            <option value="russian">russian</option>
            <option value="english" selected>english</option>

        </select>


        <select name="typeOfParser">
            <option value="SAX">SAX</option>
            <option value="DOM" selected>DOM</option>
            <option value="STAX">STAX</option>
        </select>

    <input type="submit" value="parse">
</form>




<%--<jsp:useBean id="calendar" class="java.util.GregorianCalendar"/>
<form name="Simple" action="timeaction" method="POST">
    <input type="hidden" name="time" value="${calendar.timeInMillis}"/>
    <input type="submit" name="button" value="Посчитать время"/>
</form>--%>


</body></html>