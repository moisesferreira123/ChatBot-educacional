<script>
	import { fetchCreateFlashcard } from "$lib/api/flashcards";
	import { createdFlashcard, createdFlashcardInManagement } from "$lib/stores/flashcardStore";
  import { deckManagementOverlay, newFlashcardInDeckInterfaceOverlay, newFlashcardOverlay } from "$lib/stores/overlayStore.svelte";
  import { X } from '@lucide/svelte';

  let { deckId } = $props();
   
  let flashcardFront= $state('');
  let flashcardBack= $state('');

  const token = localStorage.getItem("token");

  async function createFlashcard() {
    try {
      await fetchCreateFlashcard("STANDARD_FLASHCARD", flashcardFront, flashcardBack, "STANDARD_FLASHCARD", deckId, token);
      flashcardFront = '';
      flashcardBack = '';
      if($deckManagementOverlay !== null) createdFlashcardInManagement.set(true);
      createdFlashcard.set(true);
      newFlashcardOverlay.set(false);
      newFlashcardInDeckInterfaceOverlay.set(null);
    } catch(e) {
      alert(`Error: ${e.message}`);
    }
  }
</script>

<div class="fixed inset-0 bg-black/80 flex justify-center items-center z-100">
  <div class="relative bg-white w-full max-w-3xl p-6 rounded-lg shadow-lg mx-4">
    <button onclick={() => {newFlashcardOverlay.set(false); newFlashcardInDeckInterfaceOverlay.set(null);}} class="absolute top-4 right-4 text-gray-500 hover:text-black transition-colors cursor-pointer" >
      <X size={16} />
    </button>
    <h2 class="text-xl font-bold text-(--color14) mb-2">Create new flashcard</h2>
    <div>
      <label for="front" class="text-sm font-semibold">Flashcard Front</label>
      <input id="front" placeholder="Front" bind:value={flashcardFront} class="mt-1 mb-4 w-full h-10 rounded-md border border-(--color13) text-(--color14) px-3 py-2 focus:outline-2 focus:outline-offset-2 focus:outline-black-500 text-base font-medium" required/>
    </div>
    <div>
      <label for="back" class="text-sm font-semibold">Flashcard Back</label>
      <input id="back" placeholder="Back" bind:value={flashcardBack} class="mt-1 mb-6 w-full h-10 rounded-md border border-(--color13) text-(--color14) px-3 py-2 focus:outline-2 focus:outline-offset-2 focus:outline-black-500 text-base font-medium" />
    </div>
    <div class="flex justify-end gap-3">
      <button onclick={() => {newFlashcardOverlay.set(false); newFlashcardInDeckInterfaceOverlay.set(null);}} class="bg-white text-red-500 outline outline-offset-[-1px] outline-red-500 px-3 py-2 rounded transition-colors duration-250 hover:bg-[var(--color12)] hover:cursor-pointer">Cancel</button>
      <button onclick={createFlashcard} class="bg-(--color1) text-white px-4 py-2 rounded transition-colors duration-250 hover:bg-[var(--color6)] hover:cursor-pointer">Create</button>
    </div>
  </div>
</div>