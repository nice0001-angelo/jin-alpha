/*
O * This is a Jin-alpha Project
 * File name : JinAlpahApplication.java
 * Created by : Jinhyun
 * Created on : Jan 2020
 * Contents : This is the main Springboot Application and for swagger
 */
package net.jin;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.*;

import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.*;
import springfox.documentation.spring.web.plugins.*;
import springfox.documentation.swagger2.annotations.*;

@SpringBootApplication
@EnableJpaAuditing
@EnableSwagger2
public class JinAlphaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JinAlphaApplication.class, args);

		
/********************************** Start of Test Code *************************************/
		
		//Call JinTest method
		System.out.println();
		JinTest();
		System.out.println();

		//Print "Hello World"
		System.out.println("Hello Jee, YoonJi, YoonSeo, I Love forever");

		// null value input test
		System.out.println();
		String s = null;
		Object o = null;
		Integer i = null;
		JavaHungry(s);
		System.out.println();


/*********************************** End of Test Code *******************************************/
	}
	
	

	

	public static void JinTest() {
		System.out.println("JinTest");
	}

	public static void JavaHungry(String s) {
		System.out.println("JavaHungry is String");
	}

	public static void JavaHungry(Object o) {
		System.out.println("JavaHungry is Object");
	}

	public static void JavaHungry(Integer i) {
		System.out.println("JavaHungry is Integer");
	}
	
	
	//Bean for Swagger(2020.01)
	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Jin-Alpha").apiInfo(apiInfo()).select()
				.paths(PathSelectors.ant("/**")).build(); //api로 시작하는 것들만 지정
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Jin Alpha API").description("Jin Alpha API")
				.termsOfServiceUrl("http://www-03.ibm.com/software/sla/sladb.nsf/sla/bm?Open")
				.contact("Niklas Heidloff").license("Apache License Version 2.0")
				.licenseUrl("https://github.com/IBM-Bluemix/news-aggregator/blob/master/LICENSE").version("2.0")
				.build();
	}

	
	
}
