<script lang="ts">
  import { selectedChatFeature } from "$lib/stores/chatStore";
  // TODO: Depois trocar por um store para poder guardar o histórico de mensagens.
  let message = '';
</script>

<div class="message">
  <form action="">
    {#if $selectedChatFeature === 'Automatic Flashcards'}
    <button class="send-file"><img src="/images/icons8-upload-file-20.png" alt=""></button>
    {/if}
    <textarea bind:value={message} wrap="soft" placeholder="Type your message..."></textarea>
    <button class="send-message" disabled={message.trim() === ''}><img width=20px height=20px src="/images/icons8-send-24.png" alt=""></button>
  </form>
</div>

<style>
  .message {
    padding: 16px;
    background-color: var(--white);
    border-bottom-left-radius: calc(0.5rem - 2px);
    border-bottom-right-radius: calc(0.5rem - 2px);
  }

  form {
    display: flex;
    align-items: center;
    gap: 0.5rem;
  }

  textarea {
    all: unset;
    height: 30px;
    line-height: 20px;
    font-size: calc(14/16 * 1rem);
    color: var(--color14);
    padding: 10px 12px 6px 12px;
    border: 1px solid var(--color13);
    border-radius: calc(0.5rem - 2px);
    overflow-wrap: break-word;
    width: 100%;
  }

  textarea:focus {
    outline: 2px solid var(--color14);
  }

  .send-message {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 48px;
    width: 48px;
    flex-shrink: 0;
    background-color: var(--color1);
    border-radius: calc(0.5rem - 2px);
    cursor: pointer;
  }

  .send-message:enabled:hover {
    background-color: var(--color6) ;
  }

  .send-message:disabled {
    opacity: 0.5;
    cursor: auto;
  }

  .send-file {
    display: flex;
    align-items: center;
    justify-content: center;
    flex-shrink: 0;
    width: 48px;
    height: 48px;
    border: 1px solid var(--color13);
    border-radius: calc(0.5rem - 2px);
  }

  .send-file:hover {
    background-color: var(--color8);
    cursor: pointer;
  }
</style>