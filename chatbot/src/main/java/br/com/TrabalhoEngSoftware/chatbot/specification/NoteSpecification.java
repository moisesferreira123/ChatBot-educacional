package br.com.TrabalhoEngSoftware.chatbot.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.TrabalhoEngSoftware.chatbot.entity.NoteEntity;
import jakarta.persistence.criteria.Predicate;

public class NoteSpecification {
	
	// Filtro por título
	public static Specification<NoteEntity> filterByTitle(String title, Long userId) {
		return (root, query, criteriaBuilder)-> {
			Predicate titlePredicate = criteriaBuilder.conjunction();
			if(title != null && !title.isEmpty()) {
				titlePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + title.toLowerCase() + "%");
			}
			Predicate userPredicate = criteriaBuilder.equal(root.get("userEntity").get("id"), userId);
			return criteriaBuilder.and(titlePredicate, userPredicate);
		};
	}
	
    // Ordenação por data de criação mais recente
	public static Specification<NoteEntity> sortByCreatedAtDesc() {
		return (root, query, criteriaBuilder) -> {
			query.orderBy(criteriaBuilder.desc(root.get("createdAt")));
			return null;
		};
	}
	
    // Ordenação por data de criação mais antiga
	public static Specification<NoteEntity> sortByCreatedAtAsc() {
		return (root, query, criteriaBuilder) -> {
			query.orderBy(criteriaBuilder.asc(root.get("createdAt")));
			return null;
		};
	}
	
    // Ordenação por data de atualização mais recente
	public static Specification<NoteEntity> sortedByUpdatedAtDesc() {
		return (root, query, criteriaBuilder) -> {
			query.orderBy(criteriaBuilder.desc(root.get("updatedAt")));
			return null;
		};
	}
	
    // Ordenação por data de atualizão mais antiga
	public static Specification<NoteEntity> sortedByUpdatedAtAsc() {
		return (root, query, criteriaBuilder) -> {
			query.orderBy(criteriaBuilder.asc(root.get("updatedAt")));
			return null;
		};
	}
}


