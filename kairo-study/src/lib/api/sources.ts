//import { PUBLIC_BACKEND_URL } from '$env/static/public'; // Assuming you have your backend URL in environment variables

// Interface for a Source (should match your backend DTO)
interface Source {
  id: number;
  fileName: string;
  filePath: string; // Or a download URL
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

  if (!response.ok) {
     // Try to parse the error response from the backend
     let errorMessage = 'Failed to upload file';
     try {
       const error = await response.json();
       errorMessage = error.message || error.error || errorMessage;
     } catch (e) {
       // Ignore if response is not JSON
     }
     throw new Error(errorMessage);
  }

  return response.json();
}

// Function to delete a source
export async function deleteSource(sourceId: number, token: string): Promise<void> {
    // Note: The backend endpoint is /api/sources/{sourceId}, not nested under noteId
    const response = await fetch(`http://localhost:8080/api/sources/${sourceId}`, {
        method: 'DELETE',
        headers: {
            'Authorization': `Bearer ${token}`
        }
    });

    if (!response.ok) {
        let errorMessage = 'Failed to delete source';
        try {
          const error = await response.json();
          errorMessage = error.message || error.error || errorMessage;
        } catch (e) {
          // Ignore if response is not JSON
        }
        throw new Error(errorMessage);
    }

    // No content expected for a successful DELETE
}
