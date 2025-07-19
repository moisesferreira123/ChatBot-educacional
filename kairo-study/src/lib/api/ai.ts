export async function fetchAICompletion(
    userPrompt: string,
    onChunk: (chunk: string) => void,
    onComplete: () => void,
    onError: (error: any) => void,
    token: string,
    noteId?: number,
    sourceIds?: number[]
) {
    try {
        const apiEndpoint = 'http://localhost:8080/api/ai/complete';
        const requestBody: {
            userPrompts: string[];
            noteId?: number;
            sourceIds?: number[];
        } = {
            userPrompts: [userPrompt],
        };

        console.log(sourceIds);

        if (noteId !== undefined) {
            requestBody.noteId = noteId;
        }
        if (sourceIds && sourceIds.length > 0) {
            requestBody.sourceIds = sourceIds;
        }

        const response = await fetch(apiEndpoint, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`,
            },
            body: JSON.stringify(requestBody)
        });

        if (!response.ok) {
            const errorText = await response.text();
            onError(new Error(`HTTP error! status: ${response.status}, body: ${errorText}`));
            return;
        }

        if (!response.body) {
            onError(new Error('Response body is not a readable stream.'));
            return;
        }

        onChunk(await response.text());
        onComplete();
    } catch (error) {
        console.error('Error fetching AI completion from backend:', error);
        throw error;
    }
}

export async function fetchGenerateFlashcards(noteId : number, sourceIds : number[], deckId : number, count : number, token: string) {
  const requestBody: {
    userPrompts: string[];
    noteId?: number;
    sourceIds?: number[];
  } = {
    userPrompts: [""]
  };

  const response = await fetch(`http://localhost:8080/api/ai/generate/flashcard/${deckId}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    },
    body: JSON.stringify({ count: count })
  });

  if (noteId !== undefined) {
    requestBody.noteId = noteId;
  }
  if (sourceIds.length > 0) {
    requestBody.sourceIds = sourceIds;
  }
  
  if(!response.ok){
    const errorMessage = await response.json();
    throw new Error(errorMessage.message);
  }
  return response.json();
}
