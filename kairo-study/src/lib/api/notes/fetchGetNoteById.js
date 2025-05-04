export async function fetchGetNoteById(noteId, token) {
  const response = await fetch(`http://localhost:8080/api/notes/${noteId}`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });
  if(!response.ok) throw new Error("Error getting note by Id");
  return response.json();
}