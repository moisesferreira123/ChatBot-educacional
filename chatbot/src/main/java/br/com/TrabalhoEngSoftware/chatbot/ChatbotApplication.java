package br.com.TrabalhoEngSoftware.chatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.function.context.config.ContextFunctionCatalogAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@SpringBootApplication(exclude = { ContextFunctionCatalogAutoConfiguration.class })
@EntityScan(basePackages = {"br.com.TrabalhoEngSoftwareFramework.framework.entity", "br.com.TrabalhoEngSoftware.chatbot.entity"})
@EnableJpaRepositories(basePackages = {"br.com.TrabalhoEngSoftwareFramework.framework.repository", "br.com.TrabalhoEngSoftware.chatbot.repository"})
@ComponentScan(
    basePackages = {
        "br.com.TrabalhoEngSoftwareFramework.framework",
        "br.com.TrabalhoEngSoftware.chatbot"
    },
    excludeFilters = @ComponentScan.Filter(
        type = FilterType.REGEX,
        pattern = "br\\.com\\.TrabalhoEngSoftwareFramework\\.framework\\.infra\\.provider\\..*"
    )
)
public class ChatbotApplication {

  public static void main(String[] args) {
    SpringApplication.run(ChatbotApplication.class, args);
  }

}