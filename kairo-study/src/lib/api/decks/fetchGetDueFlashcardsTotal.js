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