export async function fetchCreateFlashcard(type, front, back, flashcardType, deckId, token) {
  const response = await fetch(`http://localhost:8080/api/flashcards/${deckId}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    },
    body: JSON.stringify({
        type: type, 
        front: front,
        back: back,
        flashcardType: flashcardType
    })
  });
  if(!response.ok){
    const errorMessage = await response.json();
    throw new Error(errorMessage.message);
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
    const errorMessage = await response.json();
    throw new Error(errorMessage.message);
  }
}

export async function fetchListFlashcards(page, pageSize, word, flashcardFilter, deckId, sortType, token) {
  const response = await fetch(`http://localhost:8080/api/flashcards?page=${page}&size=${pageSize}&word=${word}&flashcardFilter=${flashcardFilter}&sortType=${sortType}&deckId=${deckId}`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });
  if(!response.ok){
    const errorMessage = await response.json();
    throw new Error(errorMessage.message);
  }
  return response.json();
}

export async function fetchUpdateFlashcard(flashcardId, type, front, back, flashcardType, token) {
  const response = await fetch(`http://localhost:8080/api/flashcards/${flashcardId}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    },
    body: JSON.stringify({
      type: type,
      front: front,
      back: back,
      flashcardType: flashcardType
    })
  });
  if(!response.ok){
    const errorMessage = await response.json();
    throw new Error(errorMessage.message);
  }
}

export async function fetchGetNextDueFlashcardByDeckId(deckId, token) {
  const response = await fetch(`http://localhost:8080/api/flashcards/next-due-flashcard-by-deck-id/${deckId}`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });
  if(response.status === 204) {
    console.log("There are no more cards to review.");
    return null;
  } else if(!response.ok){
    const errorMessage = await response.json();
    throw new Error(errorMessage.message);
  }
  return response.json();
}

export async function fetchApplyReviewResult(flashcardId, flashcardType, answer, token) {
  const response = await fetch(`http://localhost:8080/api/flashcards/evaluate-answer/${flashcardId}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    },
    body: JSON.stringify({
      flashcardType: flashcardType,
      answer: answer
    })
  });
  if(!response.ok){
    const errorMessage = await response.json();
    throw new Error(errorMessage.message);
  }
}

export async function fetchGetCountNewFlashcards(deckId, token) {
  const response = await fetch(`http://localhost:8080/api/flashcards/get-count-new-flashcards/${deckId}`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });
  if(!response.ok){
    const errorMessage = await response.json();
    throw new Error(errorMessage.message);
  }
  return response.json();
}

export async function fetchGetCountLearningFlashcards(deckId, token) {
  const response = await fetch(`http://localhost:8080/api/flashcards/get-count-learning-flashcards/${deckId}`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });
  if(!response.ok){
    const errorMessage = await response.json();
    throw new Error(errorMessage.message);
  }
  return response.json();
}

export async function fetchGetCountReviewFlashcards(deckId, token) {
  const response = await fetch(`http://localhost:8080/api/flashcards/get-count-review-flashcards/${deckId}`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });
  if(!response.ok){
    const errorMessage = await response.json();
    throw new Error(errorMessage.message);
  }
  return response.json();
}

export async function fetchGenerateFlashcardsFromNote(noteId, deckId, count, token) {
  const response = await fetch(`http://localhost:8080/api/flashcards/generate-from-note/${noteId}/deck/${deckId}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    },
    body: JSON.stringify({ count: count })
  });
  //if (!response.ok) {
  //  const errorData = await response.text();
  //  throw new Error(`Error generating flashcards: ${response.status} ${errorData}`);
  //}
  if(!response.ok){
    const errorMessage = await response.json();
    throw new Error(errorMessage.message);
  }
  return response.json();
}
export async function fetchGetFlashcardById(flashcardId, token) {
  const response = await fetch(`http://localhost:8080/api/flashcards/${flashcardId}`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });
  if(!response.ok){
    const errorMessage = await response.json();
    throw new Error(errorMessage.message);
  }
  return response.json();
}

export async function fetchGetNextDueFlashcard(token) {
  const response = await fetch(`http://localhost:8080/api/flashcards/next-due-flashcard`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });
  if(response.status === 204) {
    console.log("There are no more cards to review.");
    return null;
  } else if(!response.ok){
    const errorMessage = await response.json();
    throw new Error(errorMessage.message);
  }
  return response.json();
}

export async function fetchGetCountAllNewFlashcards(token) {
  const response = await fetch(`http://localhost:8080/api/flashcards/get-count-all-new-flashcards`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });
  if(!response.ok){
    const errorMessage = await response.json();
    throw new Error(errorMessage.message);
  }
  return response.json();
}

export async function fetchGetCountAllLearningFlashcards(token) {
  const response = await fetch(`http://localhost:8080/api/flashcards/get-count-all-learning-flashcards`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });
  if(!response.ok){
    const errorMessage = await response.json();
    throw new Error(errorMessage.message);
  }
  return response.json();
}

export async function fetchGetCountAllReviewFlashcards(token) {
  const response = await fetch(`http://localhost:8080/api/flashcards/get-count-all-review-flashcards`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });
  if(!response.ok){
    const errorMessage = await response.json();
    throw new Error(errorMessage.message);
  }
  return response.json();
}