<script lang="ts">
  import { selectedChatFeature } from "$lib/stores/chatStore";
</script>

<div class="message">
  <form action="">
    <textarea wrap="soft" placeholder="Type your message..."></textarea>
    <button><img src="/images/icons8-send-24.png" alt=""></button>
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

  button {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 48px;
    width: 48px;
    flex-shrink: 0;
    background-color: var(--color1);
    border-radius: calc(0.5rem - 2px);
  }
</style>