<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>JDBC task</title>
</head>
<body>

    <form name="employeeAddForm" method="POST"
      action="${pageContext.request.contextPath}/JDBC">
        <fieldset>
            <legend>New employee creation</legend>
            Имя:
            <input type="text" name="firstName" required />
            Фамилия:
            <input type="text" name="lastName" required />
            <input type="submit" value="Create" />
        </fieldset>
    </form>

    Employees list: <br/>

    <c:forEach var="emp" items="${employeeDAO.getAllEmployees()}">
        ${emp.getFirstName()} ${emp.getLastName()}<br/>
    </c:forEach>

</body>
</html>