package com.prj.issuetracker;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.prj")
public class IssuetrackerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(IssuetrackerApplication.class, args);
		System.out.println(Arrays.deepToString(context.getBeanDefinitionNames()));
	}

}
