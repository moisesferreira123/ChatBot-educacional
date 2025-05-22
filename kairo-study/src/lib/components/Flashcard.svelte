<script>
	import { fetchDeleteFlashcard } from "$lib/api/flashcards/fetchDeleteFlashcard";
	import { deletedFlashcard } from "$lib/stores/flashcardStore";
	import { deleteWarningOverlay } from "$lib/stores/overlayStore.svelte";
  import { SquarePen, Trash2 } from "@lucide/svelte";
  import { onMount } from "svelte";

  export let flashcardId;
  export let front;
  export let back;

  let alertTitle = "Are you sure?";
  let alertMessage = `This action cannot be undone. It will permanently delete this flashcard.`;

  let token;

  async function deleteFlashcard() {
    try {
      await fetchDeleteFlashcard(flashcardId, token);
      deleteWarningOverlay.set(null);
      deletedFlashcard.set(true);
    } catch(e) {
      alert(e.message);
    }
  }

  onMount(() => {
    token = localStorage.getItem("token");
  });
</script>

<div class="flex justify-between rounded-lg shadow-xs border border-(--color13) hover:border-gray-400 transition-all p-4">
  <div>
    <h4 class="font-semibold mb-2 text-(--color14)">{front}</h4>
    <p class="font-medium text-gray-500">{back}</p>
  </div>
  <div class="flex gap-2 ml-4">
    <button class="flex items-center justify-center text-(--color14) rounded-md font-medium hover:bg-(--color8) hover:cursor-pointer h-8 w-8">
      <SquarePen size={16} />
    </button>
    <button onclick={() => deleteWarningOverlay.set({alertTitle, alertMessage, deleteButton: deleteFlashcard})} class="flex items-center justify-center text-red-500 rounded-md font-medium hover:bg-(--color8) hover:cursor-pointer h-8 w-8">
      <Trash2 size={16} />
    </button>
  </div>
</div>