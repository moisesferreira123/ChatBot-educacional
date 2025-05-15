<script>
  import { deckManagementOverlay } from "$lib/stores/overlayStore.svelte";
  import { X, Settings, Search, Trash2, Plus } from "@lucide/svelte";

  export let id;
  export let title;
  export let topic;

  let word;
</script>


<div class="fixed inset-0 z-50 bg-black/80 data-[state=open]:animate-in data-[state=closed]:animate-out data-[state=closed]:fade-out-0 data-[state=open]:fade-in-0">
  <div class="fixed left-[50%] top-[50%] z-50 w-full translate-x-[-50%] translate-y-[-50%] gap-4 border bg-background p-6 shadow-lg duration-200 data-[state=open]:animate-in data-[state=closed]:animate-out data-[state=closed]:fade-out-0 data-[state=open]:fade-in-0 data-[state=closed]:zoom-out-95 data-[state=open]:zoom-in-95 data-[state=closed]:slide-out-to-left-1/2 data-[state=closed]:slide-out-to-top-[48%] data-[state=open]:slide-in-from-left-1/2 data-[state=open]:slide-in-from-top-[48%] sm:rounded-lg max-w-3xl h-[80vh] flex flex-col">
    <div class="flex flex-col space-y-1.5 text-center sm:text-left flex-shrink-0">
      <div class="flex items-center justify-between">
        <h2 class="font-semibold tracking-tight text-xl">{title}</h2>
        <div class="flex gap-2">
          <button class="inline-flex items-center justify-center gap-2 whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg]:size-4 [&_svg]:shrink-0 border border-input bg-background hover:bg-accent hover:text-accent-foreground h-9 rounded-md px-3">
            <Settings size={16} />
            Edit Deck
          </button>
          <button class="inline-flex items-center justify-center gap-2 whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg]:size-4 [&_svg]:shrink-0 bg-destructive text-destructive-foreground hover:bg-destructive/90 h-9 rounded-md px-3">
            <Trash2 size={16}/>
            Delete Deck
          </button>
        </div>
      </div>
    </div>
    <div class="flex-grow overflow-hidden">
      <div class="flex justify-between items-center mb-4">
        <h3 class="text-lg font-medium">Flashcards neste deck (5)</h3>
        <button class="inline-flex items-center justify-center gap-2 whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg]:size-4 [&_svg]:shrink-0 border border-input bg-background hover:bg-accent hover:text-accent-foreground h-9 rounded-md px-3">
          <Plus size={16} />
          Add Flashcards
        </button>
      </div>
    </div>
  </div>
</div>

<!-- <div class="fixed inset-0 bg-black/80 flex justify-center items-center z-50">
  <div class="relative max-w-3xl bg-white p-6 rounded-lg shadow-lg mx-4">
    <button class="absolute top-4 right-4 text-gray-500 hover:text-black transition-colors cursor-pointer" onclick={() => deckManagementOverlay.set(null)} >
      <X size={16} />
    </button>
    <div class="flex items-center justify-between">
      <h2 class="text-xl font-semibold tracking-tight text-(--color14)">{title}</h2>
      <button class="inline-flex items-center justify-center gap-2 whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg]:size-4 [&_svg]:shrink-0 border border-input bg-background hover:bg-accent hover:text-accent-foreground h-9 rounded-md px-3">
        <Settings size={16} />
        Edit Deck
      </button>
      <button class="inline-flex items-center justify-center gap-2 whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg]:size-4 [&_svg]:shrink-0 bg-destructive text-destructive-foreground hover:bg-destructive/90 h-9 rounded-md px-3">
        <Trash2 size={16} />

      </button>
    </div>
    <div class="mb-4">
      <Search class="absolute left-3 top-1/2 transform -translate-y-1/2" size={16} color="var(--color16)" />
      <input class="w-full h-10 border border-(--color13) py-2 pl-10 pr-3 text-(--color14) font-medium rounded-md focus:outline-offset-2 focus:outline-black-500 focus:outline-2 bg-white" placeholder="Search decks..."
             bind:value={word}>
    </div>
  </div>
</div> -->