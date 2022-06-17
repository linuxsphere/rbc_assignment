package com.xinglie.dataset;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.File;

@EnableJpaRepositories
@Configuration
@EnableTransactionManagement
public class DataSetAppConfiguration {

    final String appFolder = "C:\\dowJones";
    final String h2DB = "h2" ;

    @Bean(name="uploadedFilesFolder")
    public File getUploadedFileFolder(){
        String fileFolder = appFolder +  "\\files";

        File file = new File(fileFolder);
        return file;
    }

    @Bean(name="transactionManager")
    public JpaTransactionManager jpaTransactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return transactionManager;
    }


    @Bean(destroyMethod = "close")
    public DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:" + h2DB + ";DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=true;INIT=CREATE SCHEMA IF NOT EXISTS DOWJONES\\;SET SCHEMA DOWJONES" );


        dataSource.setUsername("sa");
        dataSource.setPassword("password");
        return dataSource;
    }

    private HibernateJpaVendorAdapter vendorAdapter(){
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(false);
        vendorAdapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
        vendorAdapter.setGenerateDdl(true);


        return vendorAdapter;
    }

    @Bean(name="entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter());
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan("com.xinglie.dataset.model", "com.xinglie.dataset.repository");
        entityManagerFactoryBean.setPersistenceUnitName("DownJonesApp_PU");
        return entityManagerFactoryBean;
    }
}
