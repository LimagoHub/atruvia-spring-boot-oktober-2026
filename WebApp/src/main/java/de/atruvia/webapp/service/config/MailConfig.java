package de.atruvia.webapp.service.config;


import de.atruvia.webapp.YamlPropertySourceFactory;
import de.atruvia.webapp.service.MailServiceDummy;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:mail.yml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix="mail")
@Setter
public class MailConfig {
    private String host;
    private String protocol;
    private String username;
    private String passwort;

    @Bean
    public MailServiceDummy MailServiceDummy() {
        return new MailServiceDummy(username, passwort);
    }
}
