package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.configuration.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ JwtProperties.class })
public class TpTacs2024C1Grupo1Application {

	public static void main(String[] args) {
		SpringApplication.run(TpTacs2024C1Grupo1Application.class, args);
	}

}
