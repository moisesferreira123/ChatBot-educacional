<script lang="ts">
  import { LucideRotateCw, X } from '@lucide/svelte';

  // Props to control visibility and handle closing
  let {show = $bindable(), onClose} = $props();
</script>

{#if show}
  <aside class="right-0 top-0 bottom-0 flex-shrink-0 w-80 border-neutral-300 rounded-md bg-white shadow-lg z-50 transform transition-transform duration-300 ease-in-out {show ? 'translate-x-0' : 'translate-x-full'} pt-6">
    <header class="flex justify-between items-center p-4 border-b border-neutral-300">
      <h2 class="text-lg font-semibold text-gray-900">Flashcards</h2>
      <button class="text-gray-500 hover:text-black" onclick={onClose}>
        <X size={20} />
      </button>
    </header>
    <div class="p-4">
      <div class="flex flex-col items-center justify-center h-64 border border-dashed border-neutral-300 rounded-md text-gray-500">
        <div class="rounded-full bg-gray-100 p-3 mb-4">
            <LucideRotateCw size={20}/>
        </div>
        <strong>No Flashcards yet</strong>
        <p class="text-sm text-center">Create Flashcards to help with your studies</p>
        <button class="mt-4 bg-[var(--color1)] text-white px-4 py-2 rounded hover:bg-[var(--color1)]/90">
          + Create Flashcard
        </button>
      </div>
    </div>
  </aside>
{/if}
