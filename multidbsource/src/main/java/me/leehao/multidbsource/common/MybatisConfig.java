package me.leehao.multidbsource.common;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Configuration
@MapperScan(basePackages="me.leehao.multidbsource.mapper", sqlSessionFactoryRef="sessionFactory")
public class MybatisConfig {
    @Autowired
    Environment environment;

    @Bean(name = "dataSourceTotal")
    @ConfigurationProperties(prefix = "spring.datasource.druid.total")
    public DataSource getDateSourceTotal()
    {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "dataSourcePart1")
    @ConfigurationProperties(prefix = "spring.datasource.druid.part1")
    public DataSource getDateSourcePart1()
    {
        return DruidDataSourceBuilder.create().build();
    }


    @Bean(name="dynamicDataSource")
    public DynamicDataSource DataSource(@Qualifier("dataSourceTotal") DataSource dataSourceTotal,
                                        @Qualifier("dataSourcePart1") DataSource dataSourcePart1){
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DatabaseType.TOTAL, dataSourceTotal);
        targetDataSource.put(DatabaseType.PART1, dataSourcePart1);
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSource);
        dataSource.setDefaultTargetDataSource(dataSourceTotal);

        return dataSource;
    }

    @Bean(name="sessionFactory")
    public SqlSessionFactory sessionFactory(@Qualifier("dynamicDataSource")DynamicDataSource dataSource)
            throws Exception{
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactoryBean.setMapperLocations(
                resolver.getResources(Objects.requireNonNull(environment.getProperty("mybatis.mapperLocations"))));
        return sessionFactoryBean.getObject();
    }
}
