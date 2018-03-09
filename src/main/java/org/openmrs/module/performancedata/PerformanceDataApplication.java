package org.openmrs.module.performancedata;

import org.openmrs.module.performancedata.options.GeneratorOptions;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PerformanceDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(PerformanceDataApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            GeneratorOptions generatorOptions = new GeneratorOptions(args);
            System.out.println(generatorOptions.toString());
        };
    }
}
