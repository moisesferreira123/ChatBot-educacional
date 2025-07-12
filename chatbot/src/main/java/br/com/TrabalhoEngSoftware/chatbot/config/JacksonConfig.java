package br.com.TrabalhoEngSoftware.chatbot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.TrabalhoEngSoftware.chatbot.dto.StandardFlashcardDTO;
import br.com.TrabalhoEngSoftware.chatbot.dto.StandardUserAnswerDTO;

@Configuration
public class JacksonConfig {

  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();

    module.registerSubtypes(
      new NamedType(StandardFlashcardDTO.class, "STANDARD_FLASHCARD")
    );

    module.registerSubtypes(
      new NamedType(StandardUserAnswerDTO.class, "STANDARD_FLASHCARD")
    );

    objectMapper.registerModule(module);

    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    return objectMapper;
  }
}
