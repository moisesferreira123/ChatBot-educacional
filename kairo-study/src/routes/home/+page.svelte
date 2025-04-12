<script>
  import HeaderLoggedIn from "$lib/components/HeaderLoggedIn.svelte";
	import Intro from "$lib/components/Intro.svelte";
</script>

<HeaderLoggedIn/>
<!-- Trocar User pelo primeiro nome -->
<main>
  <Intro
    title="Hello, User!"
    subtitle="Welcome! What would you like to study with Kairo today?"
    firstButtonIcon="/images/icons8-plus-16.png"
    firstButtonText="New Chat"
    secondButtonIcon="/images/icons8-chat-messages-16.png"
    secondButtonText="Personalized Chat"
  />
</main>

<style>
  :global body {
    background-color: var(--color2);
  }

  main {
    padding-top: 85px;
    margin: 0 25px;
  }
</style>