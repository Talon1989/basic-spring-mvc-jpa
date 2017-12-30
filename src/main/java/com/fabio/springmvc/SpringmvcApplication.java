package com.fabio.springmvc;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringmvcApplication {

	private static final Logger log = Logger.getLogger(SpringmvcApplication.class);

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SpringmvcApplication.class, args);
//		System.out.println(ctx.getBeanDefinitionCount());
//		log.info(ctx.getBeanDefinitionCount());
	}
}
