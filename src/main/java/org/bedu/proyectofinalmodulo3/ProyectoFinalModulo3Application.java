package org.bedu.proyectofinalmodulo3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ProyectoFinalModulo3Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoFinalModulo3Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ProyectoFinalModulo3Application.class);
	}
}
