<script>
  import { fetchCreateNote } from "$lib/api/notes/fetchCreateNote";
	import { newNoteOverlay } from "$lib/stores/overlayStore.svelte";
  
  let noteTitle='';
  let noteSubtitle='';

  const token = localStorage.getItem("token");

  async function createNote() {
    try {
      await fetchCreateNote(noteTitle, noteSubtitle, token);
      noteTitle = '';
      noteSubtitle = '';
      newNoteOverlay.set(false);
    } catch(e) {
      alert(e.message);
    }
  }
</script>

<div class="fixed inset-0 bg-black/80 flex justify-center items-center z-50">
  <div class="bg-white p-6 rounded-lg shadow-lg">
    <h2 class="text-xl font-bold mb-2">Create new note</h2>
    <input placeholder="Title" bind:value={noteTitle} class="mb-2 border p-2 w-full" required/>
    <input placeholder="Subtitle (optional)" bind:value={noteSubtitle} class="mb-4 border p-2 w-full" />
    <div class="flex justify-end gap-2">
      <button on:click={() => newNoteOverlay.set(false)} class="bg-gray-300 px-4 py-2 rounded">Cancel</button>
      <button on:click={createNote} class="bg-blue-500 text-white px-4 py-2 rounded">Create</button>
    </div>
  </div>
</div>