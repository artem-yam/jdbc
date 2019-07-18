package com.epam.jdbc;

/**
 * Defines web page and method to access this page
 */
public class GoToPageHandler {
    /**
     * Method to access page
     */
    private GoToPageMethodEnum method;
    /**
     * Web page
     */
    private String page;

    /**
     * Constructor
     *
     * @param method Method to access page
     * @param page   Web page
     */
    public GoToPageHandler(GoToPageMethodEnum method, String page) {
        super();
        this.method = method;
        this.page = page;
    }

    /**
     * Method getter
     *
     * @return {@link GoToPageMethodEnum}
     */
    public GoToPageMethodEnum getMethod() {
        return method;
    }

    /**
     * Method setter
     *
     * @param method {@link GoToPageMethodEnum}
     */
    public void setMethod(GoToPageMethodEnum method) {
        this.method = method;
    }

    /**
     * Page getter
     *
     * @return web page
     */
    public String getPage() {
        return page;
    }

    /**
     * Page setter
     *
     * @param page web page
     */
    public void setPage(String page) {
        this.page = page;
    }

}
