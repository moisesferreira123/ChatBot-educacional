export async function fetchGetFlashcardById(flashcardId, token) {
  const response = await fetch(`http://localhost:8080/api/flashcards/${flashcardId}`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });
  if(!response.ok) throw new Error("Error getting flashcard by Id");
  return response.json();
}