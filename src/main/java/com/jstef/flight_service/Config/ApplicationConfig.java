package com.jstef.flight_service.Config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.catalina.Context;
import org.apache.tomcat.util.scan.StandardJarScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages="com.jstef.flight_service")
public class ApplicationConfig {

    @Autowired
    Environment env;

    @Bean
    public DataSource securityDataSource() {

        ComboPooledDataSource securityDataSource = new ComboPooledDataSource();

        try {
            securityDataSource.setDriverClass("spring.datasource.driverClassName");
        }
        catch (PropertyVetoException exc) {
            throw new RuntimeException(exc);
        }
        // set database connection properties, make sure to fill application
        //properties with valid ones
        securityDataSource.setJdbcUrl(env.getProperty("spring.datasource.url"));
        securityDataSource.setUser(env.getProperty("spring.datasource.username"));
        securityDataSource.setPassword(env.getProperty("spring.datasource.password"));

        // connection pool
        securityDataSource.setInitialPoolSize(
                Integer.parseInt(env.getProperty("connection.pool.initialPoolSize")));
        securityDataSource.setInitialPoolSize(
                Integer.parseInt(env.getProperty("connection.pool.minPoolSize")));
        securityDataSource.setInitialPoolSize(
                Integer.parseInt(env.getProperty("connection.pool.maxPoolSize")));
        securityDataSource.setInitialPoolSize(
                Integer.parseInt(env.getProperty("connection.pool.maxIdleTime")));

        securityDataSource.setUnreturnedConnectionTimeout(2);

        return securityDataSource;
    }

    @Bean
    public ViewResolver viewResolver() {

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("SAMPLE_EMAIL_ADDRESS");
        mailSender.setPassword("SAMPLE_PASSWORD");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Bean
    public TomcatServletWebServerFactory tomcatFactory() {
        return new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                ((StandardJarScanner) context.getJarScanner()).setScanManifest(false);
            }
        };
    }
}
