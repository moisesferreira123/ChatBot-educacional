package br.com.TrabalhoEngSoftware.chatbot.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.TrabalhoEngSoftware.chatbot.entity.NoteEntity;

public class NoteSpecificationBuilder {
	
	private Specification<NoteEntity> specification;

    public NoteSpecificationBuilder() {
        this.specification = Specification.where(null);  // Começa com uma especificação vazia
    }

    public NoteSpecificationBuilder withTitle(String title, Long userId) {
        this.specification = this.specification.and(NoteSpecification.filterByTitle(title, userId));
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

    // Retorna a especificação construída
    public Specification<NoteEntity> build() {
        return specification;
    }
}
