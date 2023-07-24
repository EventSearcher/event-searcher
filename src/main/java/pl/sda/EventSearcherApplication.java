package pl.sda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pl.sda.Security.ApiConfigProperties;


@SpringBootApplication
@EnableConfigurationProperties(ApiConfigProperties.class)
public class EventSearcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventSearcherApplication.class, args);
	}

}
