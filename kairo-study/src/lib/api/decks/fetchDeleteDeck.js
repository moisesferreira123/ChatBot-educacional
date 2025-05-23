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