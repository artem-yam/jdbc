package com.epam.jdbc.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Changes request encoding to support russian symbols
 */
public class EncodingFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
        throws IOException, ServletException {
        
        request.setCharacterEncoding("UTF-8");
        
        chain.doFilter(request, response);
    }
}