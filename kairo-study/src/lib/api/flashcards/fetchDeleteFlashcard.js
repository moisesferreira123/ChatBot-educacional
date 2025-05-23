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