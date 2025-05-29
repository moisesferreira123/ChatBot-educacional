package br.com.TrabalhoEngSoftware.chatbot.controller;

import br.com.TrabalhoEngSoftware.chatbot.entity.UserEntity; // Added
import br.com.TrabalhoEngSoftware.chatbot.service.AiService;
import reactor.core.publisher.Flux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication; // Added
import org.springframework.web.bind.annotation.*;

import java.util.List; // Added

// Wrapper para um request de completions
class CompletionRequest {
    private String systemPrompt;
    private String userPrompt;
    private Long noteId; // To provide context from a specific note
    private List<Long> sourceIds; // To provide context from specific sources

    // Getters and setters for systemPrompt
    public String getSystemPrompt() { return systemPrompt; }
    public void setSystemPrompt(String systemPrompt) { this.systemPrompt = systemPrompt; }

    // Getters and setters for userPrompt
    public String getUserPrompt() { return userPrompt; }
    public void setUserPrompt(String userPrompt) { this.userPrompt = userPrompt; }

    // Getters and setters for noteId
    public Long getNoteId() { return noteId; }
    public void setNoteId(Long noteId) { this.noteId = noteId; }

    // Getters and setters for sourceIds
    public List<Long> getSourceIds() { return sourceIds; }
    public void setSourceIds(List<Long> sourceIds) { this.sourceIds = sourceIds; }
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
    @Autowired // Added Autowired for constructor injection clarity
    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    /**
     * Endpoint para solicitar uma conclusão do LLM.
     *
     * @param request O corpo do request contendo os prompts do sistema e do usuário, e opcionais noteId/sourceIds.
     * @param authentication The current user's authentication details.
     * @return A sugestão da LLM.
     */
    @PostMapping("/complete") // Handles POST requests to /api/ai/complete
    public String complete(@RequestBody CompletionRequest request, Authentication authentication) {
        UserEntity currentUser = (UserEntity) authentication.getPrincipal();
        // Call the AiService to get the completion, now with noteId, sourceIds, and userId
        return aiService.getCompletion(
            request.getSystemPrompt(),
            request.getUserPrompt(),
            request.getNoteId(),
            request.getSourceIds(),
            currentUser.getId()
        );
    }
}