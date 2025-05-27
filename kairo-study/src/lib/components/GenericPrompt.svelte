<script lang="ts">
  import { genericPromptStore, closeGenericPrompt, type GenericPromptConfig, type InputConfig } from '$lib/stores/overlayStore.svelte';
  import { X } from '@lucide/svelte';
  import { onMount, onDestroy } from 'svelte';

  let currentConfig: GenericPromptConfig | null = $state(null);
  let inputValues = $state<Record<string, string>>({});
  let unsubscribe: (() => void) | null = null;

  onMount(() => {
    unsubscribe = genericPromptStore.subscribe(value => {
      currentConfig = value;
      if (value && value.inputsConfig) {
        const initialValues: Record<string, string> = {};
        for (const input of value.inputsConfig) {
          initialValues[input.id] = input.initialValue;
        }
        inputValues = initialValues;
      } else {
        inputValues = {};
      }
    });
  });

  onDestroy(() => {
    if (unsubscribe) {
      unsubscribe();
    }
  });

  function handleClose() {
    closeGenericPrompt();
  }

  function handleActionButtonClick(action: (currentInputValues: Record<string, string>) => void | Promise<void>) {
    // Pass a copy of inputValues to prevent direct modification issues if action is async
    action({ ...inputValues });
    // The action itself should call closeGenericPrompt() if it needs to close the dialog.
  }

  // Ensure that when the prompt is shown, focus is managed appropriately, e.g., on the first input.
  let firstInputRef: HTMLElement | null = $state(null);
  $effect(() => {
    if (currentConfig && currentConfig.inputsConfig && currentConfig.inputsConfig.length > 0 && firstInputRef) {
      setTimeout(() => firstInputRef?.focus(), 50); // Delay to ensure element is visible
    }
  });

</script>

{#if currentConfig}
<div class="fixed inset-0 bg-black/80 flex justify-center items-center z-[100]" onkeydown={(e) => e.key === 'Escape' && handleClose()}>
  <div class="relative bg-white w-full max-w-lg p-6 rounded-lg shadow-lg mx-4">
    <button
      aria-label="Close dialog"
      onclick={handleClose}
      class="absolute top-3 right-3 text-gray-500 hover:text-black transition-colors cursor-pointer p-1 rounded-full hover:bg-gray-100"
    >
      <X size={20} />
    </button>

    {#if currentConfig.title}
      <h2 class="text-xl font-bold text-gray-800 mb-3">{currentConfig.title}</h2>
    {/if}

    {#if currentConfig.description}
      <p class="text-sm text-gray-600 mb-4 whitespace-pre-wrap">{currentConfig.description}</p>
    {/if}

    {#if currentConfig.inputsConfig && currentConfig.inputsConfig.length > 0}
    <div class="space-y-4">
      {#each currentConfig.inputsConfig as inputConfig, index (inputConfig.id)}
        <div>
          <label for={inputConfig.id} class="block text-sm font-medium text-gray-700 mb-1">
            {inputConfig.label}
          </label>
          {#if inputConfig.type === 'textarea'}
            <textarea
              id={inputConfig.id}
              placeholder={inputConfig.placeholder}
              bind:value={inputValues[inputConfig.id]}
              required={inputConfig.required}
              rows={3}
              class="mt-1 block w-full h-auto rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm p-2 text-gray-800"
              {#if index === 0} bind:this={firstInputRef} {/if}
            ></textarea>
          {:else}
            <input
              type={inputConfig.type || 'text'}
              id={inputConfig.id}
              placeholder={inputConfig.placeholder}
              bind:value={inputValues[inputConfig.id]}
              required={inputConfig.required}
              class="mt-1 block w-full h-10 rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm p-2 text-gray-800"
              {#if index === 0} bind:this={firstInputRef} {/if}
            />
          {/if}
        </div>
      {/each}
    </div>
    {/if}

    {#if currentConfig.actionButtons && currentConfig.actionButtons.length > 0}
    <div class="flex justify-end gap-3 mt-6">
      {#each currentConfig.actionButtons as buttonConfig (buttonConfig.text)}
        <button
          onclick={() => handleActionButtonClick(buttonConfig.action)}
          class="{buttonConfig.style || 'bg-indigo-600 text-white hover:bg-indigo-700'} min-w-[80px] text-sm font-medium px-4 py-2 rounded-md shadow-sm transition-colors duration-150 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500'"
        >
          {buttonConfig.text}
        </button>
      {/each}
    </div>
    {/if}
  </div>
</div>
{/if}
