<script lang="ts">
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
  <form onsubmit={handleSubmit} class="flex items-center gap-2">
    <textarea
      bind:value={message}
      wrap="soft"
      placeholder="Type your message..."
      class="flex-grow p-2 border border-neutral-300 rounded-md resize-none leading-tight focus:outline-none focus:ring-1 focus:ring-[var(--color1)] text-sm"
      rows="1"
      {disabled}
      onkeydown={(e) => {
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
    min-height: 40px;
    max-height: 120px;
    transition: height 0.1s ease-out;
  }

  .send-message {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 40px;
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
</style>