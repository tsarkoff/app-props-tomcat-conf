package ru.krymtech.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.String.format;

//@PropertySource("classpath:application.properties")
@PropertySource("file:${catalina.home}/conf/04-conf-app-props-redirect.properties")
@ConfigurationProperties(prefix = "conf.app")
@SpringBootApplication
@RestController
@RequestMapping("/")
public class ConfApp extends SpringBootServletInitializer {
    @Value("${conf.app.nickname}")
    private String nickname;

    public static void main(String[] args) {
        System.setProperty("SPRING_CONFIG_LOCATION", "%CATALINA_HOME%/conf/03-conf-app-main.properties");
        SpringApplication.run(ConfApp.class, args);
    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder app) {
        return app
                .sources(ConfApp.class)
                .properties("spring.config.name=03-conf-app-main");
    }

    @GetMapping
    public ResponseEntity<String> welcome() {
        return new ResponseEntity<>(format("GET ConfApp.welcome(): Welcome while using ==>  %s !!!", nickname), HttpStatus.OK);
    }
}
