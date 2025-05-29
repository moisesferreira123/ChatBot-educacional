<script lang="ts">
	import { LucideRotateCw, X } from '@lucide/svelte';
	import GenerateFlashcardsSection from './GenerateFlashcardsSection.svelte';

	// Props to control visibility and handle closing
	let { show = $bindable(), onClose } = $props();
</script>

{#if show}
	<aside
		class="bottom-0 right-0 top-0 z-50 w-80 flex-shrink-0 transform rounded-md border-neutral-300 bg-white shadow-lg transition-transform duration-300 ease-in-out {show
			? 'translate-x-0'
			: 'translate-x-full'} pt-6"
	>
		<header class="flex items-center justify-between border-b border-neutral-300 p-4">
			<h2 class="text-lg font-semibold text-gray-900">Flashcards</h2>
			<button class="text-gray-500 hover:text-black" onclick={onClose}>
				<X size={20} />
			</button>
		</header>
		<div class="flex-grow overflow-y-auto p-4">
			<div class="mb-6 flex flex-col items-center justify-center text-center text-gray-500">
				<div class="mb-4 rounded-full bg-gray-100 p-3">
					<LucideRotateCw size={20} />
				</div>
				<strong>Flashcard Hub</strong>
				<p class="text-sm">
					Manage manually created flashcards or use AI to generate new ones from your note content.
				</p>
			</div>

			<GenerateFlashcardsSection />
		</div>
	</aside>
{/if}

<style>
	/* Ensure the aside itself doesn't have padding that restricts its children */
	aside {
		padding-top: 0; /* Header has its own padding */
	}
</style>
