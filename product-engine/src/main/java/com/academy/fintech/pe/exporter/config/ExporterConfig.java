package com.academy.fintech.pe.exporter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class ExporterConfig {
    @Bean(name = "exporterDatasource")
    @ConfigurationProperties("exporter.datasource")
    public DataSource exporterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name="exporterTransaction")
    @Autowired
    DataSourceTransactionManager exporterTransaction(@Qualifier("exporterDatasource") DataSource datasource) {
        DataSourceTransactionManager txm  = new DataSourceTransactionManager(datasource);
        return txm;
    }


}
