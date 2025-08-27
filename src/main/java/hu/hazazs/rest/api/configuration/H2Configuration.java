package hu.hazazs.rest.api.configuration;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "hu.hazazs.rest.api.repository.h2",
        entityManagerFactoryRef = "h2EntityManagerFactory",
        transactionManagerRef = "h2TransactionManager"
)
public class H2Configuration {

    @Bean
    @ConfigurationProperties("spring.datasource.h2")
    public DataSourceProperties h2DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource h2DataSource() {
        return h2DataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean h2EntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(h2DataSource())
                .packages("hu.hazazs.rest.api.entity.h2")
                .persistenceUnit("h2")
                .build();
    }

    @Bean
    public PlatformTransactionManager h2TransactionManager(
            @Qualifier("h2EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}