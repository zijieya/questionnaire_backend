package win.jieblog.questionnaire.configuration;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSourceConfig extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
       return DatabaseContextHolder.getDatabaseType();
    }
}
