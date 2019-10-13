package com.bcg.config;

/**
 * @Auther: baicg
 * @Date: 2018/12/22 14:52
 * @Description:使数据源与线程绑定
 */
public class DynamicDataSourceHolder {

    //使用ThreadLocal把数据源与当前线程绑定
    private static final ThreadLocal<String> dataSource = new ThreadLocal<String>();

    public static void setDataSource(String dataSourceName){

        dataSource.set(dataSourceName);

    }

    public static String getDataSource() {

        return (String) dataSource.get();

    }

    public static void clearDataSource(){

        dataSource.remove();

    }

}
