package br.com.TrabalhoEngSoftware.chatbot.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.TrabalhoEngSoftware.chatbot.service.TopicService;

@RestController
@RequestMapping("api/topics")
public class TopicController {

  @Autowired
  private TopicService topics;

  @GetMapping
  public Collection<String> getAllTopics() {
    return topics.getAllTopics();
  }
}
