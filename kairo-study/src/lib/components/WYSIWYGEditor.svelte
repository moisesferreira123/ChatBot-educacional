<script lang="ts">
	import 'katex/dist/katex.min.css';
	import { onMount, onDestroy } from 'svelte';
	import { Editor } from '@tiptap/core';
	import StarterKit from '@tiptap/starter-kit';
	import Mathematics from '@tiptap-pro/extension-mathematics';
	import AiComplete from '$lib/extensions/AiComplete';
	import Placeholder from '@tiptap/extension-placeholder';
	import {
		Bold,
		Italic,
		Code,
		Strikethrough,
		Heading1,
		Heading2,
		Heading3,
		List,
		ListOrdered,
		Undo,
		Redo,
		Send
	} from '@lucide/svelte';
	import handleAISubmit from '$lib/extensions/handleAISubmit.svelte';
	import { clickOutside } from '$lib/actions/clickOutside'; // Import clickOutside

	interface Props {
		content: string | undefined;
		onContentChange: (html: string) => void;
		token: string | null;
	}

	let { content, onContentChange = () => {}, token }: Props = $props();
	let element: HTMLElement | undefined; // Changed Element to HTMLElement for clarity
	let editor = $state.raw<Editor | undefined>();
	let editorBox = $state.raw({ current: editor });

	// AI Prompt State
	let showAIPrompt = $state(false);
	let aiPromptValue = $state(''); // Renamed from aiPrompt to avoid conflict with potential component
	let isLoadingAI = $state(false); // Renamed from isLoading
	let editorSelectionPos = $state(0); // Renamed from editorPos
	let promptPosition = $state({ top: 0, left: 0 });
	let aiPromptInputRef: HTMLInputElement | null = $state(null);

	onMount(() => {
		editor = new Editor({
			element: element,
			content,
			extensions: [
				StarterKit,
				Mathematics,
				AiComplete.configure({
					onTrigger: (pos: number) => {
						editorSelectionPos = pos;
						if (editor?.view) {
							const coords = editor.view.coordsAtPos(pos);
							promptPosition = { top: coords.bottom + 5, left: coords.left };
						}
						showAIPrompt = true;
						// Focus the input next tick after it's rendered
						setTimeout(() => {
							aiPromptInputRef?.focus();
						}, 0);
					}
				}),
				Placeholder.configure({
					placeholder: 'Write something here or type @ai to get AI suggestions...'
				})
			],
			onTransaction: () => {
				editorBox = { current: editor };
			},
			onUpdate: ({ editor: currentEditor }) => {
				onContentChange(currentEditor.getHTML());
			},
			// Close AI prompt on editor blur or selection change if needed
			onBlur: () => {
				// showAIPrompt = false; // Consider if this is desired UX
			},
			onSelectionUpdate: () => {
				// If prompt is open and selection moves away, consider closing
				// This might be too aggressive, so evaluate UX
			}
		});
	});

	onDestroy(() => {
		if (editor) {
			editor.destroy();
		}
	});

	async function generateAIContent() {
		if (!aiPromptValue.trim()) return;
		isLoadingAI = true;
		// The AiComplete extension deletes "@ai", so insert at editorSelectionPos
		await handleAISubmit(aiPromptValue, editorSelectionPos, editor!, token);
		isLoadingAI = false;
		showAIPrompt = false;
		aiPromptValue = '';
	}

	function closeAiPrompt() {
		showAIPrompt = false;
		aiPromptValue = '';
		editor?.chain().focus().run(); // Return focus to editor
	}

	// Handle Escape key for the AI prompt
	function handleAiPromptKeydown(event: KeyboardEvent) {
		if (event.key === 'Escape') {
			closeAiPrompt();
		}
		if (event.key === 'Enter' && !isLoadingAI) {
			event.preventDefault(); // Prevent newline in editor if prompt is focused
			generateAIContent();
		}
	}
</script>

{#if editor}
	<div
		class="sticky top-0 z-10 flex gap-1 rounded-t-md border-b border-neutral-200 bg-neutral-50 p-2"
	>
		{#snippet EditorButton(action, isActive = false, ButtonIcon, disabled = false)}
			<button
				class="ring-offset-background focus-visible:ring-ring hover:text-accent-foreground inline-flex h-8 w-8 items-center justify-center gap-2 whitespace-nowrap rounded-md font-medium transition-colors hover:bg-neutral-200 focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg]:size-4 [&_svg]:shrink-0"
				class:bg-neutral-300={isActive}
				{disabled}
				onclick={action}
				type="button"
			>
				<ButtonIcon size={16} />
			</button>
		{/snippet}

		{#each [{ action: () => editor
						.chain()
						.focus()
						.toggleBold()
						.run(), isActive: editor.isActive('bold'), ButtonIcon: Bold }, { action: () => editor
						.chain()
						.focus()
						.toggleItalic()
						.run(), isActive: editor.isActive('italic'), ButtonIcon: Italic }, { action: () => editor
							.chain()
							.focus()
							.undo()
							.run(), ButtonIcon: Undo, disabled: !editor.can().undo() }, { action: () => editor
						.chain()
						.focus()
						.redo()
						.run(), ButtonIcon: Redo, disabled: !editor.can().redo() }, { action: () => editor
						.chain()
						.focus()
						.toggleBulletList()
						.run(), isActive: editor.isActive('bulletList'), ButtonIcon: List }, { action: () => editor
							.chain()
							.focus()
							.toggleOrderedList()
							.run(), isActive: editor.isActive('orderedList'), ButtonIcon: ListOrdered }, { action: () => editor
							.chain()
							.focus()
							.toggleStrike()
							.run(), isActive: editor.isActive('strike'), ButtonIcon: Strikethrough }, { action: () => editor
							.chain()
							.focus()
							.toggleCode()
							.run(), isActive: editor.isActive('code'), ButtonIcon: Code }, { action: () => editor
						.chain()
						.focus()
						.toggleHeading({ level: 1 })
						.run(), isActive: editor.isActive( 'heading', { level: 1 } ), ButtonIcon: Heading1 }, { action: () => editor
							.chain()
							.focus()
							.toggleHeading({ level: 2 })
							.run(), isActive: editor.isActive( 'heading', { level: 2 } ), ButtonIcon: Heading2 }, { action: () => editor
							.chain()
							.focus()
							.toggleHeading({ level: 3 })
							.run(), isActive: editor.isActive( 'heading', { level: 3 } ), ButtonIcon: Heading3 }] as button}
			{@render EditorButton(button.action, button.isActive, button.ButtonIcon, button.disabled)}
		{/each}
	</div>
{/if}

