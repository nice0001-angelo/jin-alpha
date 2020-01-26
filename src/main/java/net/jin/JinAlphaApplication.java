/*
 * This is a Jin-alpha Project
 * File name : JinAlpahApplication.java
 * Created by : Jinhyun
 * Created on : Jan 2020
 * Contents : This is the main Springboot Application and for swagger
 */
package net.jin;


import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaAuditing
@EnableSwagger2
public class JinAlphaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JinAlphaApplication.class, args);
	}
	
	@Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Jin-Alpha")
                .apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.ant("/api/**"))
                .build();
    }
     
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Jin Alpha API")
                .description("Jin Alpha API")
                .termsOfServiceUrl("http://www-03.ibm.com/software/sla/sladb.nsf/sla/bm?Open")
                .contact("Niklas Heidloff")
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/IBM-Bluemix/news-aggregator/blob/master/LICENSE")
                .version("2.0")
                .build();
    }

}
