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