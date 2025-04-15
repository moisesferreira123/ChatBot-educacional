<script>
  import AsideChat from "$lib/components/AsideChat.svelte";
  import HeaderLoggedIn from "$lib/components/HeaderLoggedIn.svelte";
</script>

<HeaderLoggedIn/>
<main>
  <AsideChat/>
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