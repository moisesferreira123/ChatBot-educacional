export async function fetchDeleteDeck(deckId, token) {
  const response = await fetch(`http://localhost:8080/api/decks/${deckId}`, {
    method: 'DELETE',
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });
  if(!response.ok){
    throw new Error("Error deleting deck");
  }
}

export async function fetchGetFlashcardsTotal(deckId, token) {
  const response = await fetch(`http://localhost:8080/api/decks/get-flashcards-total/${deckId}`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });
  if(!response.ok) throw new Error("Error getting flashcards total by deck Id");
  return response.json();
}

export async function fetchUpdateDeck(deckId, title, topic, token) {
  const response = await fetch(`http://localhost:8080/api/decks/${deckId}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    },
    body: JSON.stringify({
      title: title,
      topic: topic
    })
  });
  if(!response.ok) throw new Error("Error updating deck");
  return response.json();
}

export async function fetchCreateDeck(title, topic, token) {
  const response = await fetch('http://localhost:8080/api/decks', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    },
    body: JSON.stringify({
      title: title,
      topic: topic
    })
  });
  if(!response.ok){
    const errorMessage = response.message || 'Unknown error creating deck';
    throw new Error(errorMessage);
  }
}

export async function fetchGetDueFlashcardsTotal(deckId, token) {
  const response = await fetch(`http://localhost:8080/api/decks/get-due-flashcards-total/${deckId}`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });
  if(!response.ok) throw new Error("Error getting due flashcards total by deck Id");
  return response.json();
}

export async function fetchGetMasteryLevel(deckId, token) {
  const response = await fetch(`http://localhost:8080/api/decks/get-mastery-level/${deckId}`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });
  if(!response.ok) throw new Error("Error getting mastery level by deck Id");
  return response.json();
}

export async function fetchListDecks(page, pageSize, title, topic, sortType, token) {
  const response = await fetch(`http://localhost:8080/api/decks?page=${page}&size=${pageSize}&title=${title}&topic=${topic}&sortType=${sortType}`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });
  if(!response.ok) throw new Error('Error fetching decks');
  return response.json();
}