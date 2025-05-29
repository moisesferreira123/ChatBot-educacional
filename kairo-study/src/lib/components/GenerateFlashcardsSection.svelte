<script lang="ts">
  import { onMount } from 'svelte';
  import { fetchListDecks } from '$lib/api/decks';
  import { fetchGenerateFlashcardsFromNote } from '$lib/api/flashcards';
  import { page } from '$app/state';

  interface DeckSummary {
    id: number;
    title: string;
  }

  let token: string | null;
  let decks: DeckSummary[] = $state([]);
  let selectedDeckId: number | null = $state(null);
  let flashcardCount: number = $state(5);
  let isLoadingDecks = $state(false);
  let isLoadingGeneration = $state(false);
  let errorMessage = $state<string | null>(null);
  let successMessage = $state<string | null>(null);

  const currentNoteId = $derived(Number(page.params.id));

  onMount(async () => {
    token = localStorage.getItem("token");
    if (!token) {
      errorMessage = "Authentication token not found.";
      return;
    }
    isLoadingDecks = true;
    try {
      const decksData = await fetchListDecks(0, 100, "", "", "createdAtDesc", token);
      if (decksData && decksData.content) {
        decks = decksData.content.map((deck: any) => ({ id: deck.id, title: deck.title }));
      } else {
        decks = [];
      }
    } catch (e: any) {
      errorMessage = "Failed to load decks: " + (e.message || "Unknown error");
      console.error("Error fetching decks:", e);
    } finally {
      isLoadingDecks = false;
    }
  });

  async function handleGenerate() {
    if (!currentNoteId) {
        errorMessage = "Note ID is missing. Cannot generate flashcards.";
        return;
    }
    if (!selectedDeckId) {
      errorMessage = "Please select a deck to add flashcards to.";
      return;
    }
    if (!token) {
      errorMessage = "Authentication token not found.";
      return;
    }

    isLoadingGeneration = true;
    errorMessage = null;
    successMessage = null;

    try {
      const generatedFlashcards = await fetchGenerateFlashcardsFromNote(currentNoteId, selectedDeckId, flashcardCount, token);
      const targetDeck = decks.find(d => d.id === selectedDeckId);
      successMessage = `Successfully generated ${generatedFlashcards.length} flashcards in deck "${targetDeck?.title || 'selected deck'}"!`;
    } catch (e: any) {
      errorMessage = "Failed to generate flashcards: " + (e.message || "Unknown error");
      console.error("Flashcard generation error:", e);
    } finally {
      isLoadingGeneration = false;
    }
  }
</script>

<div class="mt-6 p-4 border border-dashed border-neutral-300 rounded-md bg-neutral-50">
  <h3 class="text-md font-semibold text-gray-800 mb-3">Generate Flashcards with AI</h3>
  {#if isLoadingDecks}
    <p class="text-sm text-gray-600">Loading your decks...</p>
  {:else if decks.length === 0 && !errorMessage}
    <p class="text-sm text-gray-600">
      You don't have any decks yet. Please 
      <a href="/flashcards" class="text-[var(--color1)] hover:underline">create a deck</a> 
      first to generate flashcards into it.
    </p>
  {:else if decks.length > 0}
    <div class="mb-3">
      <label for="deck-select-ai" class="block text-sm font-medium text-gray-700 mb-1">Target Deck:</label>
      <select id="deck-select-ai" bind:value={selectedDeckId} class="w-full p-2 border border-neutral-300 rounded-md text-sm focus:ring-[var(--color1)] focus:border-[var(--color1)] bg-white">
        <option value={null} disabled>-- Select a Deck --</option>
        {#each decks as deck (deck.id)}
          <option value={deck.id}>{deck.title}</option>
        {/each}
      </select>
    </div>
    <div class="mb-3">
      <label for="flashcard-count-ai" class="block text-sm font-medium text-gray-700 mb-1">Number of Flashcards (1-20):</label>
      <input id="flashcard-count-ai" type="number" bind:value={flashcardCount} min="1" max="20" class="w-full p-2 border border-neutral-300 rounded-md text-sm focus:ring-[var(--color1)] focus:border-[var(--color1)] bg-white" />
    </div>
    <button 
      onclick={handleGenerate} 
      disabled={isLoadingGeneration || isLoadingDecks || !selectedDeckId || !currentNoteId || flashcardCount < 1} 
      class="w-full bg-[var(--color1)] text-white px-4 py-2 rounded hover:bg-[var(--color6)] disabled:opacity-60 disabled:cursor-not-allowed"
    >
      {#if isLoadingGeneration}
        Generating...
      {:else}
        Generate AI Flashcards
      {/if}
    </button>
  {/if}

  {#if errorMessage && !isLoadingDecks}
    <p class="text-red-500 text-xs mt-2">{errorMessage}</p>
  {/if}
  {#if successMessage}
    <p class="text-green-600 text-xs mt-2">{successMessage}</p>
  {/if}
</div>