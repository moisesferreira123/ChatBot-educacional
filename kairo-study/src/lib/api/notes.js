export async function fetchCreateNote(title, subtitle, token) {
  const response = await fetch('http://localhost:8080/api/notes', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    },
    body: JSON.stringify({
      title: title,
      subtitle: subtitle
    })
  });
  if(!response.ok){
    const errorMessage = response.message || 'Unknown error creating note';
    throw new Error(errorMessage);
  }
  return response.json();
}

export async function fetchDeleteNote(noteId, token) {
  const response = await fetch(`http://localhost:8080/api/notes/${noteId}`, {
    method: 'DELETE',
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });
  if(!response.ok){
    throw new Error("Error deleting note");
  }
}

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

export async function fetchUpdateNote(noteId, title, subtitle, content, token) {
  const response = await fetch(`http://localhost:8080/api/notes/${noteId}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    },
    body: JSON.stringify({
      title: title,
      subtitle: subtitle,
      content: content
    })
  });
  if(!response.ok) throw new Error("Error updating note");
  return response.json();
}
