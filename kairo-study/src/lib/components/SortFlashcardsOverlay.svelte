<script>
  import { sortFlashcardsOverlay } from '$lib/stores/overlayStore.svelte';
  import { clickOutside } from '$lib/actions/clickOutside';

  let { changeSort } = $props();

  function changeSortOverlay(newSort) {
    sortFlashcardsOverlay.set(false);
    changeSort(newSort);
  }

  const sortTypes = [
    {
      sortTitle: "Last review date (recent)",
      sortType: "lastReviewedAtDesc"
    },
    {
      sortTitle: "Last review date (old)",
      sortType: "lastReviewedAtAsc"
    },
    {
      sortTitle: "Creation date (recent)",
      sortType: "createdAtDesc"
    },
    {
      sortTitle: "Creation date (old)",
      sortType: "createdAtAsc"
    },
    {
      sortTitle: "Next review date (closest)",
      sortType: "nextReviewAsc"
    },
    {
      sortTitle: "Next review date (furthest)",
      sortType: "nextReviewDesc"
    }
  ];

</script>
{#snippet sortTypeButton({sortTitle, sortType})}
  <button onclick={() => changeSortOverlay(sortType)} class="text-start px-2 py-1.5 text-sm text-(--color14) w-full hover:bg-gray-100 hover:cursor-pointer">{sortTitle}</button>
{/snippet}

<div use:clickOutside={() => sortFlashcardsOverlay.set(false)} class="absolute flex flex-col items-start right-0 top-10 z-50 bg-white p-1 mt-1 min-w-55 max-h-[150px] overflow-y-auto rounded-md shadow border border-(--color13)">
  {#each sortTypes as sort}
    {@render sortTypeButton(sort)}
  {/each}
</div>