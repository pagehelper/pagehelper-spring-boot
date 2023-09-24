package tk.mybatis.pagehelper.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "tk.mybatis.pagehelper.business1.mapper", sqlSessionFactoryRef = "business1SqlSessionFactory")
public class Business1Config {

    @Bean(name = "business1DataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.business1")
    public DataSourceProperties business1DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "business1DataSource")
    public DataSource business1DataSource(@Qualifier("business1DataSourceProperties") DataSourceProperties dataSourceProperties) {
        HikariDataSource hikariDataSource = dataSourceProperties.initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
        hikariDataSource.setPoolName("business1DataSource");
        return hikariDataSource;
    }

    @Bean(name = "business1SqlSessionFactory")
    public SqlSessionFactory business1SqlSessionFactory(@Qualifier("business1DataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        MybatisProperties mybatisProperties = new MybatisProperties();
        mybatisProperties.setMapperLocations(new String[]{"tk/mybatis/pagehelper/business1/mapper/*Mapper.xml"});
        factory.setMapperLocations(mybatisProperties.resolveMapperLocations());
        return factory.getObject();
    }
}
