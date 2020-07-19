/*
O * This is a Jin-alpha Project
 * File name : JinAlpahApplication.java
 * Created by : Jinhyun
 * Created on : Jan 2020
 * Contents : This is the main Springboot Application and for swagger
 */
package net.jin;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import net.jin.overwatch.service.OverWatch;
import net.jin.overwatch.service.impl.Mccree;
import net.jin.overwatch.service.impl.Mei;
import net.jin.overwatch.service.impl.Reaper;
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

		
/********************************** Start of Test Code *************************************/
		
		//Call JinTest method
		System.out.println();
		JinTest();
		System.out.println();

		//Print "Hello World"
		System.out.println("Hello World");

		// null value input test
		System.out.println();
		String s = null;
		Object o = null;
		Integer i = null;
		JavaHungry(s);
		System.out.println();

		// 다형성 테스트(OverWatch) : 입출력 처리부		
		OverWatch ow; // 인터페이스 객체 선언
		System.out.println("플레이할 캐릭터 번호 선택(1. 메이, 2. 리퍼, 3. 맥크리)");
		Scanner scanner = new Scanner(System.in); // 스캐너 객체
		int inputData = scanner.nextInt();
		if (inputData == 1) {
			ow = new Mei(); // 업캐스팅
		} else if (inputData == 2) {
			ow = new Reaper(); // 업캐스팅
		} else {
			ow = new Mccree(); // 업캐스팅
		}
        
		// 다형성 테스트(OverWatch):	선택한 조건에 따라서 부모 객체로 자식 메소드 사용(하나의 타입으로 다양한 결과를 얻어냄 / 다형성)
		ow.name();
		ow.lClick();
		ow.rClick();
		ow.shiftButton();
		ow.eButton();
		ow.qButton();
		
/*********************************** End of Test Code *******************************************/
	}
	
	
	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Jin-Alpha").apiInfo(apiInfo()).select()
				.paths(PathSelectors.ant("/api/**")).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Jin Alpha API").description("Jin Alpha API")
				.termsOfServiceUrl("http://www-03.ibm.com/software/sla/sladb.nsf/sla/bm?Open")
				.contact("Niklas Heidloff").license("Apache License Version 2.0")
				.licenseUrl("https://github.com/IBM-Bluemix/news-aggregator/blob/master/LICENSE").version("2.0")
				.build();
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


}
