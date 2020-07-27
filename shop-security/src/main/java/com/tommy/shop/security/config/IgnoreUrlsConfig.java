package com.tommy.shop.security.config;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 用于配置不需要保护的资源路径
 *
 * @author chengk
 * @date 2020/7/15
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "secure.ignored")
public class IgnoreUrlsConfig {

    private List<String> urls = Lists.newArrayList();

}
