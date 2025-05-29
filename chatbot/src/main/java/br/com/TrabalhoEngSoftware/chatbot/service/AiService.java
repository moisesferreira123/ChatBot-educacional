package br.com.TrabalhoEngSoftware.chatbot.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

import reactor.core.publisher.Flux;

// Service para interagir com o modelo de linguagem (LLM) configurado
@Service
public class AiService {
    
    private final ChatClient chatClient;
    private final NoteRepository noteRepository;
    private final SourceRepository sourceRepository;

    @Value("${file.upload-dir:./uploads}") // Usa ./uploads enquanto a variavel nao tiver definida no application.properties
    private String uploadDir;

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
}