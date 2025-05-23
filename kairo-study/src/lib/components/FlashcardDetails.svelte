<script>
	import { flashcardDetailsOverlay } from "$lib/stores/overlayStore.svelte";
  import { CalendarCheck2, CalendarClock, X } from "@lucide/svelte";
	import { onMount } from "svelte";

  export let flashcardId;

  let token;

  onMount(() => {
    token = localStorage.getItem("token");
  });
</script>

<div class="fixed inset-0 bg-black/80 flex justify-center items-center z-200">
  <div class="relative bg-white p-6 rounded-lg shadow-lg mx-4 w-full max-w-2xl h-[90vh]">
    <button onclick={() => flashcardDetailsOverlay.set(null)} class="absolute top-4 right-4 text-gray-500 hover:text-black transition-colors cursor-pointer" >
      <X size={16} />
    </button>
    <div class="overflow-hidden overflow-y-auto h-full">
      <h2 class="font-bold tracking-tight text-xl">Flashcard Details</h2>
      <h3 class="text-lg font-semibold text-gray-700">Front</h3>
      <div class="bg-gray-50 border border-(--color13) rounded-md p-4 mt-2">O que é uma derivada?</div>
      <h3 class="text-lg font-semibold text-gray-700">Back</h3>
      <div class="bg-gray-50 border border-(--color13) rounded-md p-4 mt-2">A derivada de uma função representa a taxa de variação instantânea da função em relação à variável independente.</div>
      <div class="grid grid-cols-2 gap-4 mt-6">
        <div class="flex items-center bg-gray-50 border border-(--color13) rounded-md p-4 gap-3">
          <span class="text-gray-500"><CalendarCheck2 class="tex-gray-500" size={20} /></span>
          <div class="flex flex-col">
            <p class="text-sm text-gray-500">Última revisão</p>
            <p class="font-medium">12/04/2025</p>
          </div>
        </div>
        <div class="flex items-center bg-gray-50 border border-(--color13) rounded-md p-4 gap-3">
          <span class="text-gray-500"><CalendarClock size={20} /></span>
          <div class="flex flex-col">
            <p class="text-sm text-gray-500">Próxima revisão</p>
            <p class="font-medium">12/04/2025</p>
          </div>
        </div>
      </div>
      <div class="flex items-center justify-between border-t border-t-(--color13) pt-4">
        <div class="flex items-center gap-2">
          <p class="text-sm text-gray-500">Criado em:</p>
          <p class="text-sm font-medium"></p>
        </div>
        <div class="flex items-center rounded-full px-2.5 py-0.5 text-sm font semibold gap-1 bg-amber-100 text-amber-800">
          <X size={14} />
          Não dominado
        </div>
      </div>
    </div>
  </div>
</div>