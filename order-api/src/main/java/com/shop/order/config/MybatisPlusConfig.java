package com.shop.order.config;

import com.baomidou.mybatisplus.extension.parsers.DynamicTableNameParser;
import com.baomidou.mybatisplus.extension.parsers.ITableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.shop.utils.HeaderConstants;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * MybatisPlus插件配置
 * @Author YKF
 * @date 2020/3/18上午11:51
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.shop.order.db"})
public class MybatisPlusConfig {
    @Autowired
    private Tables tablesName;
    /**
     * 动态表名
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(HttpServletRequest request) {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        DynamicTableNameParser dynamicTableNameParser = new DynamicTableNameParser();
        dynamicTableNameParser.setTableNameHandlerMap(new HashMap<String, ITableNameHandler>(tablesName.getTableNames().size()){{
            //涉及表集合
            List<String> tables = tablesName.getTableNames();
            //动态表规则 初始表名+_+appKey
            tables.forEach(
                    tableTitle ->{
                        put(tableTitle,(metaObject, sql, tableName) ->{
                            // metaObject 可以获取传入参数，这里实现你自己的动态规则
                            // 因为metaObject太麻烦，这里根据自身的业务需求直接从头信息里面获取
                            String appKey = null;
                            try {
                                if (request == null) {
                                    return tableName;
                                }
                                appKey = request.getHeader(HeaderConstants.APP_KEY);
                                if (appKey != null && !appKey.isEmpty()) {
                                    return String.format("%s_%s", tableName, appKey);
                                }
                            } catch (Exception e) {
                                System.out.println("没有http");
                            }
                            return tableName;
                        });
                    }
            );
        }});
        paginationInterceptor.setSqlParserList(Collections.singletonList(dynamicTableNameParser));
        return paginationInterceptor;
    }
}
