package br.com.TrabalhoEngSoftware.chatbot.repository;

import br.com.TrabalhoEngSoftware.chatbot.entity.SourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SourceRepository extends JpaRepository<SourceEntity, Long> {
    // Encontra todas os arquivos associados ao ID especificado
    List<SourceEntity> findByNoteEntityId(Long noteId);

    // Encontra um arquivo específico associado ao ID do arquivo e ao ID da nota (para confirmar que o arquivo pertence à nota)
    SourceEntity findByIdAndNoteEntityId(Long sourceId, Long noteId);
}
