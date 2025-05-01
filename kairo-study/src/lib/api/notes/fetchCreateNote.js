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
    throw new Error("Error creating note: Title not provided");
  }
}