import { fetchAIStream } from './fetchAIStream.svelte';
import { Editor } from '@tiptap/core';
import { marked } from 'marked';

export default async function handleAISubmit(userPrompt: string, insertPos: number, editor: Editor, token: string) {
  if (!userPrompt) return;

  let isStreaming = true;
  let firstInsertion = true;
  try {   
    await fetchAIStream(userPrompt, (chunk: string) => {
      if (!isStreaming) return; // Handle cancellation
      const htmlChunk = marked.parse(chunk, {breaks: true, gfm: true});
      if (firstInsertion) editor.chain().insertContentAt(insertPos, htmlChunk).focus().run();
      else editor.chain().insertContent(htmlChunk).focus().run();
      firstInsertion = false;
    }, token);
  } catch (error) {
    prompt('Error fetching AI response:', (error as Error).message);
    console.error('Error fetching AI response:', error);
  }

  isStreaming = false;
}