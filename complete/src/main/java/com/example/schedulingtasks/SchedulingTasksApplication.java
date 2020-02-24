package com.example.schedulingtasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SchedulingTasksApplication {

	public static void main(final String[] args) {
		SpringApplication.run(SchedulingTasksApplication.class);
	}
}
