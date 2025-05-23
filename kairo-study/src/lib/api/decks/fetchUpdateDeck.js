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