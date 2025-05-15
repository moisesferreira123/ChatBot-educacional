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