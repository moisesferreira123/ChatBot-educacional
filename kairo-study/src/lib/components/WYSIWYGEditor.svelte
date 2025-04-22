<script>
	import { onMount, onDestroy } from 'svelte';
	import { Editor } from '@tiptap/core';
	import StarterKit from '@tiptap/starter-kit';
	import { Bold, Italic, Code, Strikethrough, Heading1, Heading2, Heading3, Underline } from '@lucide/svelte';

	let element;
	let editor;

	onMount(() => {
		editor = new Editor({
			element: element,
			extensions: [StarterKit],
			content: '<p>Write something here... </p>',
			onTransaction: () => {
				// force re-render so `editor.isActive` works as expected
				editor = editor;
			},
		});
	});

	onDestroy(() => {
		if (editor) {
			editor.destroy();
		}
	});
</script>

{#if editor}
  <div class="flex gap-2 mb-4 ">
    {#snippet EditorButton(focusAction, isActive, icon)}
      <button class="{ isActive ? "bg-neutral-300" : ""} inline-flex items-center justify-center gap-2 whitespace-nowrap rounded-md font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg]:size-4 [&_svg]:shrink-0 hover:bg-neutral-300/50 hover:text-accent-foreground h-8 w-8"
        onclick={focusAction}
        class:active={isActive}
      >
        <svelte:component this={icon} size={16} />
      </button>
    {/snippet}

    {#each [
      { action: () => editor.chain().focus().toggleBold().run(), isActive: editor.isActive('bold'), icon: Bold },
      { action: () => editor.chain().focus().toggleItalic().run(), isActive: editor.isActive('italic'), icon: Italic },
      // { action: () => editor.chain().focus().toggleUnderline().run(), isActive: editor.isActive('underline'), icon: Underline },
      { action: () => editor.chain().focus().toggleStrike().run(), isActive: editor.isActive('strike'), icon: Strikethrough },
      { action: () => editor.chain().focus().toggleCode().run(), isActive: editor.isActive('code'), icon: Code },
      { action: () => editor.chain().focus().toggleHeading({ level: 1 }).run(), isActive: editor.isActive('heading', { level: 1 }), icon: Heading1},
      { action: () => editor.chain().focus().toggleHeading({ level: 2 }).run(), isActive: editor.isActive('heading', { level: 2 }), icon: Heading2},
      { action: () => editor.chain().focus().toggleHeading({ level: 3 }).run(), isActive: editor.isActive('heading', { level: 3 }), icon: Heading3},
    ] as button}
      {@render EditorButton(button.action, button.isActive, button.icon)}
    {/each}
  </div>
  
  {/if}
<div class="rich-editor p-5" bind:this={element} />

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
	/* button.active {
		background: black;
		color: white;
	} */
</style>
