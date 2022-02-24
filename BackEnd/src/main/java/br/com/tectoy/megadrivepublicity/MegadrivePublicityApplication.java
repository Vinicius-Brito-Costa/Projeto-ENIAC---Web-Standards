package br.com.tectoy.megadrivepublicity;

import br.com.tectoy.megadrivepublicity.config.AwsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
@EnableConfigurationProperties(AwsProperties.class)
public class MegadrivePublicityApplication {

	public static void main(String[] args) {
		SpringApplication.run(MegadrivePublicityApplication.class, args);
	}

}
