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