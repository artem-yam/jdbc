<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>JDBC task</title>
</head>
<body>

<form name="employeeAddForm" method="POST"
      action="<c:url value="/JDBC?command=CREATEEMPLOYEE"/>">
    <fieldset>
        <legend>New employee creation</legend>
        Имя:
        <input type="text" name="firstName" required/>
        Фамилия:
        <input type="text" name="lastName" required/>
        <input type="submit" value="Create"/>
    </fieldset>
</form>

<div style="color: darkred; font-weight: bold">
    ${DBError}
</div>


<c:if test="${employees != null && !employees.isEmpty()}">
    Employees list: <br/> <br/>

    <c:forEach var="emp" items="${employees}">
        ${emp.getId()} ${emp.getFirstName()} ${emp.getLastName()}<br/>
    </c:forEach>

</c:if>


</body>
</html>