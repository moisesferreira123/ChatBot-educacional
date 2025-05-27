<script lang="ts">
  import { X } from '@lucide/svelte';
	import SendMessage from './SendMessage.svelte';
	import AiChatBubble from './AIChatBubble.svelte';
  import UserChatBubble from './UserChatBubble.svelte';

  // Props to control visibility and handle closing
  let {show = $bindable(), onClose} = $props();

  interface Message {
    content : string;
    sender : string;
  };

  let messages = $state<Message[]>([]);
</script>

{#if show}
  <aside class="right-0 top-0 bottom-0 flex-shrink-0 w-80 border-neutral-300 rounded-md bg-white shadow-lg z-50 transform transition-transform duration-300 ease-in-out {show ? 'translate-x-0' : 'translate-x-full'} pt-6">
    <header class="flex justify-between items-center p-4 border-b border-neutral-300">
      <h2 class="text-lg font-semibold text-gray-900">Kairo Assistant</h2>
      <button class="text-gray-500 hover:text-black" onclick={onClose}>
        <X size={20} />
      </button>
    </header>
    <div class="p-4">
      {#if messages.length == 0}
      <div class="flex flex-col items-center justify-center h-64 border border-dashed border-neutral-300 rounded-md text-gray-500">
        <p>Chat with Kairo about your note</p>
      </div>
      {:else}
      <div class="flex flex-col overflow-y-auto items-start justify-items-start max-h-full min-h-64 border border-solid border-neutral-300 rounded-md text-gray-500" >
        {#each messages as msg}
          <AiChatBubble message={msg.content}/>
          <UserChatBubble message={msg.content}/>
        {/each}
      </div>
      {/if}
    </div>
    <div class="bottom-0">
        <SendMessage onsubmit = {(message: string) => {messages.push({content: message, sender: "User"});}}/>
    </div>
  </aside>
{/if}
