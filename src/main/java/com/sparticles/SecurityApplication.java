package com.sparticles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动
 *
 */
@SpringBootApplication
@EnableScheduling
@EnableAsync
public class SecurityApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(SecurityApplication.class, args);
    }
}
