<script lang='ts'>
  import { menuStore } from "$lib/stores/mobileMenuStore.svelte";
</script>
<button class="hamburger {menuStore.active ? 'active' : ''}" onclick={() => {menuStore.active = !menuStore.active}}>&#9776;</button>

<style>
  .hamburger {
		display: none;
		font-size: 30px;
		background: none;
		width: 40px;
		height: 40px;
		padding-bottom: 5px;
		border: none;
		cursor: pointer;
		color: var(--color4);
	}

	@media (max-width: 545px) {
    .hamburger {
			display: flex;
			flex-direction: column;
			justify-content: center;
		}

		.hamburger:hover {
			box-shadow: 0 0 2px rgba(1, 1, 44, 1);
			border-radius: 10px;
		}

		.hamburger.active {
			box-shadow: 0 0 2px var(--color4);
			border-radius: 10px;
			background-color: rgba(1, 1, 44, 0.2);
		}

		.hamburger.active:hover {
			background-color: rgba(1, 1, 44, 0.1);
		}
}
</style>