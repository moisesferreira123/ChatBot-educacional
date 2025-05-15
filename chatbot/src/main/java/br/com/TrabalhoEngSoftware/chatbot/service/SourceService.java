package br.com.TrabalhoEngSoftware.chatbot.service;

import br.com.TrabalhoEngSoftware.chatbot.dto.SourceDTO;
import br.com.TrabalhoEngSoftware.chatbot.entity.NoteEntity;
import br.com.TrabalhoEngSoftware.chatbot.entity.SourceEntity;
import br.com.TrabalhoEngSoftware.chatbot.repository.NoteRepository;
import br.com.TrabalhoEngSoftware.chatbot.repository.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SourceService {

    @Autowired
    private SourceRepository sourceRepository;

    @Autowired
    private NoteRepository noteRepository; // Para encontrar a nota associada ao arquivo

    // TODO: Configurar o diretório de upload de forma mais robusta (ex: usando propriedades do Spring)
    private final String uploadDir = "./uploads"; // FIXME: Temporário, deve ser configurado corretamente

    public SourceService() {
        // Criar o diretório de upload se não existir
        try {
            Files.createDirectories(Paths.get(uploadDir));
        } catch (IOException e) {
            // Tratamento básico de erro
            e.printStackTrace();
        }
    }

    @Transactional
    public SourceDTO uploadSource(Long noteId, Long userId, MultipartFile file) {
        // Encontrar a nota e garantir que pertence ao usuário
        NoteEntity note = noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        if (!note.getUserEntity().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized to add source to this note");
        }

        try {
            // Gerar um nome de arquivo único
            String originalFileName = file.getOriginalFilename();
            String uniqueFileName = System.currentTimeMillis() + "_" + originalFileName;
            Path filePath = Paths.get(uploadDir, uniqueFileName);

            // Salvar o arquivo no sistema de arquivos
            Files.copy(file.getInputStream(), filePath);

            // Criar a entidade SourceEntity
            // e associá-la à nota
            SourceEntity source = new SourceEntity();
            source.setFileName(originalFileName);
            source.setFilePath(filePath.toString()); // Store the file path
            source.setNoteEntity(note);

            SourceEntity savedSource = sourceRepository.save(source);

            return new SourceDTO(savedSource);

        } catch (IOException e) {
            // Tratamento de erro ao salvar o arquivo
            throw new RuntimeException("Failed to upload file", e);
        }
    }

    @Transactional(readOnly = true)
    public List<SourceDTO> getSourcesByNoteId(Long noteId, Long userId) {
        // Encontrar a nota e garantir que pertence ao usuário
        NoteEntity note = noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        if (!note.getUserEntity().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized to view sources for this note");
        }

        List<SourceEntity> sources = sourceRepository.findByNoteEntityId(noteId);
        return sources.stream()
                .map(SourceDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteSource(Long sourceId, Long userId) {
        // Encontrar o arquivo e garantir que pertence ao usuário
        SourceEntity source = sourceRepository.findById(sourceId)
                .orElseThrow(() -> new RuntimeException("Source not found"));

        if (!source.getNoteEntity().getUserEntity().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized to delete this source");
        }

        try {
            // Remover o arquivo do sistema de arquivos
            Path filePath = Paths.get(source.getFilePath());
            Files.deleteIfExists(filePath);

            // Remover a entidade do banco de dados
            sourceRepository.delete(source);
        } catch (IOException e) {
            // Tratamento de erro ao remover o arquivo
            throw new RuntimeException("Failed to delete file", e);
        }
    }
}
