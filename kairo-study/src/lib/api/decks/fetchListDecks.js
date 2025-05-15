export async function fetchListDecks(page, pageSize, title, topic, sortType, token) {
  const response = await fetch(`http://localhost:8080/api/decks?page=${page}&size=${pageSize}&title=${title}&topic=${topic}&sortType=${sortType}`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });
  if(!response.ok) throw new Error('Error fetching decks');
  return response.json();
}