import { writable } from "svelte/store";

export const sortTypeNotes = writable('updatedAtDesc');
export const sortTypeDecks = writable('lastReviewedAtDesc');
export const sortTypeFlashcards = writable('lastReviewedAtDesc');