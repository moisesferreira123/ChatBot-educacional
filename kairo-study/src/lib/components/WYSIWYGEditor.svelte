
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
		Redo
	} from '@lucide/svelte';
	import handleAISubmit from '$lib/extensions/handleAISubmit.svelte';
  //TODO: import { EditorContent } from '@tiptap/svelte';
  interface Props {
    content: string | undefined,
    onContentChange: (html: string) => void,
	token: string | null,
  }
  
  let {content, onContentChange = () => {}, token}: Props = $props();
	let element: Element | undefined;
  let editor = $state.raw<Editor | undefined>();
  let editorBox = $state.raw({current: editor});
  let showAIPrompt = $state(false);
  let aiPrompt = $state('');
  let isLoading = $state(false);
  let editorPos = 0;
  
  //TODO: Use a sanitizer for the HTML output
  //TODO: Use a Markdown parser for the AI output
	onMount(() => {
		editor = new Editor({
			element: element,
			content,
			extensions: [
				StarterKit,
				Mathematics,
				AiComplete.configure({
					onTrigger: (pos: number) => {
            editorPos = pos;
						showAIPrompt = true;
					}
				}),
				Placeholder.configure({
					placeholder: 'Write something here or type @ai to get AI suggestions...'
				})
			],
			onTransaction: () => {
				// force re-render so `editor.isActive` works as expected
				editorBox = {current: editor};
			},
			onUpdate: ({editor}) => {
				onContentChange(editor.getHTML());
			}
		});
	});

	onDestroy(() => {
		if (editor) {
			editor.destroy();
		}
	});

  async function generateButtonClicked() {
    isLoading = true;
    await handleAISubmit(aiPrompt, editorPos, editor, token);
    console.log(content);
    isLoading = false;
    showAIPrompt = false;
    aiPrompt = '';
  }
</script>

{#if editor}
	{#if showAIPrompt}
		<div class="ai-modal">
			<input
				type="text"
				bind:value={aiPrompt}
				placeholder="Enter your AI prompt"
				disabled={isLoading}
			/>
			<button onclick={generateButtonClicked} disabled={isLoading}>
				{isLoading ? 'Generating...' : 'Generate'}
			</button>
		</div>
	{/if}

	<div class="flex gap-2 p-2 align-middle">
		{#snippet EditorButton(action: any, isActive = false, ButtonIcon: any, disabled = false)}
			<button
				class="ring-offset-background focus-visible:ring-ring hover:text-accent-foreground inline-flex h-8 w-8 items-center justify-center gap-2 whitespace-nowrap rounded-md font-medium transition-colors hover:bg-neutral-300/50 focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg]:size-4 [&_svg]:shrink-0"
				class:bg-neutral-300={isActive}
				{disabled}
				onclick={action}
			>
        <ButtonIcon size={16}/>
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
						.run(), ButtonIcon: Undo }, { action: () => editor
						.chain()
						.focus()
						.redo()
						.run(), ButtonIcon: Redo }, { action: () => editor
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
			{@render EditorButton(button.action, button.isActive, button.ButtonIcon)}
		{/each}
	</div>
{/if}
<div class="rich-editor overflow-scroll p-5" bind:this={element}></div>

<style>
	:global .rich-editor {
		border: 1px solid #ccc;
		border-radius: 4px;
		padding: 10px;
		min-height: 100%;
		background-color: white;
		font-family: 'Montserrat', sans-serif;
		font-size: 16px;
		line-height: 1.5;
	}
	:global .rich-editor *:focus {
		outline: none;
	}
	:global .rich-editor h1 {
		font-size: 2em;
		font-weight: bold;
		margin: 0;
	}

	:global .rich-editor h2 {
		font-size: 1.5em;
		font-weight: bold;
		margin: 0;
	}
	:global .rich-editor h3 {
		font-size: 1.17em;
		font-weight: bold;
		margin: 0;
	}
	:global .rich-editor p {
		font-size: 1em;
		margin: 0;
	}

	:global .rich-editor code {
		background-color: var(--color-neutral-200);
		padding: 0.3em 0.5em;
		border-radius: 3px;
	}

	:global .rich-editor ul {
		list-style-type: disc;
		margin: 0;
		padding-left: 20px;
	}
	:global .rich-editor li {
		margin: 0;
		padding: 0;
	}
	:global .rich-editor ol {
		list-style-type: decimal;
		margin: 0;
		padding-left: 20px;
	}

	:global .tiptap p.is-editor-empty:first-child::before {
		color: #adb5bd;
		content: attr(data-placeholder);
		float: left;
		height: 0;
		pointer-events: none;
	}
</style>
