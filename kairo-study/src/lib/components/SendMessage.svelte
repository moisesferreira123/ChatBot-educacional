<script lang="ts">
  import { selectedChatFeature } from "$lib/stores/chatStore";
  let message = $state('');
  let { onsubmit, disabled = false } = $props<{
    onsubmit: (message: string) => void;
    disabled?: boolean;
  }>();

  function handleSubmit() {
    if (message.trim() === '' || disabled) return;
    onsubmit(message);
    message = '';
  }
</script>

<div class="message-input-area">
  <form on:submit|preventDefault={handleSubmit} class="flex items-center gap-2">
    {#if $selectedChatFeature === 'Smart Summary' || $selectedChatFeature === 'Automatic Flashcards'}
      <button type="button" class="send-file" {disabled} title="Upload file (feature not implemented)">
        <img src="/images/icons8-upload-file-20.png" alt="Upload file">
      </button>
    {/if}
    <textarea
      bind:value={message}
      wrap="soft"
      placeholder="Type your message..."
      class="flex-grow p-2 border border-neutral-300 rounded-md resize-none leading-tight focus:outline-none focus:ring-1 focus:ring-[var(--color1)] text-sm"
      rows="1"
      {disabled}
      on:keydown={(e) => {
        if (e.key === 'Enter' && !e.shiftKey) {
          e.preventDefault();
          handleSubmit();
        }
        // Basic auto-resize logic for textarea (optional)
        const el = e.currentTarget as HTMLTextAreaElement;
        el.style.height = 'auto';
        el.style.height = `${el.scrollHeight}px`;
      }}
    ></textarea>
    <button type="submit" class="send-message" disabled={message.trim() === '' || disabled} title="Send message">
      <img width=20px height=20px src="/images/icons8-send-24.png" alt="Send">
    </button>
  </form>
</div>

<style>
  .message-input-area {
    padding: 8px;
    background-color: var(--white);
  }

  textarea {
    min-height: 40px; /* Min height for one line */
    max-height: 120px; /* Max height before scrolling */
    transition: height 0.1s ease-out;
  }

  .send-message, .send-file {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 40px; /* Match textarea height */
    width: 40px;
    flex-shrink: 0;
    border-radius: calc(0.5rem - 2px);
    cursor: pointer;
    transition: background-color 0.2s;
  }

  .send-message {
    background-color: var(--color1);
  }
  .send-message:enabled:hover {
    background-color: var(--color6);
  }
  .send-message:disabled {
    opacity: 0.5;
    cursor: not-allowed;
  }

  .send-file {
    border: 1px solid var(--color13);
  }
  .send-file:hover {
    background-color: var(--color8);
  }
  .send-file:disabled {
    opacity: 0.5;
    cursor: not-allowed;
  }
</style>