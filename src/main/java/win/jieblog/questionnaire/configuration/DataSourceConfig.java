package win.jieblog.questionnaire.configuration;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import win.jieblog.questionnaire.enums.DatabaseType;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {
    /**
     * 写库
     * @return
     */
    @Bean(name = "write")
    @ConfigurationProperties("spring.datasource.druid.one")
    public DataSource dataSourceOne(){
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 读库
     * @return
     */
    @Bean(name = "read")
    @ConfigurationProperties("spring.datasource.druid.two")
    public DataSource dataSourceTwo(){
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 动态数据源 默认为读库
     * @param writeDataSource
     * @param readDataSource
     * @return
     */
    @Bean
    public AbstractRoutingDataSource routingDataSource(@Qualifier("write")DataSource writeDataSource, @Qualifier("read") DataSource readDataSource){
        Map<Object,Object> targetDataSource=new HashMap<Object, Object>();
        targetDataSource.put(DatabaseType.WRITE,writeDataSource);
        targetDataSource.put(DatabaseType.READ,readDataSource);
        AbstractRoutingDataSource routingDataSource=new DynamicDataSourceConfig();
        routingDataSource.setTargetDataSources(targetDataSource);
        routingDataSource.setDefaultTargetDataSource(writeDataSource);//设置默认的datasource
        return routingDataSource;
    }
    @Bean
    public SqlSessionFactory sqlSessionFactory(AbstractRoutingDataSource routingDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(routingDataSource);
        SqlSessionFactory sqlSessionFactory=sqlSessionFactoryBean.getObject();
        return sqlSessionFactory;
    }
}
