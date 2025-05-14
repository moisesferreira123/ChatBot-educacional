package br.com.TrabalhoEngSoftware.chatbot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

// Service para interagir com o modelo de linguagem (LLM) configurado
@Service
public class AiService {
    
    private final ChatClient chatClient;
    
    // Injeção do ChatClient fornecido pelo Spring AI
    @Autowired
    public AiService(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }
    
    /**
     * Solicita uma sugestão do LLM configurado.
     *
     * @param systemPrompt O prompt de sistema para guiar o comportamento do LLM.
     * @param userPrompt   O prompt de entrada do usuário.
     * @return A sugestão do LLM.
     */
    public String getCompletion(String systemPrompt, String userPrompt) {
        // Create a list to hold the messages
        List<Message> messages = new ArrayList<>();

        // Add the system message if provided
        if (systemPrompt != null && !systemPrompt.trim().isEmpty()) {
            messages.add(new SystemMessage(systemPrompt));
        }

        // Adiciona a mensagem do usuário
        messages.add(new UserMessage(userPrompt));

        // Cria um Prompt com a lista de mensagens
        Prompt prompt = new Prompt(messages);

        // Chama o LLM usando o ChatClient
        return chatClient.prompt(prompt).call().content();
    }
}

