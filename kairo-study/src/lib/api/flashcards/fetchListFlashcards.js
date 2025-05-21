export async function fetchListFlashcards(page, pageSize, word, dominatedFlashcard, undominatedFlashcard, deckId, sortType, token) {
  const response = await fetch(`http://localhost:8080/api/flashcards?page=${page}&size=${pageSize}&word=${word}&dominatedFlashcard=${dominatedFlashcard}&undominatedFlashcard=${undominatedFlashcard}&sortType=${sortType}&deckId=${deckId}`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });
  if(!response.ok) throw new Error('Error fetching flashcards');
  return response.json();
}