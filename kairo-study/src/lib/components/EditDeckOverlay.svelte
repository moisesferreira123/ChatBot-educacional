<script>
	import { fetchUpdateDeck } from "$lib/api/decks/fetchUpdateDeck";
  import { editDeckOverlay } from "$lib/stores/overlayStore.svelte";
	import { onMount } from "svelte";
  import { X } from "@lucide/svelte";

  export let id;
  export let title;
  export let topic;
  export let updateDeckInformations;

  let token;

  async function updateDeck() {
    try {
      await fetchUpdateDeck(id, title, topic, token);
      editDeckOverlay.set(false);
    } catch(e) {
      alert(e.message);
    }
  }

  onMount(() => {
    token = localStorage.getItem("token");
  });

</script>

<div class="fixed inset-0 bg-black/80 flex justify-center items-center z-50">
  <div class="relative bg-white w-full max-w-3xl p-6 rounded-lg shadow-lg mx-4">
    <button onclick={() => editDeckOverlay.set(false)} class="absolute top-4 right-4 text-gray-500 hover:text-black transition-colors cursor-pointer" >
      <X size={16} />
    </button>
    <h2 class="text-xl font-bold text-(--color14) mb-2">Update deck</h2>
    <div>
      <label for="title" class="text-sm font-semibold mb-1">Deck Title</label>
      <input id="title" placeholder="Title" bind:value={title} class="mb-4 w-full h-10 rounded-md border border-(--color13) text-(--color14) px-3 py-2 focus:outline-2 focus:outline-offset-2 focus:outline-black-500 text-base font-medium" required/>
    </div>
    <div>
      <label for="topic" class="text-sm font-semibold mb-1">Topic</label>
      <input id=topic placeholder="Topic (E.g.: Biology)" bind:value={topic} class="mb-6 w-full h-10 rounded-md border border-(--color13) text-(--color14) px-3 py-2 focus:outline-2 focus:outline-offset-2 focus:outline-black-500 text-base font-medium" />
    </div>
    <div class="flex justify-end gap-3">
      <button onclick={() => editDeckOverlay.set(false)} class="bg-white text-(--color14) outline outline-offset-[-1px] outline-(--color13) px-3 py-2 rounded transition-colors duration-250 hover:bg-[var(--color12)] hover:cursor-pointer">Cancel</button>
      <button onclick={() => {updateDeckInformations(title, topic); updateDeck();}} class="bg-(--color1) text-white px-4 py-2 rounded transition-colors duration-250 hover:bg-[var(--color6)] hover:cursor-pointer">Save Changes</button>
    </div>
  </div>
</div>