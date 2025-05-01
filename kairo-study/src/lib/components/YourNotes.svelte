<script>
  import { Search, ArrowUpDown } from '@lucide/svelte';
	import { fetchListNotes } from '$lib/api/notes/fetchListNotes';
  import { onMount } from 'svelte';
  import NotesHome from './NotesHome.svelte';

  let allNotes = [];
  let visibleNotes = [];
  let currentPage = 0;
  let pageSize = 5;
  let titleFilter = "";
  let sortType = "updateAtDesc";
  let hasMore = true;
  let displayCount = 10;

  const token = localStorage.getItem("token");

  async function loadNotes() {
    if(!hasMore) return;
    const data = await fetchListNotes(currentPage, pageSize, titleFilter, sortType, token);
    allNotes = [...allNotes, ...data.content];
    updateVisibleNotes();
    hasMore = !data.last;
    currentPage++;
  }

  function updateVisibleNotes() {
    visibleNotes = allNotes.slice(0, displayCount);
  }

  function showMore() {
    displayCount += 5;
    updateVisibleNotes();
    if(displayCount > allNotes.length && hasMore) {
      loadNotes();
    }
  }

  function showLess() {
    displayCount = Math.max(5, displayCount-5);
    updateVisibleNotes();
  }

  function changeSort(newSort) {
    sortType = newSort;
    resetPages();
    loadNotes();
  }

  function updateTitleFilter(event) {
    titleFilter = event.target.value;
    resetPages();
    loadNotes();
  }

  function resetPages() {
    currentPage = 0;
    displayCount = 5;
    allNotes= [];
    hasMore = true;
  }

  onMount(() => {
    loadNotes();
  });

</script>

<div class="flex justify-between items-center mb-[16px]">
  <h2 class="text-[var(--color15)] font-semibold text-2xl">Your Notes</h2>
  <button class="inline-flex transition-colors ring-offset-background px-3 bg-white border border-input rounded-md whitespace-nowrap gap-2 justify-center items-center h-9 cursor-pointer border-(--color13) hover:bg-(--color8)">
    <ArrowUpDown size={16} color="var(--color14)" />
    <p class="font-semibold text-sm text-(--color14)">Sort</p>
  </button>
</div>
  <div class="bg-white rounded-lg shadow overflow-hidden">
  <div class="relative p-4 border-b border-b-(--color13)">
    <Search class="absolute left-7 top-1/2 transform -translate-y-1/2" size={16} color="var(--color16)" />
    <input class="w-full h-10 border border-(--color13) py-2 pl-10 pr-3 text-(--color14) font-medium rounded-md focus:outline-offset-2 focus:outline-black-500 focus:outline-2" placeholder="Search notes..."
           bind:value={titleFilter} on:input={updateTitleFilter}>
  </div>
  {#each visibleNotes as note}
    <NotesHome id={note.id} title={note.title} subtitle={note.subtitle} updatedAt={new Date(note.updatedAt).toLocaleString()} />
  {/each}
</div>