package br.com.TrabalhoEngSoftware.chatbot.service;

import br.com.TrabalhoEngSoftware.chatbot.dto.SourceDTO;
import br.com.TrabalhoEngSoftware.chatbot.entity.NoteEntity;
import br.com.TrabalhoEngSoftware.chatbot.entity.SourceEntity;
import br.com.TrabalhoEngSoftware.chatbot.exception.FileStorageException;
import br.com.TrabalhoEngSoftware.chatbot.exception.ObjectDeletionException;
import br.com.TrabalhoEngSoftware.chatbot.exception.ObjectNotFoundException;
import br.com.TrabalhoEngSoftware.chatbot.exception.StorageInitializationException;
import br.com.TrabalhoEngSoftware.chatbot.exception.UnauthorizedObjectAccessException;
import br.com.TrabalhoEngSoftware.chatbot.repository.NoteRepository;
import br.com.TrabalhoEngSoftware.chatbot.repository.SourceRepository;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
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
    private final Tika tika = new Tika(); // Inicializar instância do Tika

    public SourceService() {
        // Criar o diretório de upload se não existir
        try {
            Files.createDirectories(Paths.get(uploadDir));
        } catch (IOException e) {
            // Tratamento básico de erro
            e.printStackTrace();
            throw new StorageInitializationException("Failed to initialize storage directory");
        }
    }

    @Transactional
    public SourceDTO uploadSource(Long noteId, Long userId, MultipartFile file) {
        NoteEntity note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ObjectNotFoundException("Note not found"));

        if (!note.getUserEntity().getId().equals(userId)) {
            throw new UnauthorizedObjectAccessException("Unauthorized to add source to this note");
        }

        try {
            String originalFileName = file.getOriginalFilename();
            String uniqueFileName = System.currentTimeMillis() + "_" + originalFileName;
            Path filePath = Paths.get(uploadDir, uniqueFileName);

            Files.copy(file.getInputStream(), filePath);

            String extractedTextContent = "";
            try (InputStream inputStream = file.getInputStream()) {
                extractedTextContent = tika.parseToString(inputStream);
            } catch (IOException | TikaException e) {
                System.err.println("Error extracting text from file " + originalFileName + ": " + e.getMessage());
                //Segue com texto vazio (sem jogar exceptions)
            }

            SourceEntity source = new SourceEntity();
            source.setFileName(originalFileName);
            source.setFilePath(filePath.toString());
            source.setNoteEntity(note);
            source.setExtractedText(extractedTextContent);

            SourceEntity savedSource = sourceRepository.save(source);

            return new SourceDTO(savedSource);

        } catch (IOException e) {
            throw new FileStorageException("Failed to upload file");
        }
    }

    @Transactional(readOnly = true)
    public List<SourceDTO> getSourcesByNoteId(Long noteId, Long userId) {
        NoteEntity note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ObjectNotFoundException("Note not found"));

        if (!note.getUserEntity().getId().equals(userId)) {
            throw new UnauthorizedObjectAccessException("Unauthorized to view sources for this note");
        }

        List<SourceEntity> sources = sourceRepository.findByNoteEntityId(noteId);
        return sources.stream()
                .map(SourceDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteSource(Long sourceId, Long userId) {
        SourceEntity source = sourceRepository.findById(sourceId)
                .orElseThrow(() -> new ObjectNotFoundException("Source not found"));

        if (!source.getNoteEntity().getUserEntity().getId().equals(userId)) {
            throw new UnauthorizedObjectAccessException("Unauthorized to delete this source");
        }

        try {
            Path filePath = Paths.get(source.getFilePath());
            Files.deleteIfExists(filePath);
            sourceRepository.delete(source);
        } catch (IOException e) {
            throw new ObjectDeletionException("Failed to delete file " + source.getFileName());
        }
    }
}