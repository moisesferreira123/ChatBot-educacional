<script lang="ts">
	import { selectedChatFeature } from "$lib/stores/chatStore";

  function handleClick(feature: string) {
    selectedChatFeature.set(feature);
  }

  const asideElements = [
    {
      actionName: 'Smart Summary',
      iconInactive: '/images/icons8-brief-gray-24.png',
      iconActive: '/images/icons8-brief-white-24.png',
    },
    {
      actionName: 'Automatic Flashcards',
      iconInactive: '/images/icons8-flashcards-gray-24.png',
      iconActive: '/images/icons8-flashcards-white-24.png',
    },
    {
      actionName: 'Questions and Answers',
      iconInactive: '/images/icons8-faq-gray-24.png',
      iconActive: '/images/icons8-faq-white-24.png',
    },
    {
      actionName: 'Content Suggestions',
      iconInactive: '/images/icons8-camera-gray-24.png',
      iconActive: '/images/icons8-camera-white-24.png',
    },
    {
      actionName: 'Test Simulation',
      iconInactive: '/images/icons8-exam-gray-24.png',
      iconActive: '/images/icons8-exam-white-24.png',
    },
  ]
</script>

{#snippet asideElement({actionName, iconInactive, iconActive})}
  <button on:click={() => handleClick(actionName)}
    class:active={actionName === $selectedChatFeature}>
    <img src={actionName === $selectedChatFeature
      ? iconActive
      : iconInactive} alt="">
    <p>{actionName}</p>
  </button>
{/snippet}

<aside>
  <h2>Features</h2>
  <nav>
    {#each asideElements as Element} 
      {@render asideElement(Element)} 
    {/each}
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