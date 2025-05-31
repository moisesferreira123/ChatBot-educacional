<script>
  import { sortDecksOverlay } from '$lib/stores/overlayStore.svelte';
  import { clickOutside } from '$lib/actions/clickOutside';

  let { changeSort } = $props();

  function changeSortOverlay(newSort) {
    sortDecksOverlay.set(false);
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
      sortTitle: "Mastery level (high)",
      sortType: "masteryLevelDesc"
    },
    {
      sortTitle: "Mastery level (low)",
      sortType: "masteryLevelAsc"
    },
    {
      sortTitle: "More due cards",
      sortType: "totalDueFlashcardsDesc"
    },
    {
      sortTitle: "Fewer due cards",
      sortType: "totalDueFlashcardsAsc"
    },
    {
      sortTitle: "Total number of cards",
      sortType: "totalFlashcardsDesc"
    }
  ];

</script>
{#snippet sortTypeButton({sortTitle, sortType})}
  <button onclick={() => changeSortOverlay(sortType)} class="text-start px-2 py-1.5 text-sm text-(--color14) w-full hover:bg-gray-100 hover:cursor-pointer">{sortTitle}</button>
{/snippet}

<div use:clickOutside={() => sortDecksOverlay.set(false)} class="absolute flex flex-col items-start right-0 top-9 z-50 bg-white p-1 mt-1 min-w-46 max-h-[180px] overflow-y-auto rounded-md shadow border border-(--color13)">
  {#each sortTypes as sort}
    {@render sortTypeButton(sort)}
  {/each}
</div>