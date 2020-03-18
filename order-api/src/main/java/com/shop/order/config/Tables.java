package com.shop.order.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author YKF
 * @date 2020/3/18上午11:52
 */
@ConfigurationProperties(prefix = "shop.mybatis")
@Component
@RefreshScope
public class Tables {
    private List<String> tableNames;

    public List<String> getTableNames() {
        return tableNames;
    }

    public void setTableNames(List<String> tableNames) {
        this.tableNames = tableNames;
    }

}
