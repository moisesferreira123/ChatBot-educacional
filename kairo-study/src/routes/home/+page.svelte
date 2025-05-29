<script>
  import HeaderLoggedIn from "$lib/components/HeaderLoggedIn.svelte";
	import Intro from "$lib/components/Intro.svelte";
	import NewNoteOverlay from "$lib/components/NewNoteOverlay.svelte";
	import YourNotes from "$lib/components/YourNotes.svelte";
  import { newNoteOverlay } from '$lib/stores/overlayStore.svelte';
	import { userData } from "$lib/stores/userDataStore";
  //TODO: Deixar o HearderLoggedIn mais modular, permitindo a inserção de ícones e textos diferentes

  let firstName = $userData.username;
  let title = `Hello, ${firstName}!`;
</script>
<div class="flex flex-col pointer-events-auto">
  <HeaderLoggedIn/>
  <!-- Trocar User pelo primeiro nome -->
  <main class="p-20">
    <Intro
      title={title}
      subtitle="Welcome! What would you like to study with Kairo today?"
      firstButtonIcon="/images/icons8-plus-16.png"
      firstButtonText="New Note"
      secondButtonIcon="/images/icons8-chat-messages-16.png"
      secondButtonText="Personalized Note"
      buttonClick1="home"
    />
    {#if $newNoteOverlay}
      <NewNoteOverlay />
    {/if}
    <YourNotes />
  </main>
</div>

<style>
  :global body {
    background-color: var(--color2);
  }

  :global(html, body) {
    height: auto;
    min-height: 100%;
    overflow-y: auto;
  }


</style>