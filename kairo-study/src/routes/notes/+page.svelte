<script lang='ts'>
  import HeaderLoggedIn from "$lib/components/HeaderLoggedIn.svelte";
  import WYSIWYGEditor from "$lib/components/WYSIWYGEditor.svelte";
  import { ChevronLeft, Pencil, Plus } from "@lucide/svelte";

  //TODO: Pegar o titulo da nota do banco de dados
  let noteTitle = "Untitled Note";
  let noteContent = "";

</script>

<HeaderLoggedIn />
<div class="flex flex-1 flex-col !pt-20">
  <header class="bg-white border-b p-3 flex items-center justify-between">
    <div class="flex items-center gap-2">
      <button class="inline-flex items-center justify-center gap-2 whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg]:size-4 [&_svg]:shrink-0 hover:bg-accent hover:text-accent-foreground h-10 w-10">
        <ChevronLeft size=5 />
      </button>
  
      <h1 class="text-xl font-semibold truncate max-w-[300px] cursor-pointer">{ noteTitle }</h1>
      <button class="inline-flex items-center justify-center gap-2 whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg]:size-4 [&_svg]:shrink-0 hover:bg-accent hover:text-accent-foreground h-8 w-8">
        <Pencil size=4 />
      </button>
      <div class="flex items-center gap-1 ml-2">
        <button class="inline-flex items-center justify-center gap-2 whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg]:size-4 [&_svg]:shrink-0 hover:bg-accent hover:text-accent-foreground h-6 w-6 rounded-full">
          <Plus size=3/>
        </button>
      </div>
    </div>
    <div></div>
  </header>
  <WYSIWYGEditor />
</div>

<style lang="postcss">
</style>