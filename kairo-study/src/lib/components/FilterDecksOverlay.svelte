<script>
  import { filterDecksOverlay } from '$lib/stores/overlayStore.svelte';
	import { clickOutsideFilter } from '$lib/actions/clickOutsideFIlter';
	import { onMount } from 'svelte';

  let { changeFilter } = $props();

  let token;
  
  let topics = $state([]);
  let filterTypes = $state(["No filter"]);

  function changeFilterOverlay(newFilter) {
    filterDecksOverlay.set(false);
    changeFilter(newFilter);
  }

  async function fetchGetAllTopics() {
    const response = await fetch(`http://localhost:8080/api/topics`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`
    }
    });
    if(!response.ok){
      const errorMessage = await response.json();
      throw new Error(errorMessage.message);
    }
    return response.json();
  }

  async function getAllTopics() {
    try {
      topics = await fetchGetAllTopics();
      filterTypes = [...filterTypes, ...topics];
    } catch(e) {
      alert(`Error: ${e.message}`);
    }
  }

  onMount(() => {
    token = localStorage.getItem("token");
    getAllTopics();
  });


</script>

{#if topics}
<div use:clickOutsideFilter={() => filterDecksOverlay.set(false)} class="absolute flex flex-col items-start right-0 top-10 z-50 bg-white p-1 mt-1 min-w-45 max-h-[150px] overflow-y-auto rounded-md shadow border border-(--color13)">
  {#each filterTypes as filter}
    <button onclick={() => changeFilterOverlay(filter)} class="text-start px-2 py-1.5 text-sm text-(--color14) w-full hover:bg-gray-100 hover:cursor-pointer">{filter}</button>
  {/each}
</div>
{/if}