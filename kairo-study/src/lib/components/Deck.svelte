<script>
  import { Clock, Folder, Settings } from '@lucide/svelte';
  import { onMount } from 'svelte';
  import { formatDate } from '$lib/stores/formatDate';
	import { fetchGetDueFlashcardsTotal } from '$lib/api/decks';
	import { fetchGetMasteryLevel } from '$lib/api/decks';
  import { deckManagementOverlay, newFlashcardInDeckInterfaceOverlay } from '$lib/stores/overlayStore.svelte';
	import { goto } from '$app/navigation';
	import { allDueFlashcards, flashcardReview } from '$lib/stores/flashcardStore';

  let { id, title, topic, lastReviewedAt } = $props();

  let token;
  let dueFlashcards = $state(0);
  let masteryLevel = $state(0);

  async function getDueFlashcardsTotal() {
    try {
      dueFlashcards = await fetchGetDueFlashcardsTotal(id, token);
    } catch(e) {
      alert(`Error: ${e.message}`);
    }
  }

  async function getMasteryLevel() {
    try {
      masteryLevel = await fetchGetMasteryLevel(id, token);
    } catch(e) {
      alert(`Error: ${e.message}`);
    }
  }

  function toGoReview() {
    if(dueFlashcards === 0) alert("There are no cards to review.");
    flashcardReview.set({id});
    allDueFlashcards.set(false);
    goto("/flashcardReview");
  }

  onMount(() => {
    token = localStorage.getItem("token");
    getDueFlashcardsTotal();
    getMasteryLevel();
  });

</script>

<div class="rounded-lg border border-(--color13) bg-white text-card-foregraund hover:shadow-md transition-shador duration-300">
  <div class="flex flex-col space-y-1.5 p-6 pb-2">
    <div class="flex justify-between items-start gap-2">
      <div class="max-w-[calc(100%-101px)]">
        <h3 class="w-full wrap-break-word font-bold tracking-tight text-lg">{title}</h3>
        <div class="flex items-center mt-1 text-sm font-normal text-gray-500">
          <Folder class="mr-1" size={16} color="var(--color17)"  />
          {topic}
        </div>
      </div>
      <div class="flex gap-2 items-center">
        <div class="bg-gray-100 text-gray-700 text-sm font-medium px-2 py-1 rounded">
          {dueFlashcards} due cards
        </div>
        <button onclick={() => {deckManagementOverlay.set({id, title, topic}); console.log($deckManagementOverlay);}} class="flex items-center shrink-0 justify-center cursor-pointer hover:bg-(--color8) h-7 w-7 rounded">
          <Settings size={16} color="var(--color14)" />
        </button>
      </div>
    </div>
  </div>
  <div class="p-6 pt-0 rounded-lg">
    <div class="flex justify-between text-sm">
      <div class="flex items-center font-normal text-gray-500">
        <Clock class="mr-1" size={16} color="var(--color17)" />
        {#if formatDate(lastReviewedAt) === formatDate(new Date(null))}
          Reviewed: Never reviewed
        {:else}
          Reviewed: {formatDate(lastReviewedAt)}
        {/if}
      </div>
      <div class="flex items-center">
        <span class="text-gray-500 font-normal mr-1">Mastery:</span>
        {#if Math.round(masteryLevel) < 50}
          <span class="font-medium text-red-600">{Math.round(masteryLevel)}%</span>
        {:else if Math.round(masteryLevel) < 80}
          <span class="font-medium text-yellow-600">{Math.round(masteryLevel)}%</span>
        {:else}
          <span class="font-medium text-green-600">{Math.round(masteryLevel)}%</span>
        {/if}
      </div>
    </div>
    <div class="mt-3 pt-3 border-t border-t-(--color13) flex justify-between">
      <button onclick={toGoReview} class="flex items-center justify-center font-semibold rounded-md h-9 px-3 cursor-pointer text-sm text-(--color1) hover:bg-(--color8)">Study</button>
      <button onclick={() => newFlashcardInDeckInterfaceOverlay.set({id})} class="flex items-center justify-center font-semibold rounded-md h-9 px-3 cursor-pointer text-sm text-(--color14) hover:bg-(--color8)">Add Flashcard</button>
    </div>
  </div>
</div>
