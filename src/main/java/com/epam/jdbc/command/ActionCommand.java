package com.epam.jdbc.command;

import com.epam.jdbc.GoToPageHandler;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
    GoToPageHandler execute(HttpServletRequest request);
}