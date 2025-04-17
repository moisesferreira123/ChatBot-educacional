<script>
  import AsideChat from "$lib/components/AsideChat.svelte";
	import Chat from "$lib/components/Chat.svelte";
  import HeaderLoggedIn from "$lib/components/HeaderLoggedIn.svelte";
	import { selectedChatFeature } from "$lib/stores/chatStore";

  $: feature = $selectedChatFeature;
</script>

<HeaderLoggedIn/>
<main>
  <AsideChat/>
  <Chat title={feature}/>
</main>

<style>
  :global body {
    background-color: var(--color2);
  }

  main {
    display: grid;
    grid-template-columns: minmax(0, 256px) 1fr;
    padding-top: 96px;
    margin: 0 25px;
    gap: 1rem;
    /* TODO: Caso queiramos colocar mais coisas no Aside, retirar isso. */
    height: calc(100vh - 32px);
  }
</style>