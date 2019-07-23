package com.epam.jdbc.command.parameters;

import com.epam.jdbc.datalayer.DataSourceType;

/**
 * Parameters used in common command
 */
public class CommandParameters {
    
    /**
     * Type of used data source
     */
    private DataSourceType dataSourceType = DataSourceType.ORACLE;
    
    /**
     * Data source type getter
     *
     * @return enum value for some data source {@link DataSourceType}
     */
    public DataSourceType getDataSourceType() {
        return dataSourceType;
    }
    
}
