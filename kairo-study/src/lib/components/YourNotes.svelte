<script>
  import { Search, ArrowUpDown, ArrowUp, ArrowDown } from '@lucide/svelte';
	import { fetchListNotes } from '$lib/api/notes';
  import { onMount } from 'svelte';
  import { sortNotesOverlay } from '$lib/stores/overlayStore.svelte';
  import { sortTypeNotes } from '$lib/stores/sortType';
  import NotesHome from './NotesHome.svelte';
  import SortNotesOverlay from './SortNotesOverlay.svelte';

  let allNotes = [];
  let visibleNotes = [];
  let currentPage = 0;
  let pageSize = 5;
  let titleFilter = "";
  let hasMore = true;
  let displayCount = 5;

  let token;

  async function loadNotes() {
    if(!hasMore) return;
    try {
      const data = await fetchListNotes(currentPage, pageSize, titleFilter, $sortTypeNotes, token);
      allNotes = [...allNotes, ...data.content];
      updateVisibleNotes();
      hasMore = !data.last;
      currentPage++;
    } catch(e) {
      alert(`Error: ${e.message}`);
    }
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
    sortTypeNotes.set(newSort);
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
    allNotes = [];
    hasMore = true;
  }

  onMount(() => {
    token = localStorage.getItem("token");
    sortTypeNotes.set("updatedAtDesc");
    loadNotes();
  });

</script>

<div class="relative flex justify-between items-center mb-[16px]">
  <h2 class="text-[var(--color15)] font-semibold text-2xl">Your Notes</h2>
  <button data-user-button onclick={() => sortNotesOverlay.update(open => !open)} class="inline-flex transition-colors ring-offset-background px-3 bg-white border border-input rounded-md whitespace-nowrap gap-2 justify-center items-center h-9 cursor-pointer border-(--color13) hover:bg-(--color8)">
    <ArrowUpDown size={16} color="var(--color14)" />
    <p class="font-semibold text-sm text-(--color14)">Sort</p>
  </button>
  {#if $sortNotesOverlay}
    <SortNotesOverlay changeSort={(newSort) => changeSort(newSort)}/>
  {/if}
</div>
<div class="bg-white rounded-lg shadow">
  <div class="relative p-4 border-b border-b-(--color13)">
    <Search class="absolute left-7 top-1/2 transform -translate-y-1/2" size={16} color="var(--color16)" />
    <input class="w-full h-10 border border-(--color13) py-2 pl-10 pr-3 text-(--color14) font-medium rounded-md focus:outline-offset-2 focus:outline-black-500 focus:outline-2" placeholder="Search notes..."
           bind:value={titleFilter} oninput={updateTitleFilter}>
  </div>
  {#each visibleNotes as note}
    <NotesHome 
      id={note.id} 
      title={note.title} 
      subtitle={note.subtitle} 
      updatedAt={new Date(note.updatedAt).toLocaleString()} 
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
    disabled={displayCount <= 5}>
    <ArrowUp size={20} />
  </button>
  <button 
    onclick={showMore} 
    class="flex h-10 w-10 justify-center items-center rounded-full border border-(--color13) bg-white shadow cursor-pointer hover:bg-gray-50 disabled:bg-white disabled:opacity-50 disabled:cursor-default" 
    disabled={allNotes.length === 0 || (!hasMore && displayCount >= allNotes.length)}>
    <ArrowDown size={20} />
  </button>   
</div>