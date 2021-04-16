package com.react.pnld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication
@ConfigurationPropertiesScan("com.react.pnld.model")
@EnableScheduling
public class PnldIndicatorsApplication {

    public static void main(String[] args) { SpringApplication.run(PnldIndicatorsApplication.class, args); }

}
