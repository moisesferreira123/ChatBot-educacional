export async function fetchListNotes(page, pageSize, title, sortType, token) {
  const response = await fetch(`http://localhost:8080/api/notes?page=${page}&size=${pageSize}&title=${title}&sortType=${sortType}`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });
  if(!response.ok) throw new Error('Error fetching notes');
  return response.json();
}
