package com.epam.jdbc.command;

import com.epam.jdbc.GoToPageHandler;
import com.epam.jdbc.GoToPageMethodEnum;

import javax.servlet.http.HttpServletRequest;

public class MainPageCommand implements ActionCommand {
    @Override
    public GoToPageHandler execute(HttpServletRequest request) {
        request.getSession().removeAttribute("userAction");

		/*ScientificActivityDAO scientificActivityDAO = (ScientificActivityDAO) request.getServletContext()
				.getAttribute("scientificActivityDAO");

		request.getSession().setAttribute("activityList", scientificActivityDAO.getAllActivities());
*/
        String page = ("/main.jsp");
        
        GoToPageHandler handler =
            new GoToPageHandler(GoToPageMethodEnum.FORWARD, page);
        return handler;
    }
}