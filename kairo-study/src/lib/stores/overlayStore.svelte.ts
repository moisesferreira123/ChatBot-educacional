// src/lib/stores/overlayStore.ts
import { goto } from '$app/navigation'
import { writable } from 'svelte/store';

export const newNoteOverlay = writable(false);
export const newDeckOverlay = writable(false);
export const newFlashcardOverlay = writable(false);
export const sortNotesOverlay = writable(false);
export const sortDecksOverlay = writable(false);
export const sortFlashcardsOverlay = writable(false);
export const deleteWarningOverlay = writable(null);
export const actionOverlay = writable(null);
export const deckManagementOverlay = writable(null);
export const newFlashcardInDeckInterfaceOverlay = writable(null);
export const flashcardDetailsOverlay = writable(null);
export const filterFlashcardsOverlay = writable(null);
export const filterDecksOverlay = writable(null);

export const overlayStore = $state<{currentOverlay: string | null}>({ 
  currentOverlay: null
});

function updateBodyScroll() {
  document.body.style.overflow = overlayStore.currentOverlay ? 'hidden' : 'auto';
}

export function toggleOverlay(id: string) {
  overlayStore.currentOverlay = id;
  updateBodyScroll();
}

export function closeOverlay() {
  overlayStore.currentOverlay = null;
  updateBodyScroll();
}