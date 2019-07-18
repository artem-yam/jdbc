package com.epam.jdbc;

import org.apache.logging.log4j.CloseableThreadContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Adds session ID as parameter
 */
public class SessionFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
        throws IOException, ServletException {
        
        try (final CloseableThreadContext.Instance ctc =
                 CloseableThreadContext.put("sessionId",
                     ((HttpServletRequest) request).getSession().getId())) {
            
            chain.doFilter(request, response);
        }
    }
}