{#if showAIPrompt}
	<div
		class="ai-cursor-prompt"
		style="position: fixed; top: {promptPosition.top}px; left: {promptPosition.left}px;"
		use:clickOutside={closeAiPrompt}
		onkeydown={handleAiPromptKeydown}
	>
		<input
			bind:this={aiPromptInputRef}
			type="text"
			bind:value={aiPromptValue}
			placeholder="Type your AI prompt..."
			disabled={isLoadingAI}
			class="ai-prompt-input"
		/>
		<button
			onclick={generateAIContent}
			disabled={isLoadingAI || !aiPromptValue.trim()}
			class="ai-prompt-button"
		>
			{#if isLoadingAI}
				<span class="loading-dots"><span>.</span><span>.</span><span>.</span></span>
			{:else}
				<Send size={16} />
			{/if}
		</button>
	</div>
{/if}

<div
	class="rich-editor flex-grow overflow-y-auto rounded-b-md border border-neutral-200 bg-white p-5"
	bind:this={element}
	onclick={() => editor?.chain().focus().run()}
></div>

<style>
	.ai-cursor-prompt {
		display: flex;
		align-items: center;
		padding: 6px 8px;
		background-color: white;
		border: 1px solid #ccc;
		border-radius: 6px;
		box-shadow: 0 2px 10px rgba(0, 0, 0, 0.15);
		z-index: 100; /* Ensure it's above editor content */
	}

	.ai-prompt-input {
		border: none;
		outline: none;
		padding: 6px;
		font-size: 14px;
		flex-grow: 1;
		min-width: 200px; /* Adjust as needed */
	}

	.ai-prompt-button {
		background: none;
		border: none;
		cursor: pointer;
		padding: 6px;
		margin-left: 6px;
		display: flex;
		align-items: center;
		justify-content: center;
		color: var(--color1); /* Use your theme color */
	}
	.ai-prompt-button:disabled {
		opacity: 0.5;
		cursor: not-allowed;
	}
	.ai-prompt-button:enabled:hover {
		color: var(--color6); /* Darker shade for hover */
	}

	.loading-dots span {
		animation: blink 1.4s infinite both;
		font-size: 1.2em;
	}
	.loading-dots span:nth-child(2) {
		animation-delay: 0.2s;
	}
	.loading-dots span:nth-child(3) {
		animation-delay: 0.4s;
	}
	@keyframes blink {
		0% {
			opacity: 0.2;
		}
		20% {
			opacity: 1;
		}
		100% {
			opacity: 0.2;
		}
	}

	/* Editor styles */
	:global(.rich-editor) {
		/* Keep existing styles, ensure it has enough padding if the toolbar is overlaying or fixed */
		height: 100%; /* Ensure there's space to write */
		background-color: white;
		font-family: 'Montserrat', sans-serif;
		font-size: 16px;
		line-height: 1.5;
	}
	:global(.rich-editor:focus-within) {
		/* or :global(.ProseMirror:focus) if that's the focused element */
		outline: none;
		/* border-color: var(--color1); Example focus style */
	}	:global(.rich-editor *:focus) {
		outline: none;
	}
	:global(.rich-editor p.is-editor-empty:first-child::before) {
		color: #adb5bd;
		content: attr(data-placeholder);
		float: left;
		height: 0;
		pointer-events: none;
	}
	:global(.rich-editor h1) {
		font-size: 1.7em;
		font-weight: bold;
		margin-top: 1.5em;
		margin-bottom: 0.2em;
	}
	:global(.rich-editor h2) {
		font-size: 1.5em;
		font-weight: bold;
		margin-top: 1.2em;
		margin-bottom: 0.2em;
	}
	:global(.rich-editor h3) {
		font-size: 1.17em;
		font-weight: bold;
		margin-top: 1em;
		margin-bottom: 0.2em;
	}
	:global(.rich-editor p) {
		font-size: 1em;
		margin: 0;
	}
	:global(.rich-editor code) {
		background-color: var(--color-neutral-200);
		padding: 0.3em 0.5em;
		border-radius: 3px;
	}
	:global(.rich-editor ul) {
		list-style-type: disc;
		margin: 0;
		padding-left: 20px;
	}
	:global(.rich-editor li) {
		margin: 0;
		padding: 0;
	}
	:global(.rich-editor ol) {
		list-style-type: decimal;
		margin: 0;
		padding-left: 20px;
	}
</style>
