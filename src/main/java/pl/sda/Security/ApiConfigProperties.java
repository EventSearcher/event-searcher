package pl.sda.Security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("secret")
public record ApiConfigProperties (String apiKey) {
}
