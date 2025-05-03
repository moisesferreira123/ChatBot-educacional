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