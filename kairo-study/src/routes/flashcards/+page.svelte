<script>
  import HeaderLoggedIn from "$lib/components/HeaderLoggedIn.svelte";
  import Intro from "$lib/components/Intro.svelte";
</script>

<HeaderLoggedIn/>
<main>
  <Intro
    title="Flashcards"
    subtitle="Manage your flashcards for spaced review"
    firstButtonIcon="/images/icons8-plus-16.png"
    firstButtonText="Create New Flashcards"
    secondButtonIcon="/images/icons8-chat-messages-16.png"
    secondButtonText="Start Review"
  />
</main>

<style>
  :global body {
    background-color: var(--color2);
  }

  main {
    padding-top: 96px;
    margin: 0 25px;
  }
</style>