import { writable } from 'svelte/store';

export const createdDeck = writable(false);
export const deletedDeck = writable(false);
export const updatedDeck = writable(false);