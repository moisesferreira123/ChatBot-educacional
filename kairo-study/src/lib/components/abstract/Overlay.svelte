<script lang="ts">
	import { overlayStore, closeOverlay } from '$lib/stores/overlayStore.svelte';
	import { X } from '@lucide/svelte';
	let { id, title, subtitle, form, footer = null } = $props();
</script>

{#if overlayStore.currentOverlay === id}
	<section class="flex fixed z-100 justify-center items-center overflow-y-auto bg-black/80 top-0 left-0 right-0 bottom-0 p-2.5">
		<div class="relative bg-white rounded-2xl overflow-auto">
			<div class="bg-[var(--color1)] text-white text-center rounded-t-2xl px-6 py-10">
				<button
					class="absolute top-0 right-0 m-3.5 cursor-pointer"
					onclick={closeOverlay}><X size=24/></button
				>
				<h2 class="text-xl mb-2.5">{title}</h2>
				<p>{subtitle}</p>
			</div>
			{@render form()}
			{@render footer?.()}
		</div>
	</section>
{/if}

<style>
</style>
