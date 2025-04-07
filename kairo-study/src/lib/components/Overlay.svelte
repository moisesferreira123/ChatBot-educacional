<script lang='ts'>
  import { overlayStore, closeOverlay } from '$lib/stores/overlayStore.svelte';
  let { id, title, subtitle, form, footer = null } = $props();
  //let active = $derived(currentOverlay === id);
  $inspect(overlayStore.currentOverlay);
</script>

{#if overlayStore.currentOverlay === id}
<section class="overlay">
<div class="overlay-style">
  <div class="header-overlay">
    <button class="close-overlay" onclick={() => {closeOverlay()}}>&times;</button>
    <h2>{title}</h2>
    <p>{subtitle}</p>
  </div>
  {@render form()}
  {@render footer?.()}
</div>
</section>
{/if}


<style>
  .overlay {
  display: flex;
  position: fixed;
  z-index: 1000;
  justify-content: center;
  align-items: center;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  overflow-y: auto;
  background-color: rgba(0,0,0,0.7);
  padding: 10px;
}

  .form-style {
		margin: 30px 30px;
	}

	.form-style label {
		color: var(--color3);
		font-weight: 500;
	}

	.form-style input {
		width: 100%;
		height: 40px;
		font-size: 16px;
		margin-bottom: 15px;
		padding-left: 10px;
	}

	.form-style button {
		width: 100%;
		margin-top: 10px;
	}

  .close-overlay {
		position: absolute;
		top: 0px;
		right: 10px;
		font-size: 35px;
		background: none;
		border: none;
		color: white;
		cursor: pointer;
		z-index: 1001;
	}
  .close-overlay:hover {
		font-size: 40px;
		top: -3px;
		right: 8px;
	}

	.header-overlay {
		background-color: var(--color1);
		color: white;
		text-align: center;
		padding: 25px 40px;
	}

	.header-overlay h2 {
		margin-bottom: 10px;
		font-size: 25px;
	}

	.overlay-style > p {
		margin-top: -20px;
		margin-bottom: 30px;
		font-size: small;
		color: var(--color3);
		text-align: center;
		font-weight: 500;
	}

</style>