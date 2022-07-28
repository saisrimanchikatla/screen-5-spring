package com.jocata.hmrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@ComponentScan(basePackages="com.jocata.hmrs")
@EntityScan(basePackages="com.jocata.hmrs.entity")
public class HmrsControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HmrsControllerApplication.class, args);
	}

}
