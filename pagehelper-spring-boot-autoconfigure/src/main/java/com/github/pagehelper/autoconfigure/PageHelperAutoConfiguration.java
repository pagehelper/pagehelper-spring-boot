/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.github.pagehelper.autoconfigure;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 自定注入分页插件
 *
 * @author liuzh
 */
@Configuration
@ConditionalOnBean(SqlSessionFactory.class)
@EnableConfigurationProperties({PageHelperProperties.class, PageHelperStandardProperties.class})
@AutoConfigureAfter(MybatisAutoConfiguration.class)
//@Import(PageHelperProperties.class)
@Lazy(false)
public class PageHelperAutoConfiguration implements InitializingBean {

    private final List<SqlSessionFactory> sqlSessionFactoryList;

    private final PageHelperProperties properties;

    private ApplicationContext applicationContext;

    public PageHelperAutoConfiguration(List<SqlSessionFactory> sqlSessionFactoryList, PageHelperStandardProperties standardProperties, ApplicationContext applicationContext) {
        this.sqlSessionFactoryList = sqlSessionFactoryList;
        this.properties = standardProperties.getProperties();
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        PageInterceptor interceptor = new PageInterceptor();
        interceptor.setProperties(this.properties);
        // 加载需要排除的SqlSessionFactory
        Set<SqlSessionFactory> excludeSqlSessionFactory = loadExcludeSqlSessionFactory();
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            if (excludeSqlSessionFactory.contains(sqlSessionFactory)) {
                continue;
            }
            org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
            if (!containsInterceptor(configuration, interceptor)) {
                configuration.addInterceptor(interceptor);
            }
        }
    }

    /**
     * 加载需要排除的 SqlSessionFactory
     * @return 不需要增加 pageHelper 的集合
     */
    private Set<SqlSessionFactory> loadExcludeSqlSessionFactory() {
        Set<SqlSessionFactory> excludeSqlSessionFactory = new HashSet<>();
        Optional.ofNullable(properties.getExcludeSqlSessionFactoryName())
                .ifPresent(sqlSessionFactoryBeanNameList -> {
                    sqlSessionFactoryBeanNameList.forEach(sqlSessionFactoryBeanName -> {
                        SqlSessionFactory sqlSessionFactory = applicationContext.getBean(sqlSessionFactoryBeanName, SqlSessionFactory.class);
                        excludeSqlSessionFactory.add(sqlSessionFactory);
                    });
        });
        return excludeSqlSessionFactory;
    }



    /**
     * 是否已经存在相同的拦截器
     *
     * @param configuration
     * @param interceptor
     * @return
     */
    private boolean containsInterceptor(org.apache.ibatis.session.Configuration configuration, Interceptor interceptor) {
        try {
            // getInterceptors since 3.2.2
            return configuration.getInterceptors().stream().anyMatch(config->interceptor.getClass().isAssignableFrom(config.getClass()));
        } catch (Exception e) {
            return false;
        }
    }

}
