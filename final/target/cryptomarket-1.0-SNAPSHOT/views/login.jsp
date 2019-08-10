<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/time.tld" %>


<html>


   <head>
       <META http-equiv="Content-Type" content="text/html; charset=UTF-8">

         <style>
             <%@ include file="/css/style.css" %>
         </style>


   </head>


   <body>

   <c:url value="/login.html?command=login" var="loginUrl"/>
   <c:url value="/login.html?command=toregistration" var="regUrl"/>
   <c:url value="/login.html?command=changelanguage" var="changelanguageUrl"/>



     <div class="container"  style="background-color:#f1f1f1">
<H2 style="color: #222222">${cryptocurrencymarket}</H2>

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
       <H2 style="color: #222222">${loginFailed[message]}</H2>
   </div>




   </body>

</html>