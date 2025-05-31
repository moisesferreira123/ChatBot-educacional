interface Source {
  id: number;
  fileName: string;
  filePath: string;
  createdAt: string;
}

// Function to fetch sources for a note
export async function fetchSources(noteId: string, token: string): Promise<Source[]> {
  const response = await fetch(`http://localhost:8080/api/notes/${noteId}/sources`, {
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });

  if (!response.ok) {
    // Handle HTTP errors (e.g., 401, 404, 500)
    const error = await response.json();
    throw new Error(error.message || 'Failed to fetch sources');
  }

  return response.json();
}

// Function to upload a source file
export async function uploadSource(noteId: string, formData: FormData, token: string): Promise<Source> {
  const response = await fetch(`http://localhost:8080/api/notes/${noteId}/sources`, {
    method: 'POST',
    // When sending FormData, the 'Content-Type' header is automatically set correctly by the browser,
    // including the boundary string, so you should NOT set it manually.
    headers: {
        'Authorization': `Bearer ${token}`
    },
    body: formData
  });

  if(!response.ok){
    const errorMessage = await response.json();
    throw new Error(errorMessage.message);
  }

  return response.json();
}

// Function to delete a source
export async function deleteSource(noteId: string, sourceId: number, token: string): Promise<void> {
    const response = await fetch(`http://localhost:8080/api/notes/${noteId}/sources/${sourceId}`, {
        method: 'DELETE',
        headers: {
            'Authorization': `Bearer ${token}`
        }
    });

    if(!response.ok){
      const errorMessage = await response.json();
      throw new Error(errorMessage.message);
    }

    // No content expected for a successful DELETE
}
