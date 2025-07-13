<script>
  import { sortNotesOverlay } from '$lib/stores/overlayStore.svelte';
  import { clickOutside } from '$lib/actions/clickOutside';

  let { changeSort }  = $props();

  function changeSortOverlay(newSort) {
    sortNotesOverlay.set(false);
    changeSort(newSort);
  }

  const sortTypes = [
    {
      sortTitle: "Update date (recent)",
      sortType: "updatedAtDesc"
    },
    {
      sortTitle: "Update date (old)",
      sortType: "updatedAtAsc"
    },
    {
      sortTitle: "Creation date (recent)",
      sortType: "createdAtDesc"
    },
    {
      sortTitle: "Creation date (old)",
      sortType: "createdAtAsc"
    }
  ];

</script>
{#snippet sortTypeButton({sortTitle, sortType})}
  <button onclick={() => changeSortOverlay(sortType)} class="text-start px-2 py-1.5 text-sm text-(--color14) w-full hover:bg-gray-100 hover:cursor-pointer">{sortTitle}</button>
{/snippet}

<div use:clickOutside={() => sortNotesOverlay.set(false)} class="absolute flex flex-col items-start right-0 top-9 z-50 bg-white p-1 mt-1 min-w-32 rounded-md shadow border border-(--color13)">
  {#each sortTypes as sort}
    {@render sortTypeButton(sort)}
  {/each}
</div>