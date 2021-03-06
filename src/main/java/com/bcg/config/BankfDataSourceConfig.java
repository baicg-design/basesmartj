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
 * @Date: 2018/12/21 22:02
 * @Description:备1数据源配置
 */
@Configuration
@MapperScan(basePackages = {"com.bcg.mapper.bankf"},sqlSessionFactoryRef = "bankfSeqSessionFactory")
public class BankfDataSourceConfig {

    @Value("${spring.datasource.bankf.bankfMapperLocations}")
    private String bankfMapperLocations;
    @ConfigurationProperties(prefix = "spring.datasource.bankf")
    @Bean(name = "bankfDataSource")
    public DruidDataSource bankfDataSource(){
        return new DruidDataSource();
    }

    //SeqSessionFactory配置
    @Bean(name = "bankfSeqSessionFactory")
    public SqlSessionFactory bankfSqlSessionFactory(@Qualifier("bankfDataSource") DataSource dataSource) throws Exception{
        //创建Bean
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //配置Mapper文件配置
        //sqlSessionFactoryBean.setMapperLocations(resolver.getResource(mainMapperLocations));
        //sqlSessionFactoryBean.setMapperLocations();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(bankfMapperLocations));
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
    @Bean(name = "bankfTransactionManager")
    public DataSourceTransactionManager bankfDataSourceTransactionManager(@Qualifier("bankfDataSource") DataSource dataSource){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }
}
