<script lang="ts">
	import { selectedChatFeature } from "$lib/stores/chatStore";

  function handleClick(feature: string) {
    selectedChatFeature.set(feature);
  }
</script>

<aside>
  <h2>Features</h2>
  <nav>
    <button on:click={() => handleClick('Smart Summary')}
      class:active={'Smart Summary' === $selectedChatFeature}>
      <img src={'Smart Summary' === $selectedChatFeature
        ? "/images/icons8-brief-white-24.png"
        : "/images/icons8-brief-gray-24.png"} alt="">
      <p>Smart Summary</p>
    </button>
    <button on:click={() => handleClick('Automatic Flashcards')}
      class:active={'Automatic Flashcards' === $selectedChatFeature}>
      <img src={'Automatic Flashcards' === $selectedChatFeature 
        ? "/images/icons8-flashcards-white-24.png"
        : "/images/icons8-flashcards-gray-24.png"} alt="">
      <p>Automatic Flashcards</p>
    </button>
    <button on:click={() => handleClick('Questions and Answers')}
      class:active={'Questions and Answers' === $selectedChatFeature}>
      <img src={'Questions and Answers' === $selectedChatFeature
        ? "/images/icons8-faq-white-24.png"
        : "/images/icons8-faq-gray-24.png"} alt="">
      <p>Questions and Answers</p>
    </button>
    <button on:click={() => handleClick('Content Suggestions')}
      class:active={'Content Suggestions' === $selectedChatFeature}>
      <img src={'Content Suggestions' === $selectedChatFeature
        ? "/images/icons8-camera-white-24.png"
        : "/images/icons8-camera-gray-24.png"} alt="">
      <p>Content Suggestions</p>
    </button>
    <button on:click={() => handleClick('Test Simulation')}
      class:active={'Test Simulation' === $selectedChatFeature}>
      <img src={'Test Simulation' === $selectedChatFeature
        ? "/images/icons8-exam-white-24.png"
        : "/images/icons8-exam-gray-24.png"} alt="">
      <p>Test Simulation</p>
    </button>
  </nav>
</aside>

<style>
  aside {
    background-color: var(--white);
    padding: 16px;
    border-radius: 0.5rem;
    align-self: flex-start;
    box-shadow: rgba(0, 0, 0, 0.05) 0px 1px 2px 0px;
  }

  h2 {
    font-size: 1rem;
    font-weight: 600;
    line-height: 24px;
    color: var(--color11);
    margin-bottom: 16px;
  }

  button {
    display: flex;
    align-items: center;
    width: 100%;
    padding: 8px 12px;
    color: var(--color11);
    font-size: calc(14/16 * 1rem);
    line-height: 20px;
    cursor: pointer;
    border-radius: calc(0.5rem - 2px);
    transition: 125ms;
  }

  button:not(.active):hover {
    background-color: var(--color12);
    transition: 125ms;
  }

  img {
    margin-right: 8px;
  }

  .active {
    color: var(--white);
    background-color: var(--color1);
  }
</style>