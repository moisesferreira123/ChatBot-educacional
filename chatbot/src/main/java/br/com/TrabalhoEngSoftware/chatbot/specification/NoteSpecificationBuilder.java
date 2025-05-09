package br.com.TrabalhoEngSoftware.chatbot.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.TrabalhoEngSoftware.chatbot.entity.NoteEntity;

public class NoteSpecificationBuilder {
	
	private Specification<NoteEntity> specification;

    public NoteSpecificationBuilder() {
        this.specification = Specification.where(null);  // Começa com uma especificação vazia
    }

    public NoteSpecificationBuilder filterByTitle(String title) {
        this.specification = this.specification.and(NoteSpecification.filterByTitle(title));
        return this;
    }

    public NoteSpecificationBuilder sortByCreatedAtDesc() {
        this.specification = this.specification.and(NoteSpecification.sortByCreatedAtDesc());
        return this;
    }

    public NoteSpecificationBuilder sortByCreatedAtAsc() {
        this.specification = this.specification.and(NoteSpecification.sortByCreatedAtAsc());
        return this;
    }

    public NoteSpecificationBuilder sortByUpdatedAtDesc() {
        this.specification = this.specification.and(NoteSpecification.sortedByUpdatedAtDesc());
        return this;
    }

    public NoteSpecificationBuilder sortByUpdatedAtAsc() {
        this.specification = this.specification.and(NoteSpecification.sortedByUpdatedAtAsc());
        return this;
    }

    // Retorna a especificação construída, filtrando por ID do usuário
    public Specification<NoteEntity> build(Long userId) {
        return this.specification.and((root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("userEntity").get("id"), userId)
        );
    }
}
