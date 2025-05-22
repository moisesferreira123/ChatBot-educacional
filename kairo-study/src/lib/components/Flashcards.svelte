<script>
	import { fetchListFlashcards } from "$lib/api/flashcards/fetchListFlashcards";
	import { sortTypeFlashcards } from "$lib/stores/sortType";
  import { Search, ArrowUpDown, SquarePen, Trash2 } from "@lucide/svelte";
	import { onMount } from "svelte";
	import { sortFlashcardsOverlay } from "$lib/stores/overlayStore.svelte";
	import Flashcard from "./Flashcard.svelte";
	import SortFlashcardsOverlay from "./SortFlashcardsOverlay.svelte";
	import { deletedFlashcard } from "$lib/stores/flashcardStore";

  export let deckId;

  let flashcards = [];
  let currentPage = 0;
  let pageSize = 10;
  let wordFilter = "";
  let dominatedFlashcardFilter = false;
  let undominatedFlashcardFilter = false;
  // let loading = false;
  let finished = false;
  let sentinel;

  let token;

  async function loadFlashcards() {
    if(finished) return;
    try{
      const newFlashcards = await fetchListFlashcards(currentPage, pageSize, wordFilter, dominatedFlashcardFilter, undominatedFlashcardFilter, deckId, $sortTypeFlashcards, token);
      if(!newFlashcards || !newFlashcards.content || newFlashcards.content.length === 0) {
        finished = true;
      } else {
        flashcards = [...flashcards, ...newFlashcards.content];
        ++currentPage;
      }
    } catch(e) {
      alert(e.message);
    }
  }

  function updateWordFilter(event) {
    wordFilter = event.target.value;
    resetPages();
    loadFlashcards();
  }

  function changeSort(newSort) {
    sortTypeFlashcards.set(newSort);
    resetPages();
    loadFlashcards();
  }

  function resetPages() {
    currentPage = 0;
    flashcards = [];
    finished = false;
  }

  deletedFlashcard.subscribe(async (value) => {
    if(value) {
      resetPages();
      loadFlashcards();
      deletedFlashcard.set(false);
    }
  });

  onMount(() => {
    token = localStorage.getItem("token");
    sortTypeFlashcards.set("lastReviewedAtDesc")
    const observer = new IntersectionObserver(async ([entry]) => {
      if(entry.isIntersecting) await loadFlashcards();
    }, {
      root: null,
      threshold: 0
    });

    if(sentinel) observer.observe(sentinel);
  });


</script>

<div class="overflow-hidden overflow-y-auto h-full pr-4">
  <div class="flex gap-2">
    <div class="relative mb-4 grow">
      <Search class="absolute left-3 top-1/2 transform -translate-y-1/2" size={16} color="var(--color16)" />
      <input class="w-full h-10 border border-(--color13) py-2 pl-10 pr-3 text-(--color14) font-medium rounded-md bg-white focus:outline-none focus:border-gray-400" placeholder="Search flashcards..."
             bind:value={wordFilter} oninput={updateWordFilter} >
    </div>
    <div class="relative">
      <!-- TODO: Colocar filtro para cards dominados e não dominados -->
      <button data-user-button onclick={() => sortFlashcardsOverlay.update(open => !open)} class="inline-flex transition-colors ring-offset-background px-3 bg-white border border-input rounded-md whitespace-nowrap gap-2 justify-center items-center h-10 cursor-pointer border-(--color13) hover:bg-(--color8)">
        <ArrowUpDown size={16} color="var(--color14)" />
        <p class="font-semibold text-sm text-(--color14)">Sort</p>
      </button>
      {#if $sortFlashcardsOverlay}
        <SortFlashcardsOverlay 
          changeSort={(newSort) => changeSort(newSort)}
        />
      {/if}
    </div>
  </div>
  <div class="flex flex-col gap-2">
    {#each flashcards as flashcard}
      <Flashcard 
        flashcardId={flashcard.id}
        front={flashcard.front}
        back={flashcard.back}
      />
    {/each}
    <div bind:this={sentinel} class="h-1"></div>
  </div>
</div>