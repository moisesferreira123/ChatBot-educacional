<script>
  import { Trash2 } from '@lucide/svelte';
  import { fetchDeleteNote } from '$lib/api/notes';
  import { onMount } from 'svelte';
  import { goto } from '$app/navigation';

  let { id, title, subtitle, updatedAt, onDelete } = $props();


  let token;

  async function openNote() {
    goto(`/notes/${id}`);
  }

  async function deleteNote() {
    try {
      await fetchDeleteNote(id, token);
      onDelete(id);
    } catch(e) {
      alert(`Error: ${e.message}`);
    }
  }

  onMount(() => {
    token = localStorage.getItem("token");
  });

</script>

<div class="flex p-4 hover:bg-gray-50 transition-colors border-b border-b-(--color13)">
  <button onclick={openNote} class="flex flex-col items-start flex-1 cursor-pointer">
    <h3 class="text-start font-semibold text-gray-900 break-all">{title}</h3>
    <p class="text-start text-sm text-gray-500 mt-1 break-word">{subtitle}</p>
    <p class="text-xs text-gray-400 mt-2">{updatedAt}</p>
  </button>
  <button class="flex h-10 w-10 items-center justify-center cursor-pointer hover:bg-red-50 rounded-md shrink-0" onclick={deleteNote}>
    <Trash2 size={16} color="red" />
  </button>
</div>

<style>
  .break-word {
    word-break: break-word;
  }
</style>