import { Editor } from '@tiptap/core';
import { marked } from 'marked';
import { fetchAICompletion } from '$lib/api/ai';


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
        await fetchAICompletion(
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
