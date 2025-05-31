<script>
  import { actionOverlay, deckManagementOverlay, deleteWarningOverlay, flashcardDetailsOverlay, newFlashcardOverlay } from "$lib/stores/overlayStore.svelte";
  import { X, Settings, Trash2, Plus } from "@lucide/svelte";
  import { fetchDeleteDeck } from "$lib/api/decks";
	import { onMount } from "svelte";
	import { deletedDeck, updatedDeck } from "$lib/stores/deckStore";
	import { fetchGetFlashcardsTotal } from "$lib/api/decks";
	import { createdFlashcardInManagement, deletedFlashcard } from "$lib/stores/flashcardStore";
	import { fetchUpdateDeck } from "$lib/api/decks";
	import DeleteWarningOverlay from "./DeleteWarningOverlay.svelte";
	import NewFlashcardOverlay from "./NewFlashcardOverlay.svelte";
	import Flashcards from "./Flashcards.svelte";
	import ActionOverlay from "./ActionOverlay.svelte";
	import FlashcardDetails from "./FlashcardDetails.svelte";
  
  let { id, title, topic } = $props();

  const alertTitle = "Are you sure?";
  const alertMessage = `This action cannot be undone. It will permanently delete the "${title}" deck and all of its flashcards.`;
  
  let flashcards = $state(0);
  let token;

  function updateDeckTitleAndTopic(deckTitle, deckTopic) {
    title = deckTitle;
    topic = deckTopic;
  }

  async function updateDeck(newTitle, newTopic) {
    try {
      await fetchUpdateDeck(id, newTitle, newTopic, token);
      actionOverlay.set(null);
      updatedDeck.set(true);
      updateDeckTitleAndTopic(newTitle, newTopic);
    } catch(e) {
      alert(`Error: ${e.message}`);
    }
  }

  async function deleteDeck() {
    try {
      await fetchDeleteDeck(id, token);
      deleteWarningOverlay.set(null);
      deckManagementOverlay.set(null);
      deletedDeck.set(true);
    } catch(e) {
      alert(`Error: ${e.message}`);
    }
  }

  async function getFlashcardsTotal() {
    try {
      flashcards = await fetchGetFlashcardsTotal(id, token);
    } catch(e) {
      alert(`Error: ${e.message}`);
    }
  }

  createdFlashcardInManagement.subscribe(async (value) => {
    if(value) {
      getFlashcardsTotal();
      createdFlashcardInManagement.set(false);
    }
  });

  deletedFlashcard.subscribe(async (value) => {
    if(value) {
      getFlashcardsTotal();
      deletedFlashcard.set(false);
    }
  });

  onMount(() => {
    token = localStorage.getItem("token");
    getFlashcardsTotal();
  });

</script>

{#if $deleteWarningOverlay}
  <DeleteWarningOverlay 
    alertTitle = {$deleteWarningOverlay.alertTitle}
    alertMessage = {$deleteWarningOverlay.alertMessage}
    cancelButton = {() => deleteWarningOverlay.set(null)}
    deleteButton = {$deleteWarningOverlay.deleteButton}
  />
{/if}
{#if $flashcardDetailsOverlay}
  <FlashcardDetails 
    flashcardId={$flashcardDetailsOverlay.flashcardId}
  />
{/if}
{#if $actionOverlay}
  <ActionOverlay 
    title={$actionOverlay.title}
    textLabel1={$actionOverlay.textLabel1}
    text1={$actionOverlay.text1}
    textLabel2={$actionOverlay.textLabel2}
    text2={$actionOverlay.text2}
    actionButton={$actionOverlay.actionButton}
  />
{:else if $newFlashcardOverlay} 
  <NewFlashcardOverlay 
    deckId={id}
  />
{:else}
  <div class="fixed inset-0 z-50 bg-black/80">
    <div class="fixed left-[50%] top-[50%] z-50 w-full translate-x-[-50%] translate-y-[-50%] gap-4 bg-white p-7 shadow-lg duration-200 sm:rounded-lg max-w-3xl h-[80vh] flex flex-col">
      <button onclick={() => deckManagementOverlay.set(null)} class="absolute top-2.5 right-2.5 text-gray-500 hover:text-black transition-colors cursor-pointer">
        <X size={16} />
      </button>
      <div class="flex flex-col space-y-1.5 text-center sm:text-left">
        <div class="flex items-center justify-between gap-2">
          <h2 style="max-width: calc(100% - 265px);" class="wrap-break-word font-bold text-wrap tracking-tight text-xl text-(--color14)">{title}</h2>
          <div class="flex gap-2 ">
            <button onclick={() => actionOverlay.set({title: "Update Deck", textLabel1: "Deck Title", text1: title, textLabel2: "Topic", text2: topic, actionButton: updateDeck})} class="inline-flex shrink-0 items-center justify-center text-(--color14) gap-2 text-sm font-semibold transition-colors border border-(--color13) hover:bg-(--color8) hover:cursor-pointer h-9 rounded-md px-3">
              <Settings size={16} color="var(--color14)" />
              Edit Deck
            </button>
            <button onclick={() => deleteWarningOverlay.set({alertTitle, alertMessage, deleteButton: deleteDeck})} class="inline-flex shrink-0 items-center text-white bg-red-500 justify-center gap-2 text-sm font-semibold transition-colors hover:bg-red-500/85 hover:cursor-pointer h-9 rounded-md px-3">
              <Trash2 size={16}/>
              Delete Deck
            </button>
          </div>
        </div>
      </div>
      <div class="flex-grow">
        <div class="flex justify-between items-center mb-0">
          <h3 class="text-lg font-semibold text-(--color14)">Flashcards in this deck ({flashcards})</h3>
          <button onclick={() => newFlashcardOverlay.set(true)} class="flex items-center justify-center text-(--color14) gap-2 text-sm font-semibold transition-colors border border-(--color13) hover:bg-(--color8) hover:cursor-pointer h-9 rounded-md px-3">
            <Plus size={16} color="var(--color14)" />
            Add Flashcards
          </button>
        </div>
      </div>
      <Flashcards 
        deckId={id}
      />
    </div>
  </div>
{/if}
