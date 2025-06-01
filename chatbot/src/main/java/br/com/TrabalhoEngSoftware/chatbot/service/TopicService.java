package br.com.TrabalhoEngSoftware.chatbot.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.TrabalhoEngSoftware.chatbot.exception.UnexpectedFailureException;

@Service
public class TopicService {
  
  private Map<String, Long> topics =  new HashMap<>();

  protected String capitalizeFirstLetter(String topic) {
    return topic.substring(0, 1).toUpperCase() + topic.substring(1).toLowerCase();
  }

  protected Boolean exists(String topic) {
    return topics.containsKey(topic);
  }

  public void addTopic(String topic) {
    if(topic == null || topic.trim().isEmpty()) {
      return;
    } else {
      String capitalizedTopic = capitalizeFirstLetter(topic);
      if(exists(capitalizedTopic)) {
        topics.put(capitalizedTopic, topics.get(capitalizedTopic)+1); 
      } else {
        topics.put(capitalizedTopic, 1L);
      }
    }
  }

  public void removeTopic(String topic) {
    if(topic == null || topic.trim().isEmpty()) {
      return;
    } else {
      String capitalizedTopic = capitalizeFirstLetter(topic);
      if(exists(capitalizedTopic)) {
        topics.put(capitalizedTopic, topics.get(capitalizedTopic)-1);
        if(topics.get(capitalizedTopic) == 0) topics.remove(capitalizedTopic);
      } else {
        throw new UnexpectedFailureException("Error removing topic");
      }
    }
  }

  public void updateTopic(String oldTopic, String newTopic) {
    removeTopic(oldTopic);
    addTopic(newTopic);
  }

  public Collection<String> getAllTopics() {
    return topics.keySet();
  }
}
