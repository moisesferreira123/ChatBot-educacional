// src/lib/stores/overlayStore.ts
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

