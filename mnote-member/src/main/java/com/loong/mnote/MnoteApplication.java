package com.loong.mnote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableSwagger2
@EnableAsync
public class MnoteApplication {

    @Value("${api.host}")
    private String apiHost;

    //log define
    static Logger logger = LoggerFactory.getLogger(MnoteApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(MnoteApplication.class)
                .properties("spring.config.name:application,application-service,application-dal",
                        "spring.config.location:classpath:/")
                .build().run(args);

//        SpringApplication.run(MnoteApplication.class, args);

        ConfigurableEnvironment environment = applicationContext.getEnvironment();

    }


    @Bean
    public Docket swaggerEmployeeApi() {

        ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList();
        parameterBuilder.name("token").description("请求头token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        parameters.add(parameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.loong.mnote.web.controller"))
                .paths(PathSelectors.any())
                .build()
                .host(apiHost)
                .globalOperationParameters(parameters)
                .apiInfo(new ApiInfoBuilder().version("1.0").title("会员端 API").description("Member 1.0V").build());
    }
}