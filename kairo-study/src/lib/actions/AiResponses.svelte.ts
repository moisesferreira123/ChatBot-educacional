import { Editor } from '@tiptap/core';
import { marked } from 'marked';
import Sysprompt from '$lib/extensions/sysprompt.txt?raw'; // This is your general system prompt


export async function handleAiCompletionRequest(
    userPrompt: string,
    insertPos: number,
    editor: Editor,
    token: string,
    noteId?: number,     // Added noteId (optional)
    sourceIds?: number[] // Added sourceIds (optional)
) {
    if (!userPrompt || !editor) return;

    let isStreaming = true;
    let firstInsertionForStream = true;

    try {
        await fetchAIStream(
            userPrompt,
            (chunk: string) => { // onChunk
                if (!isStreaming) return;
                const htmlChunk = marked.parse(chunk, {breaks: true, gfm: true});
                if (editor && editor.view) {
                    if (firstInsertionForStream) {
                        editor.chain().insertContentAt(insertPos, htmlChunk).focus().run();
                        firstInsertionForStream = false;
                    } else {
                        editor.chain().insertContent(htmlChunk).focus().run();
                    }
                    editor.commands.enter();
                }
            },
            () => { // onComplete - vazio pq sem streaming so ocorre um dump de tudo que foi recebido
            },
            (error: any) => { // onError
                if (!isStreaming) return;
                const errorMessage = (error instanceof Error) ? error.message : "Unknown AI streaming error";
                if (editor && editor.view) {
                     const errorHtml = `<p style="color: red;">Error: ${errorMessage}</p>`;
                     if (firstInsertionForStream) {
                        editor.chain().insertContentAt(insertPos, errorHtml).focus().run();
                        firstInsertionForStream = false;
                     } else {
                        editor.chain().insertContent(errorHtml).focus().run();
                     }
                }
                console.error('Error fetching AI response:', error);
                isStreaming = false;
            },
            token,
            noteId,
            sourceIds
        );
    } catch (error) {
        if (isStreaming) {
             console.error('Error initiating AI stream:', error);
             isStreaming = false;
             //TODO: Colocar algum feedback pro usuario aqui
        }
    }
}
export async function fetchAIStream(
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
            systemPrompt: string;
            userPrompt: string;
            noteId?: number;
            sourceIds?: number[];
        } = {
            systemPrompt: Sysprompt,
            userPrompt: userPrompt
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

/* versao com streaming (ainda com muitos bugs)
export default async function handleAiCompletionRequest(
    userPrompt: string,
    insertPos: number,
    editor: Editor,
    token: string,
    noteId?: number,     // Added noteId (optional)
    sourceIds?: number[] // Added sourceIds (optional)
) {
    if (!userPrompt || !editor) return;

    let isStreaming = true;
    let firstInsertionForStream = true;
    let lineBuffer = '';

    try {
        await fetchAIStream(
            userPrompt,
            (chunk: string) => { // onChunk
                if (!isStreaming) return;
                lineBuffer += chunk;
                let newlineIndex;
                while ((newlineIndex = lineBuffer.indexOf('\n')) !== -1) {
                    const completedLine = lineBuffer.substring(0, newlineIndex);
                    lineBuffer = lineBuffer.substring(newlineIndex + 1);
                    const htmlLine = marked.parse(completedLine, { breaks: true, gfm: true });
                    if (editor && editor.view) {
                        if (firstInsertionForStream) {
                            editor.chain().insertContentAt(insertPos, htmlLine).focus().run();
                            firstInsertionForStream = false;
                        } else {
                            editor.chain().insertContent(htmlLine).focus().run();
                        }
                        editor.commands.enter();
                    }
                }
            },
            () => { // onComplete
                if (!isStreaming) return;
                if (lineBuffer.length > 0 && editor && editor.view) {
                    const htmlLine = marked.parse(lineBuffer, { breaks: true, gfm: true });
                    if (firstInsertionForStream) {
                        editor.chain().insertContentAt(insertPos, htmlLine).focus().run();
                    } else {
                        editor.chain().insertContent(htmlLine).focus().run();
                    }
                    lineBuffer = '';
                }
                isStreaming = false;
                console.log('AI stream finished.');
            },
            (error: any) => { // onError
                if (!isStreaming) return;
                const errorMessage = (error instanceof Error) ? error.message : "Unknown AI streaming error";
                if (editor && editor.view) {
                     const errorHtml = `<p style="color: red;">Error: ${errorMessage}</p>`;
                     if (firstInsertionForStream) {
                        editor.chain().insertContentAt(insertPos, errorHtml).focus().run();
                        firstInsertionForStream = false;
                     } else {
                        editor.chain().insertContent(errorHtml).focus().run();
                     }
                }
                console.error('Error fetching AI response:', error);
                isStreaming = false;
            },
            token,
            noteId,      // Pass noteId
            sourceIds    // Pass sourceIds
        );
    } catch (error) {
        if (isStreaming) {
             console.error('Error initiating AI stream:', error);
             isStreaming = false;
             //TODO: Colocar algum feedback pro usuario aqui
        }
    }
}
*/

/* Versao com streaming (muito bugada pra usar)
 export async function fetchAIStream(
    userPrompt: string,
    onChunk: (chunk: string) => void,
    onComplete: () => void,
    onError: (error: any) => void,
    token: string,
    noteId?: number,     // Added noteId
    sourceIds?: number[] // Added sourceIds
) {
    try {
        const apiEndpoint = 'http://localhost:8080/api/ai/complete';
        const requestBody: {
            systemPrompt: string;
            userPrompt: string;
            noteId?: number;
            sourceIds?: number[];
        } = {
            systemPrompt: Sysprompt, // General system prompt from sysprompt.txt
            userPrompt: userPrompt
        };

        console.log(sourceIds);

        if (noteId !== undefined) { // Check for undefined as noteId could be 0
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
                'Accept': 'text/event-stream' // Crucial for SSE
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

        const reader = response.body.pipeThrough(new TextDecoderStream()).getReader();
        let buffer = ''; 

        while (true) {
            const { value, done } = await reader.read();
            if (done) {
                if (buffer.trim().length > 0) {
                   // Process remaining buffer if any - usually SSE events are terminated by \n\n
                }
                onComplete();
                break;
            }
            buffer += value;
            let eventEndIndex;
            while ((eventEndIndex = buffer.indexOf('\n\n')) !== -1) {
                const eventString = buffer.substring(0, eventEndIndex);
                buffer = buffer.substring(eventEndIndex + 2); 
                const lines = eventString.split('\n');
                let eventData = '';
                for (const line of lines) {
                    if (line.startsWith('data:')) {
                        if (eventData.length > 0) {
                           eventData += '\n'; 
                        }
                        eventData += line.substring(5); // Remove "data:" and trim
                    }
                }
                if (eventData.length > 0) {
                    onChunk(eventData);
                }
            }
        }
    } catch (error) {	
        console.error('Error in fetchAIStream:', error);
        onError(error);
    }
} */