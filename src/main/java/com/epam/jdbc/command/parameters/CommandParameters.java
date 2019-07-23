package com.epam.jdbc.command.parameters;

import com.epam.jdbc.datalayer.DataSourceType;

import javax.servlet.http.HttpSession;

public class CommandParameters {

    private DataSourceType dataSourceType = DataSourceType.ORACLE;
    private HttpSession session;

    public DataSourceType getDataSourceType() {
        return dataSourceType;
    }

    public void setDataSourceType(DataSourceType dataSourceType) {
        this.dataSourceType = dataSourceType;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }
}
