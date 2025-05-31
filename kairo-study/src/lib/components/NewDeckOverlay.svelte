<script>
	import { fetchCreateDeck } from "$lib/api/decks";
  import { newDeckOverlay } from "$lib/stores/overlayStore.svelte";
  import { createdDeck } from "$lib/stores/deckStore";
  import { X } from '@lucide/svelte';

  let deckTitle='';
  let deckTopic='';

  const token = localStorage.getItem("token");

  async function createDeck() {
    try {
      await fetchCreateDeck(deckTitle, deckTopic, token);
      deckTitle = '';
      deckTopic = '';
      newDeckOverlay.set(false);
      createdDeck.set(true);
    } catch(e) {
      alert(`Error: ${e.message}`);
    }
  }
</script>

<div class="fixed inset-0 bg-black/80 flex justify-center items-center z-50">
  <div class="relative bg-white p-6 rounded-lg shadow-lg mx-4">
    <button class="absolute top-4 right-4 text-gray-500 hover:text-black transition-colors cursor-pointer" onclick={() => newDeckOverlay.set(false)} >
      <X size={16} />
    </button>
    <h2 class="text-xl font-bold text-(--color14)">Create new deck</h2>
    <input placeholder="Title" bind:value={deckTitle} class="mb-4 mt-6 w-full h-10 rounded-md border border-(--color13) text-(--color14) px-3 py-2 focus:outline-2 focus:outline-offset-2 focus:outline-black-500 text-base font-medium" required/>
    <input placeholder="Topic (E.g.: Biology)" bind:value={deckTopic} class="mb-6 w-full h-10 rounded-md border border-(--color13) text-(--color14) px-3 py-2 focus:outline-2 focus:outline-offset-2 focus:outline-black-500 text-base font-medium" />
    <div class="flex justify-end gap-3">
      <button onclick={() => newDeckOverlay.set(false)} class="bg-white text-red-500 outline outline-offset-[-1px] px-3 py-2 rounded transition-colors duration-250 hover:bg-[var(--color12)] hover:cursor-pointer">Cancel</button>
      <button onclick={createDeck} class="bg-(--color1) text-white px-4 py-2 rounded transition-colors duration-250 hover:bg-[var(--color6)] hover:cursor-pointer">Create</button>
    </div>
  </div>
</div>