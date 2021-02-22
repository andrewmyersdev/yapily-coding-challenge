package com.yapily.marvelcomics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MarvelComicsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarvelComicsApplication.class, args);
	}

}
