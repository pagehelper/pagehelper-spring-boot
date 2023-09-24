package tk.mybatis.pagehelper.config;

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

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "tk.mybatis.pagehelper.business2.mapper", sqlSessionFactoryRef = "business2SqlSessionFactory")
public class Business2Config {

    @Bean(name = "business2DataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.business2")
    public DataSourceProperties business2DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "business2DataSource")
    public DataSource business2DataSource(@Qualifier("business2DataSourceProperties") DataSourceProperties dataSourceProperties) {
        HikariDataSource hikariDataSource = dataSourceProperties.initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
        hikariDataSource.setPoolName("business2DataSource");
        return hikariDataSource;
    }


    @Bean(name = "business2SqlSessionFactory")
    public SqlSessionFactory business1SqlSessionFactory(@Qualifier("business2DataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        MybatisProperties mybatisProperties = new MybatisProperties();
        mybatisProperties.setMapperLocations(new String[]{"tk/mybatis/pagehelper/business2/mapper/*Mapper.xml"});
        factory.setMapperLocations(mybatisProperties.resolveMapperLocations());
        return factory.getObject();
    }
}
