package com.epam.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
    private static final Logger logger = LogManager
            .getLogger(new Object() {
            }.getClass().getEnclosingClass());

    @Override
    public void sessionCreated(HttpSessionEvent ev) {
        logger.info("New session " + ev.getSession().getId() + " was created");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent ev) {
        logger.info("Session " + ev.getSession().getId() + " was destroyed");
    }
}