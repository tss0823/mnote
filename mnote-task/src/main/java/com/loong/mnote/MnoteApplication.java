package com.loong.mnote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableScheduling
public class MnoteApplication {

    //log define
    static Logger logger = LoggerFactory.getLogger(MnoteApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(MnoteApplication.class)
                .properties("spring.config.name:application,application-service,application-dal",
                        "spring.config.location:classpath:/")
                .build().run(args);

//        SpringApplication.run(MnoteApplication.class, args);

        ConfigurableEnvironment environment = applicationContext.getEnvironment();

//        logger.info("idata.api.url={}",environment.getProperty("idata.api.url"));
    }


    @Bean
    public Docket swaggerEmployeeApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.loong.mnote.task.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder().version("1.0").title("TASK API").description("Task 1.0V").build());
    }
}