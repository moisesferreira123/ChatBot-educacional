package br.com.TrabalhoEngSoftware.chatbot.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.TrabalhoEngSoftware.chatbot.entity.NoteEntity;
import br.com.TrabalhoEngSoftware.chatbot.entity.SourceEntity;
import br.com.TrabalhoEngSoftware.chatbot.repository.NoteRepository;
import br.com.TrabalhoEngSoftware.chatbot.repository.SourceRepository;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import br.com.TrabalhoEngSoftware.chatbot.dto.FlashcardSuggestionDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;

// Service para interagir com o modelo de linguagem (LLM) configurado
@Service
public class AiService {
    
    private final ChatClient chatClient;
    private final NoteRepository noteRepository;
    private final SourceRepository sourceRepository;

    @Value("${file.upload-dir:./uploads}") // Usa ./uploads enquanto a variavel nao tiver definida no application.properties
    private String uploadDir;
    private final ObjectMapper objectMapper = new ObjectMapper(); // Parsing de ouput JSON da LLM

    // Injeção do ChatClient, NoteRepository, SourceRepository
    public AiService(ChatClient.Builder chatClientBuilder, NoteRepository noteRepository, SourceRepository sourceRepository) {
        this.chatClient = chatClientBuilder.build();
        this.noteRepository = noteRepository;
        this.sourceRepository = sourceRepository;
    }
    
    /**
     * Solicita uma sugestão do LLM configurado, com contexto de nota e fontes.
     *
     * @param systemPrompt O prompt de sistema para guiar o comportamento do LLM.
     * @param userPrompt   O prompt de entrada do usuário.
     * @param noteId       O ID da nota para usar como contexto (opcional).
     * @param sourceIds    Lista de IDs de fontes para usar como contexto (opcional).
     * @param userId       O ID do usuário fazendo a requisição, para validação de acesso.
     * @return A sugestão da LLM.
     */
    public String getCompletion(String systemPrompt, String userPrompt, Long noteId, List<Long> sourceIds, Long userId) {
        StringBuilder contextBuilder = new StringBuilder();

        // Adicionar conteúdo da nota como contexto (caso fornecido)
        if (noteId != null) {
            noteRepository.findById(noteId).ifPresent(note -> {
                if (note.getUserEntity().getId().equals(userId)) {
                    contextBuilder.append("## Context from Note (ID: ").append(noteId).append("):\n");
                    contextBuilder.append("### Title: ").append(note.getTitle()).append("\n");
                    if (note.getSubtitle() != null && !note.getSubtitle().isEmpty()) {
                        contextBuilder.append("#### Subtitle: ").append(note.getSubtitle()).append("\n");
                    }
                    contextBuilder.append("### Content:\n").append(note.getContent()).append("\n\n---\n\n");
                } else {
                    System.err.println("User " + userId + " attempted to access unauthorized note " + noteId);
                }
            });
        }

        // Adicionar conteúdo dos arquivos como contexto (caso fornecido)
        if (sourceIds != null && !sourceIds.isEmpty()) {
            contextBuilder.append("## Context from Uploaded Sources:\n");
            List<SourceEntity> sources = sourceRepository.findAllById(sourceIds);
            for (SourceEntity source : sources) {
                if (source.getNoteEntity().getUserEntity().getId().equals(userId)) {
                    if (noteId == null || source.getNoteEntity().getId().equals(noteId)) {
                        if (source.getExtractedText() != null && !source.getExtractedText().isEmpty()) {
                            contextBuilder.append("### Source (File: ").append(source.getFileName()).append("):\n");
                            String extractedContent = source.getExtractedText();
                            // Trunca o conteúdo até um certo limite de tamanho
                            contextBuilder.append(extractedContent.substring(0, Math.min(extractedContent.length(), 4000)));
                            if (extractedContent.length() > 4000) {
                                contextBuilder.append("...\n[Content Truncated]");
                            }
                            contextBuilder.append("\n\n");
                        } else {
                            contextBuilder.append("### Source (File: ").append(source.getFileName()).append("):\nText content not available or empty.\n\n");
                        }
                    }
                } else {
                     System.err.println("User " + userId + " attempted to access unauthorized source " + source.getId());
                }
            }
            contextBuilder.append("---\n\n");
        }

        String effectiveSystemPrompt = systemPrompt != null ? systemPrompt : "";
        if (contextBuilder.length() > 0) {
            effectiveSystemPrompt = "## Provided Context:\n" + contextBuilder.toString() + "\n## General Instructions for AI:\n" + effectiveSystemPrompt;
        }

        List<Message> messages = new ArrayList<>();
        if (effectiveSystemPrompt != null && !effectiveSystemPrompt.trim().isEmpty()) {
            messages.add(new SystemMessage(effectiveSystemPrompt));
        }
        
        messages.add(new UserMessage(userPrompt != null ? userPrompt : "Please provide a response based on the context."));

        Prompt prompt = new Prompt(messages);
        return chatClient.prompt(prompt).call().content();
    }

    /**
     * Gera sugestões de flashcards a partir do Texto fornecido.
     *
     * @param noteContent Texto usado para gerar os flashcards.
     * @param userId      ID do Usuário.
     * @param count       Número de flashcards a ser gerado.
     * @return Uma lista de FlashcardSuggestionDTO.
     */
    public List<FlashcardSuggestionDTO> generateFlashcardSuggestions(String noteContent, Long userId, int count) {
        String systemPromptForFlashcards =
            "You are an AI assistant specialized in creating educational flashcards. " +
            "Based on the provided text, generate exactly " + count + " flashcards. " +
            "Each flashcard must have a 'front' (a question, term, or concept) and a 'back' (the corresponding answer, definition, or explanation). " +
            "The 'front' should be concise and suitable for a flashcard. The 'back' should also be concise but comprehensive enough to be useful. " +
            "IMPORTANT: Respond ONLY with a valid JSON array of objects. Each object must have two keys: \"front\" and \"back\". Do not include any other text, explanations, or introductions in your response. " +
            "Example format: [{\"front\": \"What is photosynthesis?\", \"back\": \"The process by which green plants use sunlight, water, and carbon dioxide to create their own food.\"}, {\"front\": \"Capital of France?\", \"back\": \"Paris\"}]";

        String userPromptForFlashcards = "Here is the text to generate flashcards from:\n\n" + noteContent;

        List<Message> messages = new ArrayList<>();
        messages.add(new SystemMessage(systemPromptForFlashcards));
        messages.add(new UserMessage(userPromptForFlashcards));

        Prompt prompt = new Prompt(messages);
        String jsonResponse = chatClient.prompt(prompt).call().content();

        try {
            // Remover blocos de markdown (caso a IA responda com eles)
            if (jsonResponse.startsWith("```json")) {
                jsonResponse = jsonResponse.substring(7);
                if (jsonResponse.endsWith("```")) {
                    jsonResponse = jsonResponse.substring(0, jsonResponse.length() - 3);
                }
            }
            jsonResponse = jsonResponse.trim();
            return objectMapper.readValue(jsonResponse, new TypeReference<List<FlashcardSuggestionDTO>>() {});
        } catch (IOException e) {
            System.err.println("Error parsing flashcard suggestions from AI: " + e.getMessage() + "\nResponse was: " + jsonResponse);
            //TODO: Jogar alguma exceção aqui
            return new ArrayList<>(); // Por enquanto retorna lista vazia
        }
    }
}