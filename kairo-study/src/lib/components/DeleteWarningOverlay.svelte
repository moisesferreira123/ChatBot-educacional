<script>
  import { X } from "@lucide/svelte";

  let { alertTitle, alertMessage, cancelButton, deleteButton } = $props();
</script>

<div class="fixed inset-0 bg-black/80 flex justify-center items-center z-51">
  <div class="relative bg-white p-6 rounded-lg shadow-lg mx-4 max-w-lg">
    <button class="absolute top-4 right-4 text-gray-500 hover:text-black transition-colors cursor-pointer" onclick={cancelButton} >
      <X size={16} />
    </button>
    <h2 class="text-lg font-bold text-(--color14)">{alertTitle}</h2>
    <p class="text-sm text-(--color17) mt-2 mb-4">{alertMessage}</p>
    <div class="flex justify-end gap-3">
      <button onclick={cancelButton} class="text-sm font-semibold bg-white text-(--color14) outline outline-offset-[-1px] outline-(--color13) px-3 py-2 rounded transition-colors duration-250 hover:bg-(--color8) hover:cursor-pointer">Cancel</button>
      <button onclick={deleteButton} class="text-sm font-semibold bg-red-500 text-white px-4 py-2 rounded transition-colors duration-250 hover:bg-red-500/85 hover:cursor-pointer">Delete</button>
    </div>
  </div>
</div>