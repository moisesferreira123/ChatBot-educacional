export async function fetchUpdateFlashcard(flashcardId, front, back, token) {
  const response = await fetch(`http://localhost:8080/api/flashcards/${flashcardId}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    },
    body: JSON.stringify({
      front: front,
      back: back
    })
  });
  if(!response.ok) throw new Error("Error updating flashcard");
}