package com.wanghao.dataconversion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author wanghao
 */
@SpringBootApplication
@EnableTransactionManagement
public class DataconversionApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataconversionApplication.class, args);
    }

}
