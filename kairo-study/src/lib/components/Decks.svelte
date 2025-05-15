<script>
  import { Search, Funnel, ArrowUpDown, ArrowUp, ArrowDown } from '@lucide/svelte';
  import { fetchListDecks } from '$lib/api/decks/fetchListDecks';
  import { onMount } from 'svelte';
  import { topicFilter } from '$lib/stores/filter';
  import { sortTypeDecks } from '$lib/stores/sortType';
  import { deckCreated } from '$lib/stores/deckStore';
  import Deck from './Deck.svelte';

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
    const data = await fetchListDecks(currentPage, pageSize, titleFilter, $topicFilter, $sortTypeDecks, token);
    allDecks = [...allDecks, ...data.content];
    updateVisibleDecks();
    hasMore = !data.last;
    currentPage++;
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
    sortTypeDecks.set(newSort);
    resetPages();
    loadDecks();
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
    allDecks = [];
    hasMore = true;
  }

  async function reloadDecks() {
    visibleDecks = [];
    resetPages();
    const data = await fetchListDecks(0, pageSize, titleFilter, $topicFilter, $sortTypeDecks, token);
    allDecks = data.content;
    updateVisibleDecks();
    hasMore = !data.last;
    currentPage = 1;
  }

  deckCreated.subscribe(async (value) => {
      if (value) {
        reloadDecks();
        deckCreated.set(false);
      }
    });

  onMount(() => {
    token = localStorage.getItem("token");
    loadDecks();
  });

</script>

<div class="relative mb-4">
  <Search class="absolute left-3 top-1/2 transform -translate-y-1/2" size={16} color="var(--color16)" />
  <input class="w-full h-10 border border-(--color13) py-2 pl-10 pr-3 text-(--color14) font-medium rounded-md focus:outline-offset-2 focus:outline-black-500 focus:outline-2 bg-white" placeholder="Search decks..."
         bind:value={titleFilter} oninput={updateTitleFilter}>
</div>
<div class="flex justify-between items-center mb-8">
  <div class="inline-flex ring-offset-background px-3 bg-white border border-input rounded-md whitespace-nowrap justify-center items-center h-9 border-(--color13)">
    <p class="font-semibold text-sm text-(--color14) cursor-default">Decks</p>
  </div>
  <div>
    <button data-user-button onclick={() => filterTopicOverlay.update(open => !open)} class="inline-flex transition-colors ring-offset-background px-3 bg-white border border-input rounded-md whitespace-nowrap gap-2 justify-center items-center h-9 cursor-pointer border-(--color13) hover:bg-(--color8)">
      <Funnel size={16} color="var(--color14)" />
      <p class="font-semibold text-sm text-(--color14)">Filter</p>
    </button>
    <button data-user-button onclick={() => sortDecksOverlay.update(open => !open)} class="inline-flex transition-colors ring-offset-background px-3 bg-white border border-input rounded-md whitespace-nowrap gap-2 justify-center items-center h-9 cursor-pointer border-(--color13) hover:bg-(--color8)">
      <ArrowUpDown size={16} color="var(--color14)" />
      <p class="font-semibold text-sm text-(--color14)">Sort</p>
    </button>
  </div>
</div>
<div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
  {#each visibleDecks as deck}
    <Deck
      id={deck.id} 
      title={deck.title} 
      topic={deck.topic} 
      lastReviewedAt={new Date(deck.lastReviewedAt)} 
      onDelete={() => {
        resetPages();
        loadNotes();
      }}
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
