package com.ChitChat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

//@EntityScan("com.ChitChat.entity")

@SpringBootApplication
public class ChitChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChitChatApplication.class, args);
	}
}
