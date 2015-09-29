package com.tmm.reactor

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import reactor.Environment

import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run Application, args
	}
}