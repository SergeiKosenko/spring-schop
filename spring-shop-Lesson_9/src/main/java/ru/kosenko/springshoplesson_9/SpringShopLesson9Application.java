package ru.kosenko.springshoplesson_9;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.mail.dsl.Mail;
import org.springframework.messaging.MessageChannel;


@SpringBootApplication
@OpenAPIDefinition
public class SpringShopLesson9Application {

    @Bean
    public MessageChannel sendMailChannel() {
        return new DirectChannel();

    }

    @Bean
    public IntegrationFlow sendMailFlow() {
        return IntegrationFlows.from("sendMailChannel")
                .handle(Mail.outboundAdapter("smtp.mail.ru")
                        .port(25)
                        .credentials("serg.cos@internet.ru", "5M6WkpXsCZ1ZkRFDN3t4")
                        .javaMailProperties(p -> {
                            p.put("mail.debug", "true");
                            p.put("mail.smtp.ssl.trust", "*");
                            p.put("mail.smtp.starttls.enable", "true");
                        })
                )
                .get();
    }

    public static void main(String[] args) {SpringApplication.run(SpringShopLesson9Application.class, args);}

}
