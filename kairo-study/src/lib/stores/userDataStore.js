import { writable } from 'svelte/store';
import { browser } from '$app/environment';

const defaultValue = { fullName: '' };
const initialValue = browser ? JSON.parse(localStorage.getItem('userData')) ?? defaultValue : defaultValue;

export const userData = writable(initialValue);

if (browser) {
  userData.subscribe(value => {
    localStorage.setItem('userData', JSON.stringify(value));
  });
}