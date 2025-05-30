<script lang='ts'>
	import { menuStore } from "$lib/stores/mobileMenuStore.svelte";
  	import { toggleOverlay } from "$lib/stores/overlayStore.svelte";
	import Overlay from "./abstract/Overlay.svelte";
</script>

<div class="mobile-menu {menuStore.active ? 'active' : ''}">
	<a class="features-mobile-menu" style="padding-top: 10px;" href="#features">Features</a>
	<a href="#about">About</a>
	<div class="log-in" onclick={toggleOverlay("login")}>Log in</div>
</div>

<style>
  .mobile-menu {
		display: none;
		flex-direction: column;
		background-color: white;
		position: fixed;
		top: 57px; /* ajusta baseado na altura da navbar */
		right: 10px;
		border-radius: 8px;
		box-shadow: 0 2px 8px rgba(0, 0, 0, 0.2);
		z-index: 100;
	}

	.mobile-menu a {
		padding: 5px 15px;
		border-bottom: 1px solid rgb(245, 240, 240);
	}

	.mobile-menu div {
		padding: 5px 15px;
	}

	.mobile-menu a:hover {
		background-color: rgba(1, 1, 44, 0.1);
		color: var(--color1);
	}

	.mobile-menu .features-mobile-menu {
		border-top-right-radius: 8px;
		border-top-left-radius: 8px;
	}

	.mobile-menu div:hover {
		background-color: rgba(1, 1, 44, 0.1);
		color: var(--color1);
		border-bottom-right-radius: 8px;
		border-bottom-left-radius: 8px;
		cursor: pointer;
	}

  @media (max-width: 545px) {

		.mobile-menu.active {
			display: flex;
		}
	}
</style>