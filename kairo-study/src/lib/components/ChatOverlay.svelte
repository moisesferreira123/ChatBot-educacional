<script lang="ts">
  import { X } from '@lucide/svelte';
  import SendMessage from './SendMessage.svelte';
  import AiChatBubble from './AIChatBubble.svelte';
  import UserChatBubble from './UserChatBubble.svelte';
  import { fetchAIStream } // Assuming this is adapted or a similar function is made
    from '$lib/extensions/fetchAIStream.svelte'; // Adjust path if necessary
  import { onMount, onDestroy } from 'svelte'; // Added onDestroy

  let { show = $bindable(), onClose, noteContext = "" } = $props<{
    show: boolean,
    onClose: () => void,
    noteContext?: string // Optional: Pass content of the current note for context
  }>();

  interface Message {
    id: string;
    content: string;
    sender: 'user' | 'ai' | 'system';
    timestamp: Date;
  }

  let messages = $state<Message[]>([]);
  let isLoadingAiResponse = $state(false);
  let chatError = $state<string | null>(null);
  let authToken: string | null = null;
  let chatContainerRef: HTMLElement | null = $state(null);

  onMount(() => {
    authToken = localStorage.getItem("token");
    if (show) {
      // Optional: Add an initial system message or greeting from AI
      // messages.push({
      //   id: crypto.randomUUID(),
      //   sender: 'ai',
      //   content: 'Hello! How can Kairo assist you with your note today?',
      //   timestamp: new Date()
      // });
    }
  });

  // Auto-scroll to bottom
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

    try {
      if (!authToken) {
        throw new Error("Authentication token not found.");
      }

      // Simple system prompt for chat. You can make this more sophisticated.
      // If noteContext is provided, you can prepend it to the userPrompt or include in systemPrompt.
      let systemPromptForChat = "You are Kairo, a helpful AI assistant. Be concise and helpful.";
      let fullUserPrompt = userMessageContent;

      if (noteContext && noteContext.trim().length > 0) {
        // Example of incorporating note context. Adjust as needed.
        // This might make the prompt very long. Consider summarizing noteContext or using a dedicated field if backend supports it.
        // systemPromptForChat = `You are Kairo, an AI assistant helping a student with their note. Here is the note content for context: """${noteContext}""" Respond to the user's query.`;

        // Or, append to user prompt if the AI handles context better this way:
        fullUserPrompt = `Regarding my note (context: "${noteContext.substring(0, 500)}${noteContext.length > 500 ? '...' : ''}"), ${userMessageContent}`;

      }


      // Using fetchAIStream, assuming it's adapted to take a token and call the /api/ai/complete endpoint
      // and its onChunk callback receives the full response string.
      // The current fetchAIStream expects onChunk to handle streaming parts.
      // For a simple, non-streaming response:
      const response = await fetch('http://localhost:8080/api/ai/complete', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${authToken}`
        },
        body: JSON.stringify({
          systemPrompt: systemPromptForChat,
          userPrompt: fullUserPrompt // Send the user's message
        })
      });

      if (!response.ok) {
        const errorText = await response.text();
        throw new Error(`AI service error: ${response.status} ${errorText}`);
      }

      const aiResponseContent = await response.text();

      const aiMessage: Message = {
        id: crypto.randomUUID(),
        sender: 'ai',
        content: aiResponseContent,
        timestamp: new Date()
      };
      messages = [...messages, aiMessage];

    } catch (error: any) {
      console.error("Error getting AI response:", error);
      chatError = error.message || "Failed to get a response from Kairo.";
      const errorMessage: Message = {
        id: crypto.randomUUID(),
        sender: 'system', // Or 'ai' but styled as an error
        content: `Error: ${chatError}`,
        timestamp: new Date()
      };
      messages = [...messages, errorMessage];
    } finally {
      isLoadingAiResponse = false;
    }
  }

  function handleClose() {
    // Reset state if needed when overlay closes
    // messages = [];
    // chatError = null;
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
            <span class="px-3 py-1 text-xs bg-red-100 text-red-700 rounded-full">{msg.content}</span>
          </div>
        {/if}
      {/each}

      {#if isLoadingAiResponse}
        <div class="flex justify-start">
          <AiChatBubble message="Kairo is thinking..." />
        </div>
      {/if}
      {#if chatError && !isLoadingAiResponse}
      <!-- TODO: Tratar erros aqui -->
      {/if}
    </div>

    <div class="p-2 border-t border-neutral-300">
      <SendMessage onsubmit={handleUserMessageSubmit} disabled={isLoadingAiResponse} />
    </div>
  </aside>
{/if}

<style>
  /* Add any additional specific styles if needed */
  aside {
    padding-top: 0; /* Header handles top padding */
  }
</style>