<script>
  import { deckManagementOverlay, deleteWarningOverlay, editDeckOverlay } from "$lib/stores/overlayStore.svelte";
  import { X, Settings, Search, Trash2, Plus } from "@lucide/svelte";
  import { fetchDeleteDeck } from "$lib/api/decks/fetchDeleteDeck";
	import { onMount } from "svelte";
	import DeleteWarningOverlay from "./DeleteWarningOverlay.svelte";
	import { deletedDeck } from "$lib/stores/deckStore";
	import EditDeckOverlay from "./EditDeckOverlay.svelte";
  export let id;
  export let title;
  export let topic;

  let alertTitle = "Are you sure?";
  let alertMessage = `This action cannot be undone. It will permanently delete the "${title}" deck and all of its flashcards.`;
  let word;
  let token;

  function updateDeckTitleAndTopic(deckTitle, deckTopic) {
    title = deckTitle;
    topic = deckTopic;
  }

  async function deleteDeck() {
    try {
      await fetchDeleteDeck(id, token);
      deleteWarningOverlay.set(false);
      deckManagementOverlay.set(null);
      deletedDeck.set(true);
    } catch(e) {
      alert(e.message);
    }
  }

  onMount(() => {
    token = localStorage.getItem("token");
  });

</script>

{#if $deleteWarningOverlay}
  <DeleteWarningOverlay 
    alertTitle = {alertTitle}
    alertMessage = {alertMessage}
    cancelButton = {() => deleteWarningOverlay.set(false)}
    deleteButton = {deleteDeck}
  />
{/if}
{#if $editDeckOverlay}
  <EditDeckOverlay
    id={id}
    title={title}
    topic={topic}
    updateDeckInformations={(deckTitle, deckTopic) => updateDeckTitleAndTopic(deckTitle, deckTopic)}
  />
{:else}
  <div class="fixed inset-0 z-50 bg-black/80">
    <div class="fixed left-[50%] top-[50%] z-50 w-full translate-x-[-50%] translate-y-[-50%] gap-4 bg-white p-7 shadow-lg duration-200 sm:rounded-lg max-w-3xl h-[80vh] flex flex-col">
      <button onclick={() => deckManagementOverlay.set(null)} class="absolute top-2.5 right-2.5 text-gray-500 hover:text-black transition-colors cursor-pointer">
        <X size={16} />
      </button>
      <div class="flex flex-col space-y-1.5 text-center sm:text-left flex-shrink-0">
        <div class="flex items-center justify-between">
          <h2 class="font-semibold tracking-tight text-xl">{title}</h2>
          <div class="flex gap-2">
            <button onclick={() => editDeckOverlay.set(true)} class="inline-flex items-center justify-center text-(--color14) gap-2 text-sm font-semibold transition-colors border border-(--color13) hover:bg-(--color8) hover:cursor-pointer h-9 rounded-md px-3">
              <Settings size={16} color="var(--color14)" />
              Edit Deck
            </button>
            <button onclick={() => deleteWarningOverlay.set(true)} class="inline-flex items-center text-white bg-red-500 justify-center gap-2 text-sm font-semibold transition-colors hover:bg-red-500/85 hover:cursor-pointer h-9 rounded-md px-3">
              <Trash2 size={16}/>
              Delete Deck
            </button>
          </div>
        </div>
      </div>
      <div class="flex-grow">
        <div class="flex justify-between items-center mb-4">
          <h3 class="text-lg font-medium">Flashcards in this deck(5)</h3>
          <button class="flex items-center justify-center text-(--color14) gap-2 text-sm font-semibold transition-colors border border-(--color13) hover:bg-(--color8) hover:cursor-pointer h-9 rounded-md px-3">
            <Plus size={16} color="var(--color14)" />
            Add Flashcards
          </button>
        </div>
      </div>
      <div class="overflow-hidden h-full pr-4">

      </div>
    </div>
  </div>
{/if}
