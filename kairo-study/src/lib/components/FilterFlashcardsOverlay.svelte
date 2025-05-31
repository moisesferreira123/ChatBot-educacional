<script>
  import { filterFlashcardsOverlay } from '$lib/stores/overlayStore.svelte';
	import { clickOutsideFilter } from '$lib/actions/clickOutsideFIlter';

  let { changeFilter } = $props();

  function changeFilterOverlay(newFilter) {
    filterFlashcardsOverlay.set(false);
    changeFilter(newFilter);
  }

  const filterTypes = [
    {
      filterTitle: "None",
      filterType: "none"
    },
    {
      filterTitle: "Dominated flashcards",
      filterType: "dominatedFlashcards"
    },
    {
      filterTitle: "Undominated flashcards",
      filterType: "undominatedFlashcards"
    },
    {
      filterTitle: "New flashcards",
      filterType: "newFlashcards"
    },
    {
      filterTitle: "Not new flashcards",
      filterType: "notNewFlashcards"
    },
    {
      filterTitle: "Due flashcards",
      filterType: "dueFlashcards"
    }
  ];

</script>
{#snippet filterTypeButton({filterTitle, filterType})}
  <button onclick={() => changeFilterOverlay(filterType)} class="text-start px-2 py-1.5 text-sm text-(--color14) w-full hover:bg-gray-100 hover:cursor-pointer">{filterTitle}</button>
{/snippet}

<div use:clickOutsideFilter={() => filterFlashcardsOverlay.set(false)} class="absolute flex flex-col items-start right-0 top-10 z-50 bg-white p-1 mt-1 min-w-55 max-h-[150px] overflow-y-auto rounded-md shadow border border-(--color13)">
  {#each filterTypes as filter}
    {@render filterTypeButton(filter)}
  {/each}
</div>