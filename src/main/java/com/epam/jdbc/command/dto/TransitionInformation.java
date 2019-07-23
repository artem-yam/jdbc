package com.epam.jdbc.command.dto;

import java.util.Map;

/**
 * Defines web page and method to access this page
 */
public class TransitionInformation {
    /**
     * Method to access page
     */
    private TransitionMethod method;
    /**
     * Web page
     */
    private String page;
    
    /**
     * Parameters to be set on page
     */
    private Map<String, Object> parametersToSet;
    
    /**
     * Constructor
     *
     * @param method Method to access page
     * @param page   Web page
     */
    public TransitionInformation(TransitionMethod method, String page,
                                 Map<String, Object> parametersToSet) {
        super();
        this.method = method;
        this.page = page;
        this.parametersToSet = parametersToSet;
    }
    
    /**
     * Method getter
     *
     * @return {@link TransitionMethod}
     */
    public TransitionMethod getMethod() {
        return method;
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
     * Parameters getter
     *
     * @return parameters map
     */
    public Map<String, Object> getParametersToSet() {
        return parametersToSet;
    }
}
