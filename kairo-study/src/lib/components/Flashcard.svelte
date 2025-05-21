<script>
  import { SquarePen, Trash2 } from "@lucide/svelte";
  import { onMount } from "svelte";

  export let flashcardId;
  export let front;
  export let back;

  let token;

  onMount(() => {
    token = localStorage.getItem("token");
  });
</script>

<div class="flex justify-between rounded-lg shadow-xs border border-(--color13) hover:border-gray-400 transition-all p-4">
  <div>
    <h4 class="font-medium mb-2 text-(--color14)">{front}</h4>
    <p class="text-gray-500">{back}</p>
  </div>
  <div class="flex gap-2 ml-4">
    <button class="flex items-center justify-center text-(--color14) rounded-md font-medium hover:bg-(--color8) hover:cursor-pointer h-8 w-8">
      <SquarePen size={16} />
    </button>
    <button class="flex items-center justify-center text-red-500 rounded-md font-medium hover:bg-(--color8) hover:cursor-pointer h-8 w-8">
      <Trash2 size={16} />
    </button>
  </div>
</div>