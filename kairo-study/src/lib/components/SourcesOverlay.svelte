<script lang="ts">
  import { X, } from '@lucide/svelte';

  // Props to control visibility and handle closing
  let {show = $bindable(), onClose} = $props();
</script>

{#if show}
  <aside class="left-0 top-0 bottom-0 flex-shrink-0 w-80 border-neutral-300 rounded-md bg-white shadow-lg z-50 transform transition-transform duration-300 ease-in-out {show ? 'translate-x-0' : '-translate-x-full'} pt-6">
    <header class="flex justify-between items-center p-4 border-b border-neutral-300">
      <h2 class="text-lg font-semibold text-gray-900">Documents</h2>
      <button class="text-gray-500 hover:text-black" onclick={onClose}>
        <X size={20} />
      </button>
    </header>
    <div class="p-4">
      <div class="flex flex-col items-center justify-center h-64 border border-dashed border-neutral-300 rounded-md text-gray-500">
        <strong>No documents attached</strong>
        <p class="text-sm text-center">Upload documents to get context for your notes</p>
        </div>
    </div>
  </aside>
{/if}