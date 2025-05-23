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