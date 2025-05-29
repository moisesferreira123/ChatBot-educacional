package br.com.TrabalhoEngSoftware.chatbot.controller;

import br.com.TrabalhoEngSoftware.chatbot.dto.SourceDTO;
import br.com.TrabalhoEngSoftware.chatbot.entity.UserEntity;
import br.com.TrabalhoEngSoftware.chatbot.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/notes/{noteId}/sources") // URL base para o controlador, endpoint para a nota específica
public class SourceController {

    @Autowired
    private SourceService sourceService;

    @PostMapping
    public ResponseEntity<SourceDTO> uploadSource(
            @PathVariable Long noteId,
            @RequestParam("file") MultipartFile file,
            Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        SourceDTO uploadedSource = sourceService.uploadSource(noteId, user.getId(), file);
        return ResponseEntity.status(HttpStatus.CREATED).body(uploadedSource);
    }

    @GetMapping
    public ResponseEntity<List<SourceDTO>> getSourcesByNoteId(
            @PathVariable Long noteId,
            Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        List<SourceDTO> sources = sourceService.getSourcesByNoteId(noteId, user.getId());
        return ResponseEntity.ok(sources);
    }

    // Não é necessário o noteId aqui, pois o sourceId já é suficiente para identificar o arquivo:
    @DeleteMapping("/{sourceId}")
    public ResponseEntity<Void> deleteSource(
            @PathVariable Long noteId, // Não é necessário
            @PathVariable Long sourceId,
            Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        sourceService.deleteSource(sourceId, user.getId());
        return ResponseEntity.noContent().build();
    }
}
