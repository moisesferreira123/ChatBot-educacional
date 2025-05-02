export async function fetchDeleteNote(noteId, token) {
  const response = await fetch(`http://localhost:8080/api/notes/${noteId}`, {
    method: 'DELETE',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    }
  });
  if(!response.ok){
    throw new Error("Error deleting note");
  }
}