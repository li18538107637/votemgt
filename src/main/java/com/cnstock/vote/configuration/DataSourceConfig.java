package com.cnstock.vote.configuration;


import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author liyubo
 * @date 2020/11/22 16:05
 */
@Configuration
// 配置mybatis的接口类放的地方
@MapperScan(basePackages = "com.cnstock.vote.dao", sqlSessionFactoryRef = "SqlSessionFactory")
public class DataSourceConfig {

    // 将这个对象放入Spring容器中
    @Bean(name = "DataSource")
    // 表示这个数据源是默认数据源
    @Primary
    // 读取application.properties中的配置参数映射成为一个对象
    // prefix表示参数的前缀
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource getDateSource() {
        return new DruidDataSource();
    }

    @Bean(name = "SqlSessionFactory")
    // @Qualifier表示查找Spring容器中名字为DataSource1的对象
    public SqlSessionFactory test1SqlSessionFactory(@Qualifier("DataSource") DataSource datasource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setTypeAliasesPackage("com.cnstock.vote.dto");
        bean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        bean.setMapperLocations(
                // 设置mybatis的xml所在位置
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
        return bean.getObject();
    }

    @Bean("SqlSessionTemplate1")
    public SqlSessionTemplate sqlsessiontemplate(
            @Qualifier("SqlSessionFactory") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }
}
