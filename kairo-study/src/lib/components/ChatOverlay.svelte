<script lang="ts">
  import { X } from '@lucide/svelte';
  import SendMessage from './SendMessage.svelte';
  import AiChatBubble from './AIChatBubble.svelte';
  import UserChatBubble from './UserChatBubble.svelte';
  import { onMount, onDestroy } from 'svelte';
  import { fetchAIStream } from '$lib/actions/AiResponses.svelte';
  import { marked } from 'marked';
  import { page } from '$app/state'; // Import page store for noteId

  let { show = $bindable(), onClose, sources = $bindable()} = $props();

  interface Message {
    id: string;
    content: string;
    sender: 'user' | 'ai' | 'system';
    timestamp: Date;
  }

  let sourceIds = $derived(sources.map(source => source.id));
  let messages = $state<Message[]>([]);
  let isLoadingAiResponse = $state(false);
  let chatError = $state<string | null>(null);
  let authToken: string | null = null;
  let chatContainerRef: HTMLElement | null = $state(null);

  let currentNoteId = $derived(page.params.id ? parseInt(page.params.id) : undefined);

  onMount(() => {
    authToken = localStorage.getItem("token");
    if (show && messages.length === 0) {
      messages.push({
        id: crypto.randomUUID(),
        sender:'ai',
        content: 'Hello! How can Kairo assist you with your note today?',
        timestamp: new Date()
      });
    }
  });

  $effect(() => {
    if (chatContainerRef && messages.length) {
      chatContainerRef.scrollTop = chatContainerRef.scrollHeight;
    }
  });

  async function handleUserMessageSubmit(userMessageContent: string) {
    if (!userMessageContent.trim() || isLoadingAiResponse) return;

    const userMessage: Message = {
        id: crypto.randomUUID(),
        sender: 'user',
        content: userMessageContent,
        timestamp: new Date()
    };
    messages = [...messages, userMessage];
    chatError = null;
    isLoadingAiResponse = true;

    const aiMessageId = crypto.randomUUID();
    const initialAiMessage: Message = {
        id: aiMessageId,
        sender: 'ai',
        content: "", 
        timestamp: new Date()
    };
    messages = [...messages, initialAiMessage];

    let currentAiContent = "";

    try {
        if (!authToken) {
            throw new Error("Authentication token not found.");
        }

        await fetchAIStream(
            userMessageContent,
            (chunk: string) => { // onChunk
              currentAiContent += chunk;
                messages = messages.map(msg =>
                    msg.id === aiMessageId ? { ...msg, content: marked.parse(currentAiContent) } as Message : msg
                );
            },
            () => { // onComplete
                isLoadingAiResponse = false;
                messages = messages.map(msg =>
                    msg.id === aiMessageId ? { ...msg, content: marked.parse(currentAiContent) } as Message : msg
                );
            },
            (error: any) => { // onError
                isLoadingAiResponse = false;
                chatError = error.message || "Failed to get a response from Kairo.";
                messages = messages.map(msg =>
                    msg.id === aiMessageId ? { ...msg, content: `Error: ${chatError}`, sender: 'system' } : msg
                );
            },
            authToken,
            currentNoteId,
            sourceIds
        );

    } catch (error: any) {
        isLoadingAiResponse = false;
        console.error("Error getting AI response for chat:", error);
        chatError = error.message || "Failed to get a response from Kairo.";
         messages = messages.map(msg =>
            msg.id === aiMessageId ? { ...msg, content: `Error: ${chatError}`, sender: 'system' } : msg
        );
    }
  }

  function handleClose() {
    onClose();
  }

</script>

{#if show}
  <aside class="right-0 h-full flex flex-col flex-shrink-0 w-80 sm:w-96 border-l border-neutral-300 bg-white shadow-lg z-50 transform transition-transform duration-300 ease-in-out {show ? 'translate-x-0' : 'translate-x-full'}">
    <header class="flex justify-between items-center p-4 border-b border-neutral-300">
      <h2 class="text-lg font-semibold text-gray-900">Kairo Assistant</h2>
      <button class="text-gray-500 hover:text-black p-1" onclick={handleClose}>
        <X size={20} />
      </button>
    </header>

    <div bind:this={chatContainerRef} class="flex-grow p-4 space-y-3 overflow-y-auto">
      {#if messages.length === 0 && !isLoadingAiResponse && !chatError}
        <div class="flex flex-col items-center justify-center h-full text-center text-gray-500">
          <img src="/images/icon-kairo-removebg-preview.webp" alt="Kairo Icon" class="w-16 h-16 mb-2 opacity-70" />
          <p class="text-sm">Chat with Kairo about your note or ask anything!</p>
        </div>
      {/if}

      {#each messages as msg (msg.id)}
        {#if msg.sender === 'user'}
          <div class="flex justify-end">
            <UserChatBubble message={msg.content} />
          </div>
        {:else if msg.sender === 'ai'}
          <div class="flex justify-start">
            <AiChatBubble message={msg.content} />
          </div>
        {:else if msg.sender === 'system'}
           <div class="text-center my-2">
            <span class="px-3 py-1 text-xs bg-red-100 text-red-700 rounded-full">{@html msg.content}</span>
          </div>
        {/if}
      {/each}

      {#if isLoadingAiResponse && messages[messages.length -1]?.sender !== 'ai' } <div class="flex justify-start">
          <AiChatBubble message="Kairo is thinking..." />
        </div>
      {/if}
    </div>

    <div class="p-2 border-t border-neutral-300">
      <SendMessage onsubmit={handleUserMessageSubmit} disabled={isLoadingAiResponse} />
    </div>
  </aside>
{/if}

<style>
  aside {
    padding-top: 0; 
  }
</style>