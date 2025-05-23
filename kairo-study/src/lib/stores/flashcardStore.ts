import { writable } from 'svelte/store';

export const createdFlashcard = writable(false);
export const createdFlashcardInManagement = writable(false);
export const deletedFlashcard = writable(false);
export const updatedFlashcard = writable(false);