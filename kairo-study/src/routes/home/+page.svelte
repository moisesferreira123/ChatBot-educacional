<script>
  import HeaderLoggedIn from "$lib/components/HeaderLoggedIn.svelte";
	import Intro from "$lib/components/Intro.svelte";
  //TODO: Deixar o HearderLoggedIn mais modular, permitindo a inserção de ícones e textos diferentes
</script>
<div class="flex flex-col">
  <HeaderLoggedIn/>
  <!-- Trocar User pelo primeiro nome -->
  <main class="!p-20">
    <Intro
      title="Hello, User!"
      subtitle="Welcome! What would you like to study with Kairo today?"
      firstButtonIcon="/images/icons8-plus-16.png"
      firstButtonText="New Chat"
      secondButtonIcon="/images/icons8-chat-messages-16.png"
      secondButtonText="Personalized Chat"
    />
  </main>
</div>

<style>
  :global body {
    background-color: var(--color2);
  }

</style>