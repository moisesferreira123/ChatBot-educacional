<script>
  import HeaderLoggedIn from "$lib/components/HeaderLoggedIn.svelte";
  import Intro from "$lib/components/Intro.svelte";
  import NewDeckOverlay from "$lib/components/NewDeckOverlay.svelte";
  import Decks from "$lib/components/Decks.svelte";
  import { newDeckOverlay } from "$lib/stores/overlayStore.svelte";
</script>

<HeaderLoggedIn/>
<main class="p-20">
  <Intro
    title="Flashcards"
    subtitle="Manage your flashcards for spaced review"
    firstButtonIcon="/images/icons8-plus-math-16.png"
    firstButtonText="Create New Deck"
    secondButtonIcon="/images/icons8-view-16.png"
    secondButtonText="Study All Due Cards"
    buttonClick1="flashcards"
  />
  {#if $newDeckOverlay}
    <NewDeckOverlay />
  {/if}
  <Decks />
</main>

<style>
  :global body {
    background-color: var(--color2);
  }

</style>