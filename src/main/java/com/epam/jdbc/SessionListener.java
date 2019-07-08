package com.epam.jdbc;

import com.epam.jdbc.datalayer.EmployeeDAO;
import com.epam.jdbc.datalayer.dto.Employee;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
	public void attributeRemoved(HttpSessionBindingEvent ev) {
	}

	@Override
	public void sessionCreated(HttpSessionEvent ev) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent ev) {

		System.out.println("Сессия уничтожена :" + ev);

		EmployeeDAO userDAO = (EmployeeDAO) ev.getSession().getServletContext().getAttribute("userDAO");
		Employee user = (Employee) ev.getSession().getAttribute("currentUser");
		if (user != null) {
			//userDAO.setOffline(user.getFirstName());
		}
	}
}