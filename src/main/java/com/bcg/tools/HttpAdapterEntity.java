package com.bcg.tools;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Auther: baicg
 * @Date: 2018/11/23 09:42
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "http")
@PropertySource("classpath:adapter.properties")
@Data
public class HttpAdapterEntity {

private String url;

private String requestmethod;

private String requestproperity;

private String encode;

private String timeout;

}
