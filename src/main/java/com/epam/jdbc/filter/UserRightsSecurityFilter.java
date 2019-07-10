package com.epam.jdbc.filter;

import javax.servlet.*;
import java.io.IOException;

public class UserRightsSecurityFilter implements Filter {
    /**
     * Method what realizes filtration of request: checks if user tries to
     * interact with servlet with commands which are not enable for this type of
     * user
     */
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
        /*HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        
        System.out.println("----------------------");
        System.out.println("Employee rights Filter info:");
        
        if ("true"
                .equals(req.getSession().getAttribute("UserRightsFilterWorked"))
                && req.getSession().getAttribute("UserRightsFilterWorked") !=
                       null) {
            req.getSession().setAttribute("UserRightsFilterWorked", "false");
        } else {
            req.getSession().removeAttribute("UserRightsFilterMessage");
        }
        
        if (session != null) {
            Employee user = (Employee) session.getAttribute("currentUser");
            
            System.out.println("Employee : " + user);
            
            if (user != null) {
                String userType = user.getUserType().getValue();
                
                System.out.println("user type : " + userType);
                
                String action = request.getParameter("command");
                
                System.out.println("user action : " + action);
                
                if (!("login".equals(action) || "logout".equals(action) ||
                          action == null || action.isEmpty())) {
                    
                    if ("сотрудник".equals(userType) &&
                            ("adminpage".equals(action) ||
                                 "usercreationpage".equals(action)
                                 || "usercreationcompleted".equals(action) ||
                                 "usereditpage".equals(action)
                                 || "usereditcompleted".equals(action) ||
                                 "usersearch".equals(action)
                                 || "userdelete".equals(action))) {
                        System.out.println("--------------------------");
                        System.out.println("Employee Rights Security filter");
                        System.out.println("--------------------------");
                        
                        req.getSession().setAttribute("userAction", "logout");
                        req.getSession().invalidate();
                        
                        req.getSession().setAttribute("UserRightsFilterMessage",
                            "Взаимодействие с информацией о пользователях без прав администратора");
                        req.getSession()
                            .setAttribute("UserRightsFilterWorked", "true");
                        
                        resp.sendRedirect(req.getRequestURI());
                        return;
                    }
                }
            }
        }
        */

        chain.doFilter(request, response);

    }
}