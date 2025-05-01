export async function fetchNotes(page, pageSize, title, sortType ,token) {
  const response = await fetch(`/api/notes?page=${page}&size=${pageSize}&title=${title}&sortType=${sortType}`, {
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });
  if(!response.ok()) throw new Error('Error fetching notes');
  return response.json();
}
