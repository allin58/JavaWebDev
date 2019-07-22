<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--<%@ include file="/css/style.css" %>--%>

<html>


   <head>


         <style>
             <%@ include file="/css/style.css" %>
         </style>


   </head>


   <body>

   <c:url value="/login.html?command=login" var="loginUrl"/>
    <c:url value="/login.html?command=toregistration" var="regUrl"/>
    <c:url value="/login.html?command=changelanguage" var="changelanguageUrl"/>



   <%--<FORM action="${changelanguageUrl}" method="post">

       <select name="language">
           <option value="en">en</option>
           <option value="ru">ru</option>
       </select>
       <BUTTON type="submit">change</BUTTON>

   </FORM>--%>
   <div class="container"  style="background-color:#f1f1f1">


       <FORM action="${changelanguageUrl}" method="post">
           <c:if test="${language=='en'}">
               <select name="language" onchange="this.form.submit()">
                   <option value="en" selected>en</option>
                   <option value="ru">ru</option>
               </select>
           </c:if>

           <c:if test="${language =='ru'}">
               <select name="language" onchange="this.form.submit()">
                   <option value="en">en</option>
                   <option value="ru" selected>ru</option>
               </select>
           </c:if>
       </FORM>




       <FORM action="${loginUrl}" method="post">
        <INPUT type="text" id="username" name="username" placeholder=${username}>
        <INPUT type="password" id="password" name="password" placeholder=${password}>
        <BUTTON type="submit">${login}</BUTTON>
        </FORM>

   <FORM action="${regUrl}" method="post">
       <BUTTON type="submit">${registration}</BUTTON>
   </FORM>
       <c:set var="message">${loginmessage}</c:set>
       <H2>${loginFailed[message]}</H2>
   </div>

   </body>

</html>