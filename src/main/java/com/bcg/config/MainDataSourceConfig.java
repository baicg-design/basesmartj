package com.bcg.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;


/**
 * @Auther: baicg
 * @Date: 2018/12/21 21:06
 * @Description:主数据源配置
 */
@Configuration
@MapperScan(basePackages = {"com.bcg.mapper.main"},sqlSessionFactoryRef = "mainSeqSessionFactory")
public class MainDataSourceConfig {

    @Value("${spring.datasource.main.mainMapperLocations}")
    private String mainMapperLocations;
    @ConfigurationProperties(prefix = "spring.datasource.main")
    @Bean(name = "mainDataSource")
    @Primary
    public DruidDataSource mainDataSource(){
        return new DruidDataSource();
    }

    //SeqSessionFactory配置
    @Bean(name = "mainSeqSessionFactory")
    @Primary
    public SqlSessionFactory mainSqlSessionFactory(@Qualifier("mainDataSource") DataSource dataSource) throws Exception{
        //创建Bean
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //配置Mapper文件配置
        //sqlSessionFactoryBean.setMapperLocations(resolver.getResource(mainMapperLocations));
        //sqlSessionFactoryBean.setMapperLocations();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mainMapperLocations));
        //配置分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);

        //设置插件
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});
        return sqlSessionFactoryBean.getObject();
    }
    //配置事物管理
    @Bean(name = "mainTransactionManager")
    @Primary
    public DataSourceTransactionManager mainDataSourceTransactionManager(@Qualifier("mainDataSource") DataSource dataSource){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }
}
