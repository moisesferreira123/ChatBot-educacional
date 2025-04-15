<script>
  import { fullName } from '$lib/stores/userDataStore.svelte';

  // TODO: Colocar isso depois de integrar o banco de dados
  $: firstName = $fullName.split(' ')[0];

  export let title;
  export let subtitle;
  export let firstButtonIcon;
  export let firstButtonText;
  export let secondButtonIcon;
  export let secondButtonText;
</script>

<h1>{title}</h1>
<p class="subtitle">{subtitle}</p>
<div class="grid-container">
  <button class="first-button">
    <img width="16px" height="16px" src={firstButtonIcon} alt="">
    <p>{firstButtonText}</p>
  </button>
  <button class="second-button">
    <img src={secondButtonIcon} alt="">
    <p>{secondButtonText}</p>
  </button>
</div>

<style>
  h1 {
    font-size: 1.875rem;
    font-weight: 700;
    color: var(--color4);
  }

  .subtitle {
    margin-top: 8px;
    margin-bottom: 32px;
    color: var(--color7);
  }

  .grid-container {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 1rem;
  }

  .first-button {
    background-color: var(--color1);
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 0.5rem;
    min-height: 44px;
    border-radius: calc(0.5rem - 2px);
    transition: 250ms;
  }

  .first-button p {
    font-size: 0.875rem;
    line-height: 1.25rem;
    color: var(--color2);
    font-weight: 500;
  }

  .first-button:hover {
    background-color: var(--color6);
    cursor: pointer;
    transition: 125ms;
  }

  .second-button {
    background-color: var(--white);
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 0.5rem;
    min-height: 44px;
    border-radius: calc(0.5rem - 2px);
    border: 1px solid var(--color1);
    transition: 250ms;
  }

  .second-button p {
    font-size: 0.875rem;
    line-height: 1.25rem;
    color: var(--color1);
    font-weight: 500;
  }

  .second-button:hover {
    background-color: var(--color9);
    cursor: pointer;
    transition: 125ms;
  }

</style>