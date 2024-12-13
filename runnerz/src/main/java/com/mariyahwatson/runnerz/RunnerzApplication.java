package com.mariyahwatson.runnerz;

import com.mariyahwatson.runnerz.run.Location;
import com.mariyahwatson.runnerz.run.Run;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

//import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.title;. org.springframework.context.annotation.Bean;


@SpringBootApplication
public class RunnerzApplication {

private static final Logger log= LoggerFactory.getLogger(RunnerzApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(RunnerzApplication.class, args);
		
	}
	@Bean
	CommandLineRunner runner(){
		return args -> {
			Run run = new Run(1, "my first run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 5, Location.OUTDOOR);
			log.info("Run " + run);
		};
	}

}
