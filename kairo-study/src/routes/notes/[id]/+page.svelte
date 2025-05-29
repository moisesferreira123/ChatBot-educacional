<script lang='ts'>
  import HeaderLoggedIn from "$lib/components/HeaderLoggedIn.svelte";
  import WYSIWYGEditor from "$lib/components/WYSIWYGEditor.svelte";
  import { ChevronLeft, Pencil, Plus, Clock, Save, Settings, FileText, MessageSquare, BookCheck } from "@lucide/svelte";
	import { onMount } from "svelte";
	import { fetchGetNoteById } from '$lib/api/notes';
  import { fetchUpdateNote } from "$lib/api/notes";
  import { page } from "$app/state";
	import { goto } from "$app/navigation";
	import SourcesOverlay from "$lib/components/SourcesOverlay.svelte";
	import FlashcardsOverlay from "$lib/components/FlashcardsOverlay.svelte";
	import ChatOverlay from "$lib/components/ChatOverlay.svelte";
  import Source from "$lib/components/SourcesOverlay.svelte"

  let id = $derived(page.params.id);
  
  interface Note {
    id: number;
    title: string;
    subtitle: string;
    content: string;
    createdAt: string;
    updatedAt: string;
  }

  let sources: Source[] = $state([]);
  let note: Note | null = $state(null);
  let token: string | null;
  let saveTimeout: ReturnType<typeof setTimeout> | null = null;
  const inactiveButtonClass = "border-neutral-300 hover:bg-neutral-300/50";
  const activeButtonClass = "bg-[var(--color1)] text-white hover:bg-[var(--color1)]/90";
    
    // TODO: Não fazer o fetchUpdateNote retornar o conteúdo da nota (Isso vai ter que mudar no back-end) 
    function autoSave(content: string) {
      if(saveTimeout) clearTimeout(saveTimeout);
      saveTimeout = setTimeout(async () => {
        if(!token || !note) return;
        note.content = content;
        try {
          note = await fetchUpdateNote(note.id, note.title, note.subtitle, content, token);
          console.log('Note saved automatically');
        } catch (e) {
          console.error("Error saving note:", e);
        }
      }, 1000);
    }
    
    // Para usar title, subtitle, content, updatedAt, etc... Basta colocar note.<aquilo que vc quer>
    // Exemplo: <p>Last updated: { note.updatedAt }</p>
    onMount(async () => {
      token = localStorage.getItem("token");
      try {
      note = await fetchGetNoteById(id, token);
    } catch(e: unknown) {
      alert("Erro");
      goto('/home');
    }
  });
  
  /* TODO: Implementar o onclick desse botões */
  let [showSourcesOverlay, showFlashcardsOverlay, showChatOverlay] = $state([false,false,false]);
  const footerButtons = [{
    icon: FileText,
    text: "Sources",
    action: () => {
      showSourcesOverlay = !showSourcesOverlay;
      console.log("Grabbing sources...");
    },
    active: () => { return showSourcesOverlay; }
  }, {
    icon: BookCheck,
    text: "Flashcards",
    action: () => {
      showFlashcardsOverlay = !showFlashcardsOverlay;
      console.log("Creating flashcards...");
    },
    active: () => { return showFlashcardsOverlay; }
  }, {
    icon: MessageSquare,
    text: "Chat with Kairo",
    action: () => {
      showChatOverlay = !showChatOverlay;
      console.log("Adding new note...");
    },
    active: () => { return showChatOverlay; }
  }];

</script>

<div class="flex flex-col h-screen">
  {#if note}
  <HeaderLoggedIn />
  <div class="flex h-full flex-1 flex-col pt-20">
    <header class="bg-white border-b border-neutral-300 p-3 flex items-center justify-between">
      <div class="flex items-center gap-2">
        <button onclick={() => {goto('/home')}} class="inline-flex items-center justify-center gap-2 whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg]:size-4 [&_svg]:shrink-0 hover:bg-neutral-300/50 hover:text-accent-foreground h-10 w-10">
          <ChevronLeft size=5 />
        </button>
  
        <h1 class="text-xl font-semibold truncate max-w-[300px] cursor-pointer">{ note.title }</h1>
        <button class="inline-flex items-center justify-center gap-2 whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg]:size-4 [&_svg]:shrink-0 hover:bg-neutral-300/50 hover:text-accent-foreground h-8 w-8">
          <Pencil size=4 />
        </button>
        <div class="flex items-center gap-1 ml-2">
          <button class="inline-flex items-center justify-center gap-2 whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg]:size-4 [&_svg]:shrink-0 hover:bg-neutral-300/50 hover:text-accent-foreground h-6 w-6 rounded-full">
            <Plus size=3 />
          </button>
        </div>
      </div>
      <div class="flex items-center gap-2">
        <div class="flex items-center text-xs text-gray-500">
          <Clock size=12 class="!mr-1"/>
          <p>Last updated: { new Date(note.updatedAt).toLocaleString() }</p>
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
    <div class="flex flex-1 overflow-y-hidden">
      {#if showSourcesOverlay}
      <SourcesOverlay bind:show={showSourcesOverlay} onClose={() => {showSourcesOverlay = false}} bind:sources={sources}/>
      {/if}
        <div class="w-full">
        <WYSIWYGEditor content={note.content} onContentChange={autoSave} token={token} bind:sources={sources}/>
      </div>
      <FlashcardsOverlay bind:show={showFlashcardsOverlay} onClose={() => {showFlashcardsOverlay = false}} />
      <ChatOverlay bind:show={showChatOverlay} onClose={() => {showChatOverlay = false}} noteContext={note?.content || ''} bind:sources={sources}/>
      </div>
        <footer class="bottom-0 z-5 0 w-screen bg-white border-t border-neutral-300 p-2 flex items-center justify-around">
          {#snippet footerButton(Icon, text, action, active)}
          <button onclick={action} class="{active() ? activeButtonClass : inactiveButtonClass } inline-flex items-center justify-center gap-2 whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg]:size-4 [&_svg]:shrink-0 border border-input bg-background h-10 !px-4 !py-2">
            <Icon size=5 />
            <span class="text-sm font-medium">{ text }</span>
          </button>
          {/snippet}
          {#each footerButtons as {icon, text, action, active}}
          {@render footerButton(icon, text, action, active)}
          {/each}
        </footer>
  </div>
  {:else}
  <div class="flex flex-1 items-center justify-center">
    <p>Loading note...</p>
  </div>
  {/if}
</div>
