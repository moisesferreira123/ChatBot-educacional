package br.com.TrabalhoEngSoftware.chatbot.controller;

import br.com.TrabalhoEngSoftware.chatbot.service.AiService;
import reactor.core.publisher.Flux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// Wrapper para um request de completions
class CompletionRequest {
    private String systemPrompt;
    private String userPrompt;

    // Getters and setters for systemPrompt
    public String getSystemPrompt() { return systemPrompt; }
    public void setSystemPrompt(String systemPrompt) { this.systemPrompt = systemPrompt; }

    // Getters and setters for userPrompt
    public String getUserPrompt() { return userPrompt; }
    public void setUserPrompt(String userPrompt) { this.userPrompt = userPrompt; }
}


/**
 * Controlador para lidar com requests relacionados à IA.
 * Expõe endpoints para interagir com o serviço de IA, como gerar complementações de texto.
 */
@RestController
@RequestMapping("/api/ai") // Rota base para os endpoints da API
public class AiController {

    private final AiService aiService;

    // Inject the AiService
    @Autowired
    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    /**
     * Endpoint para solicitar uma conclusão do LLM.
     *
     * @param request O corpo do request contendo os prompts do sistema e do usuário.
     * @return A sugestão da LLM.
     */
    @PostMapping("/complete") // Handles POST requests to /api/ai/complete
    public String complete(@RequestBody CompletionRequest request) {
        // Call the AiService to get the completion
        return aiService.getCompletion(request.getSystemPrompt(), request.getUserPrompt());
    }
}
