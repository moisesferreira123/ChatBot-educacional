<script>
		import { fetchGetFlashcardById } from "$lib/api/flashcards";
	import { formatDate } from "$lib/stores/formatDate";
	import { flashcardDetailsOverlay } from "$lib/stores/overlayStore.svelte";
  import { CalendarCheck2, CalendarClock, Check, X } from "@lucide/svelte";
	import { onMount } from "svelte";

  let { flashcardId } = $props();

  let flashcard = $state();
  let token;

  async function getFlashcardById() {
    try {
      flashcard = await fetchGetFlashcardById(flashcardId, token);
    } catch(e) {
      alert(`Error: ${e.message}`);
      flashcardDetailsOverlay.set(null);
    }
  }

  onMount(() => {
    token = localStorage.getItem("token");
    getFlashcardById();
  });
</script>
{#if flashcard}
  <div class="fixed inset-0 bg-black/80 flex justify-center items-center z-200">
    <div class="relative bg-white p-6 pr-0 rounded-lg shadow-lg mx-4 w-full max-w-2xl">
      <button onclick={() => flashcardDetailsOverlay.set(null)} class="absolute top-4 right-4 text-gray-500 hover:text-black transition-colors cursor-pointer" >
        <X size={16} />
      </button>
      <div class="pr-6 overflow-hidden overflow-y-auto max-h-[85vh]">
        <h2 class="font-bold tracking-tight text-xl text-(--color14)">Flashcard Details</h2>
        <h3 class="mt-5 text-lg font-semibold text-gray-700">Front</h3>
        <div class="bg-gray-50 border border-(--color13) font-medium rounded-md text-(--color14) p-4 mt-2">{flashcard.front}</div>
        <h3 class="mt-3 text-lg font-semibold text-gray-700">Back</h3>
        <div class="bg-gray-50 border border-(--color13) font-medium rounded-md text-(--color14) p-4 mt-2">{flashcard.back}</div>
        <div class="grid grid-cols-2 mq-grid gap-4 mt-7">
          <div class="flex items-center bg-gray-50 border border-(--color13) rounded-md p-4 gap-3">
            <span class="text-gray-500"><CalendarCheck2 class="tex-gray-500" size={20} /></span>
            <div class="flex flex-col">
              <p class="text-sm text-gray-500">Last review</p>
              {#if formatDate(new Date(flashcard.lastReviewedAt)) === formatDate(new Date(null))}
                <p class="font-semibold text-(--color14)">Never reviewed</p>
              {:else}
                <p class="font-semibold text-(--color14)">{formatDate(new Date(flashcard.lastReviewedAt))}</p>
              {/if}
            </div>
          </div>
          <div class="flex items-center bg-gray-50 border border-(--color13) rounded-md p-4 gap-3">
            <span class="text-gray-500"><CalendarClock size={20} /></span>
            <div class="flex flex-col">
              <p class="text-sm text-gray-500">Next review</p>
              <p class="font-semibold text-(--color14)">{formatDate( new Date(flashcard.nextReview))}</p>
            </div>
          </div>
        </div>
        <div class="flex items-center justify-between mt-6 border-t border-t-(--color13) pt-4">

         <div class="flex items-center gap-2">
            <p class="text-sm text-gray-500">Created on:</p>
            <p class="text-sm font-semibold text-(--color14)">{formatDate( new Date(flashcard.createdAt))}</p>
          </div>
          {#if flashcard.repetition < 4}
            <div class="flex items-center rounded-full px-2.5 py-0.5 text-xs font-semibold gap-1 bg-amber-100 text-amber-800">
              <X size={14} />
              Undominated
            </div>
          {:else}
            <div class="flex items-center rounded-full px-2.5 py-0.5 text-xs font-semibold gap-1 bg-green-100 text-green-800">
              <Check size={14} />
              Dominated
            </div>
          {/if}
        </div>
        <div class="flex flex-row-reverse mt-4">
          <button onclick={() => flashcardDetailsOverlay.set(null)} class="bg-(--color1) text-white px-4 py-2 rounded transition-colors duration-250 hover:bg-[var(--color6)] hover:cursor-pointer">Close</button>
        </div>
      </div>
    </div>
  </div>
{:else}
  <p class="text-gray-500 font-semibold">Loading...</p>
{/if}

<style>
  @media (max-width: 460px) {
    .mq-grid {
      grid-template-columns: repeat(1, minmax(0, 1fr));
    }
  }
</style>