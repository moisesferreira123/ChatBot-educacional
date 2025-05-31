<script>
	import { goto } from "$app/navigation";
  import { fetchCreateNote } from "$lib/api/notes";
	import { newNoteOverlay } from "$lib/stores/overlayStore.svelte";
  import { X } from '@lucide/svelte';

  let noteTitle='';
  let noteSubtitle='';
  let createdNoteId;

  const token = localStorage.getItem("token");

  async function createNote() {
    try {
      createdNoteId = await fetchCreateNote(noteTitle, noteSubtitle, token);
      noteTitle = '';
      noteSubtitle = '';
      newNoteOverlay.set(false);
      console.log(createdNoteId);
      goto(`/notes/${createdNoteId}`);
    } catch(e) {
      alert(`Error: ${e.message}`);
    }
  }
</script>

<div class="fixed inset-0 bg-black/80 flex justify-center items-center z-50">
  <div class="relative bg-white p-6 rounded-lg shadow-lg mx-4">
    <button class="absolute top-4 right-4 text-gray-500 hover:text-black transition-colors cursor-pointer" onclick={() => newNoteOverlay.set(false)} >
      <X size={16} />
    </button>
    <h2 class="text-xl font-bold text-(--color14)">Create new note</h2>
    <input placeholder="Title" bind:value={noteTitle} class="mb-4 mt-6 w-full h-10 rounded-md border border-(--color13) text-(--color14) px-3 py-2 focus:outline-2 focus:outline-offset-2 focus:outline-black-500 text-base font-medium" required/>
    <input placeholder="Subtitle (optional)" bind:value={noteSubtitle} class="mb-6 w-full h-10 rounded-md border border-(--color13) text-(--color14) px-3 py-2 focus:outline-2 focus:outline-offset-2 focus:outline-black-500 text-base font-medium" />
    <div class="flex justify-end gap-3">
      <button onclick={() => newNoteOverlay.set(false)} class="bg-white text-red-500 outline outline-offset-[-1px] px-3 py-2 rounded transition-colors duration-250 hover:bg-[var(--color12)] hover:cursor-pointer">Cancel</button>
      <button onclick={createNote} class="bg-(--color1) text-white px-4 py-2 rounded transition-colors duration-250 hover:bg-[var(--color6)] hover:cursor-pointer">Create</button>
    </div>
  </div>
</div>