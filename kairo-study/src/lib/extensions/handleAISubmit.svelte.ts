import { fetchAIStream } from './fetchAIStream.svelte';
import { Editor } from '@tiptap/core';

export default async function handleAISubmit(userPrompt: string, insertPos: number, editor: Editor) {
  if (!userPrompt) return;

  let isStreaming = true;
  let firstInsertion = true;
  try {   
    await fetchAIStream(userPrompt, (chunk: string) => {
      if (!isStreaming) return; // Handle cancellation
      //console.log(chunk);
      //let overrideOptions = {updateSelection: true, applyPasteRules: true, applyInputRules: true};
      if (firstInsertion) editor.chain().insertContentAt(insertPos, chunk).focus().run();
      else editor.chain().insertContent(chunk).focus().run();
      firstInsertion = false;
    });
  } catch (error) {
    prompt('Error fetching AI response:', (error as Error).message);
  }

  isStreaming = false;
}