package com.epam.jdbc.command;

import com.epam.jdbc.GoToPageHandler;
import com.epam.jdbc.GoToPageMethodEnum;
import com.epam.jdbc.datalayer.EmployeeDAO;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

public class UserCreationCompletedCommand implements ActionCommand {

	@Override
	public GoToPageHandler execute(HttpServletRequest request) {

		EmployeeDAO userDAO = (EmployeeDAO) request.getServletContext().getAttribute("userDAO");
		request.getSession().setAttribute("userAction", "user create");

		String userLogin = "";
		String userPassword = "";
		String lastName = "";
		String firstName = "";
		String middleName = "";
		String phoneNumber = "";
		String description = "";
		String userDepartment = "";
		String userDegreePost = "";
		try {
			userLogin = new String(request.getParameter("userLogin").getBytes("ISO-8859-1"), "UTF-8");
			userPassword = new String(request.getParameter("userPassword").getBytes("ISO-8859-1"), "UTF-8");
			lastName = new String(request.getParameter("lastName").getBytes("ISO-8859-1"), "UTF-8");
			firstName = new String(request.getParameter("firstName").getBytes("ISO-8859-1"), "UTF-8");
			middleName = new String(request.getParameter("middleName").getBytes("ISO-8859-1"), "UTF-8");
			phoneNumber = new String(request.getParameter("phoneNumber").getBytes("ISO-8859-1"), "UTF-8");
			description = new String(request.getParameter("description").getBytes("ISO-8859-1"), "UTF-8");
			userDepartment = new String(request.getParameter("userDepartment").getBytes("ISO-8859-1"), "UTF-8");
			userDegreePost = new String(request.getParameter("userDegreePost").getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("Ошибка в смене кодировки параметра");
			e.printStackTrace();
		}

		request.getSession().setAttribute("createdUser", lastName + " " + firstName + " " + middleName);

		/*UserFullName userName = new UserFullName(lastName, firstName, middleName);

		UserPersonalData userData = new UserPersonalData(userName, phoneNumber, description, userDepartment,
				userDegreePost);*/

		String userType = "";
		switch ((String) request.getParameter("userType")) {
		case "administrator":
			userType = "администратор";
			break;
		case "worker":
			userType = "сотрудник";
			break;
		default:
			break;
		}

		//userDAO.createEmployee(userLogin, userPassword, userData, userType);

		request.getSession().setAttribute("usersList", userDAO.getAllEmployees());

		String page = ("path.page.admin");

		GoToPageHandler handler = new GoToPageHandler(GoToPageMethodEnum.REDIRECT, page);
		return handler;
	}
}