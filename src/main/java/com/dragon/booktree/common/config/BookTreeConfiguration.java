package com.dragon.booktree.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BookTreeConfiguration {
	public RestTemplate restTemplate() {
		return  new RestTemplate();
	}
}
