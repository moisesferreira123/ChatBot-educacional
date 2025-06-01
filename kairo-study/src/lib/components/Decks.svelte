<script>
  import { Search, Funnel, ArrowUpDown, ArrowUp, ArrowDown } from '@lucide/svelte';
  import { fetchListDecks } from '$lib/api/decks';
  import { onMount } from 'svelte';
  import { topicFilter } from '$lib/stores/filter';
  import { sortTypeDecks } from '$lib/stores/sortType';
  import { createdDeck, deletedDeck, updatedDeck } from '$lib/stores/deckStore';
  import { deckManagementOverlay, filterDecksOverlay, newFlashcardInDeckInterfaceOverlay, sortDecksOverlay } from '$lib/stores/overlayStore.svelte';
	import { createdFlashcard, deletedFlashcard } from '$lib/stores/flashcardStore';
  import Deck from './Deck.svelte';
  import DeckManagementOverlay from './DeckManagementOverlay.svelte';
	import SortDecksOverlay from './SortDecksOverlay.svelte';
	import NewFlashcardOverlay from './NewFlashcardOverlay.svelte';
	import FilterDecksOverlay from './FilterDecksOverlay.svelte';

  let allDecks = [];
  let visibleDecks = [];
  let currentPage = 0;
  let pageSize = 12;
  let titleFilter = "";
  let hasMore = true;
  let displayCount = 12;

  let token;

  async function loadDecks() {
    if(!hasMore) return;
    try {
      const data = await fetchListDecks(currentPage, pageSize, titleFilter, $topicFilter, $sortTypeDecks, token);
      allDecks = [...allDecks, ...data.content];
      updateVisibleDecks();
      hasMore = !data.last;
      currentPage++;
    } catch(e) {
      alert(`Error: ${e.message}`);
    }
  }

  function updateVisibleDecks() {
    visibleDecks = allDecks.slice(0, displayCount);
  }

  function showMore() {
    displayCount += 12;
    updateVisibleDecks();
    if(displayCount > allDecks.length && hasMore) {
      loadDecks();
    }
  }

  function showLess() {
    displayCount = Math.max(12, displayCount-12);
    updateVisibleDecks();
  }

  function changeSort(newSort) {
    if($sortTypeDecks !== newSort) {
      sortTypeDecks.set(newSort);
      resetPages();
      loadDecks();
    }
  }

  function updateTitleFilter(event) {
    titleFilter = event.target.value;
    resetPages();
    loadDecks();
  }

  function changeTopicFilter(newTopic) {
    topicFilter.set(newTopic);
    resetPages();
    loadDecks();
  }

  function resetPages() {
    currentPage = 0;
    displayCount = 12;
    visibleDecks = [];
    allDecks = [];
    hasMore = true;
  }

  async function reloadDecks() {
    resetPages();
    const data = await fetchListDecks(0, pageSize, titleFilter, $topicFilter, $sortTypeDecks, token);
    allDecks = data.content;
    updateVisibleDecks();
    hasMore = !data.last;
    currentPage = 1;
  }

  createdDeck.subscribe(async (value) => {
      if (value) {
        reloadDecks();
        createdDeck.set(false);
      }
    });

    deletedDeck.subscribe(async (value) => {
      if (value) {
        reloadDecks();
        deletedDeck.set(false);
      }
    });

    updatedDeck.subscribe(async (value) => {
      if (value) {
        reloadDecks();
        updatedDeck.set(false);
      }
    });

    createdFlashcard.subscribe(async (value) => {
      if(value) {
        reloadDecks();
        createdFlashcard.set(false);
      }
    });

    deletedFlashcard.subscribe(async (value) => {
      if(value) {
        reloadDecks();
        deletedFlashcard.set(false);
      }
    });

  onMount(() => {
    token = localStorage.getItem("token");
    sortTypeDecks.set("lastReviewedAtDesc");
    loadDecks();
  });

</script>

{#if $newFlashcardInDeckInterfaceOverlay !== null}
  <NewFlashcardOverlay 
    deckId={$newFlashcardInDeckInterfaceOverlay.id}
  />
{/if}
<div class="relative mb-4">
  <Search class="absolute left-3 top-1/2 transform -translate-y-1/2" size={16} color="var(--color16)" />
  <input class="w-full h-10 border border-(--color13) py-2 pl-10 pr-3 text-(--color14) font-medium rounded-md focus:outline-offset-2 focus:outline-black-500 focus:outline-2 bg-white" placeholder="Search decks..."
         bind:value={titleFilter} oninput={updateTitleFilter}>
</div>
<div class="flex justify-between items-center mb-6">
  <div class="inline-flex ring-offset-background px-3 bg-white border border-input rounded-md whitespace-nowrap justify-center items-center h-9 border-(--color13)">
    <p class="font-semibold text-sm text-(--color14) cursor-default">Decks</p>
  </div>
  <div class="flex gap-1">
    <div class="relative">
      <button filter-button onclick={() => filterDecksOverlay.update(open => !open)} class="inline-flex mr-1 transition-colors ring-offset-background px-3 bg-white border border-input rounded-md whitespace-nowrap gap-2 justify-center items-center h-9 cursor-pointer border-(--color13) hover:bg-(--color8)">
        <Funnel size={16} color="var(--color14)" />
        <p class="font-semibold text-sm text-(--color14)">Filter</p>
      </button>
      {#if $filterDecksOverlay}
        <FilterDecksOverlay
          changeFilter={(newFilter) => changeTopicFilter(newFilter)}
        />
      {/if}
    </div>
    <div class="relative">
      <button data-user-button onclick={() => sortDecksOverlay.update(open => !open)} class="inline-flex transition-colors ring-offset-background px-3 bg-white border border-input rounded-md whitespace-nowrap gap-2 justify-center items-center h-9 cursor-pointer border-(--color13) hover:bg-(--color8)">
        <ArrowUpDown size={16} color="var(--color14)" />
        <p class="font-semibold text-sm text-(--color14)">Sort</p>
      </button>
      {#if $sortDecksOverlay}
        <SortDecksOverlay
          changeSort={(newSort) => changeSort(newSort)}
        />
      {/if}
    </div>
  </div>
</div>
<div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
  {#each visibleDecks as deck}
    <Deck
      id={deck.id} 
      title={deck.title} 
      topic={deck.topic} 
      lastReviewedAt={new Date(deck.lastReviewedAt)} 
    />
  {/each}
</div>
<div class="flex justify-center gap-2 py-2">
  <button 
    onclick={showLess} 
    class="flex h-10 w-10 justify-center items-center rounded-full border border-(--color13) bg-white shadow cursor-pointer hover:bg-gray-50 disabled:bg-white disabled:opacity-50 disabled:cursor-default" 
    disabled={displayCount <= 12}>
    <ArrowUp size={20} />
  </button>
  <button 
    onclick={showMore} 
    class="flex h-10 w-10 justify-center items-center rounded-full border border-(--color13) bg-white shadow cursor-pointer hover:bg-gray-50 disabled:bg-white disabled:opacity-50 disabled:cursor-default" 
    disabled={allDecks.length === 0 || (!hasMore && displayCount >= allDecks.length)}>
    <ArrowDown size={20} />
  </button>   
</div>

{#if $deckManagementOverlay !== null}
  <DeckManagementOverlay 
    id={$deckManagementOverlay.id}
    title={$deckManagementOverlay.title}
    topic={$deckManagementOverlay.topic}
  />
{/if}