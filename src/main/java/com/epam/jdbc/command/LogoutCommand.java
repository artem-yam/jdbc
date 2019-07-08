package com.epam.jdbc.command;

import javax.servlet.http.HttpServletRequest;

import com.epam.jdbc.GoToPageHandler;
import com.epam.jdbc.GoToPageMethodEnum;

public class LogoutCommand implements ActionCommand {
	@Override
	public GoToPageHandler execute(HttpServletRequest request) {
		String page = ("path.page.login");

		request.getSession().invalidate();
		
		GoToPageHandler handler = new GoToPageHandler(GoToPageMethodEnum.FORWARD, page);
		return handler;
	}
}