package com.example.bootcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class BootcampProjectApplication {

         @Bean
		 public LocaleResolver localeResolver(){
			 AcceptHeaderLocaleResolver localeResolver=new AcceptHeaderLocaleResolver();
			 localeResolver.setDefaultLocale(Locale.US);
			 return localeResolver;
		 }


	public static void main(String[] args) {
		SpringApplication.run(BootcampProjectApplication.class, args);
	}



}
