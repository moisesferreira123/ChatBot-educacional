<script lang='ts'>
  import HeaderLoggedIn from "$lib/components/HeaderLoggedIn.svelte";
  import WYSIWYGEditor from "$lib/components/WYSIWYGEditor.svelte";
  import { ChevronLeft, Pencil, Plus, Clock, Save, Settings, FileText, MessageSquare, BookCheck } from "@lucide/svelte";

  //TODO: Pegar o titulo da nota do banco de dados
  let noteTitle = "Untitled Note";
  let noteContent = "";
  let lastUpdated = "10:35 AM";

  /* TODO: Implementar o onclick desse botões */
  const footerButtons = [{
    icon: FileText,
    text: "Sources",
    action: () => {
      console.log("Grabbing sources...");
    }
  }, {
    icon: BookCheck,
    text: "Flashcards",
    action: () => {
      console.log("Creating flashcards...");
    }
  }, {
    icon: MessageSquare,
    text: "Chat with Kairo",
    action: () => {
      console.log("Adding new note...");
    }
  }];

</script>

<div class="flex flex-col h-screen">
  <HeaderLoggedIn />
  <div class="flex flex-1 flex-col !pt-20">
    <header class="bg-white border-b border-neutral-300 p-3 flex items-center justify-between">
      <div class="flex items-center gap-2">
        <button class="inline-flex items-center justify-center gap-2 whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg]:size-4 [&_svg]:shrink-0 hover:bg-neutral-300/50 hover:text-accent-foreground h-10 w-10">
          <ChevronLeft size=5 />
        </button>
  
        <h1 class="text-xl font-semibold truncate max-w-[300px] cursor-pointer">{ noteTitle }</h1>
        <button class="inline-flex items-center justify-center gap-2 whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg]:size-4 [&_svg]:shrink-0 hover:bg-neutral-300/50 hover:text-accent-foreground h-8 w-8">
          <Pencil size=4 />
        </button>
        <div class="flex items-center gap-1 ml-2">
          <button class="inline-flex items-center justify-center gap-2 whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg]:size-4 [&_svg]:shrink-0 hover:bg-neutral-300/50 hover:text-accent-foreground h-6 w-6 rounded-full">
            <Plus size=3/>
          </button>
        </div>
      </div>
      <div class="flex items-center gap-2">
        <div class="flex items-center text-xs text-gray-500">
          <Clock size=12 class="!mr-1"/>
          <p>Last updated: { lastUpdated }</p>
        </div>
        <button class="border-neutral-300 inline-flex items-center justify-center gap-2 whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg]:size-4 [&_svg]:shrink-0 border border-input bg-background hover:bg-neutral-300/50 hover:text-accent-foreground h-9 rounded-md px-3 ml-4">
          <Save size=4 />
          <span>Save</span>
        </button>
        <button class="inline-flex items-center justify-center gap-2 whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg]:size-4 [&_svg]:shrink-0 hover:bg-neutral-300/50 hover:text-accent-foreground h-10 w-10">
          <Settings size=5 />
        </button>
  
      </div>
    </header>
    <!--TODO: Realmente utilizar essa variavel no editor-->
    <WYSIWYGEditor content={noteContent}/>
    <footer class="fixed bottom-0 w-screen bg-white border-t border-neutral-300 p-2 flex items-center justify-around">
      {#snippet footerButton(icon, text, action)}
        <button onclick={action} class="border-neutral-300 inline-flex items-center justify-center gap-2 whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg]:size-4 [&_svg]:shrink-0 border border-input bg-background hover:bg-neutral-300/50 hover:text-accent-foreground h-10 !px-4 !py-2">
          <svelte:component this={icon} size=5 />
          <span class="text-sm font-medium">{ text }</span>
        </button>
      {/snippet}
      {#each footerButtons as {icon, text, action}}
        {@render footerButton(icon, text, action)}
      {/each}
    </footer>
  </div>
</div>

<style lang="postcss">
</style>