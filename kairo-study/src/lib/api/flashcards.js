export async function fetchCreateFlashcard(front, back, deckId, token) {
  const response = await fetch(`http://localhost:8080/api/flashcards/${deckId}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    },
    body: JSON.stringify({
        front: front,
        back: back
    })
  });
  if(!response.ok){
    const errorMessage = response.message || 'Unknown error creating flashcard';
    throw new Error(errorMessage);
  }
}

export async function fetchDeleteFlashcard(flashcardId, token) {
  const response = await fetch(`http://localhost:8080/api/flashcards/${flashcardId}`, {
    method: 'DELETE',
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });
  if(!response.ok){
    throw new Error("Error deleting deck");
  }
}

export async function fetchListFlashcards(page, pageSize, word, dominatedFlashcard, undominatedFlashcard, deckId, sortType, token) {
  const response = await fetch(`http://localhost:8080/api/flashcards?page=${page}&size=${pageSize}&word=${word}&dominatedFlashcard=${dominatedFlashcard}&undominatedFlashcard=${undominatedFlashcard}&sortType=${sortType}&deckId=${deckId}`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });
  if(!response.ok) throw new Error('Error fetching flashcards');
  return response.json();
}

export async function fetchUpdateFlashcard(flashcardId, front, back, token) {
  const response = await fetch(`http://localhost:8080/api/flashcards/${flashcardId}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    },
    body: JSON.stringify({
      front: front,
      back: back
    })
  });
  if(!response.ok) throw new Error("Error updating flashcard");
}