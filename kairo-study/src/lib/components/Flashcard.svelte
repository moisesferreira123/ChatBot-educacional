<script>
	import { fetchDeleteFlashcard } from "$lib/api/flashcards";
	import { fetchUpdateFlashcard } from "$lib/api/flashcards";
	import { deletedFlashcard, updatedFlashcard } from "$lib/stores/flashcardStore";
	import { actionOverlay, deleteWarningOverlay, flashcardDetailsOverlay } from "$lib/stores/overlayStore.svelte";
  import { SquarePen, Trash2 } from "@lucide/svelte";
  import { onMount } from "svelte";

  let { flashcardId, front, back } = $props();

  let alertTitle = "Are you sure?";
  let alertMessage = `This action cannot be undone. It will permanently delete this flashcard.`;

  let token;

  async function updateFlashcard(newFront, newBack) {
    try {
      await fetchUpdateFlashcard(flashcardId, "STANDARD_FLASHCARD", newFront, newBack, "STANDARD_FLASHCARD", token);
      actionOverlay.set(null);
      updatedFlashcard.set(true);
    } catch(e) {
      alert(`Error: ${e.message}`);
    }
  }

  async function deleteFlashcard() {
    try {
      await fetchDeleteFlashcard(flashcardId, token);
      deleteWarningOverlay.set(null);
      deletedFlashcard.set(true);
    } catch(e) {
      alert(`Error: ${e.message}`);
    }
  }

  onMount(() => {
    token = localStorage.getItem("token");
  });
</script>

<div class="flex justify-between rounded-lg shadow-2xs border border-(--color13) hover:border-gray-400 transition-all">
  <button onclick={() => flashcardDetailsOverlay.set({flashcardId})} class="w-full p-4 pr-0 text-left">
    <h4 class="font-semibold mb-2 text-(--color14)">{front}</h4>
    <p class="font-medium text-gray-500">{back}</p>
  </button>
  <div class="flex gap-2 p-4">
    <button onclick={() => actionOverlay.set({title: "Update Flashcard", textLabel1: "Front", text1: front, textLabel2: "Back", text2: back, actionButton: updateFlashcard})} class="flex items-center justify-center text-(--color14) rounded-md font-medium hover:bg-(--color8) hover:cursor-pointer h-8 w-8">
      <SquarePen size={16} />
    </button>
    <button onclick={() => deleteWarningOverlay.set({alertTitle, alertMessage, deleteButton: deleteFlashcard})} class="flex items-center justify-center text-red-500 rounded-md font-medium hover:bg-(--color8) hover:cursor-pointer h-8 w-8">
      <Trash2 size={16} />
    </button>
  </div>
</div>