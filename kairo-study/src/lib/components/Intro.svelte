<script lang='ts'>
	import { goto } from '$app/navigation';
	import { allDueFlashcards } from '$lib/stores/flashcardStore';
  import { newNoteOverlay, newDeckOverlay } from '$lib/stores/overlayStore.svelte';

  // TODO: Mudar isso depois. Colocar para as funções serem realizadas nos componentes que chamam o Intro.svelte

  type Props = {
    title: string;
    subtitle: string;
    firstButtonIcon: string;
    firstButtonText: string;
    secondButtonIcon: string;
    secondButtonText: string;
    buttonClick1: string;
  };

  let { title, subtitle, firstButtonIcon, firstButtonText, secondButtonIcon, secondButtonText, buttonClick1 } = $props();

  function handleClick1() {
    if(buttonClick1 === "home") {
      newNoteOverlay.set(true);
    } else if(buttonClick1 === "flashcards") {
      newDeckOverlay.set(true);
    } else {
      console.warn('buttonClick1 with unexpected value:', buttonClick1);
    }
  }

  function handleClick2() {
    if(buttonClick1 === "home") {
      // newNoteOverlay.set(true);
    } else if(buttonClick1 === "flashcards") {
      allDueFlashcards.set(true);
      goto("/flashcardReview");
    } else {
      console.warn('buttonClick1 with unexpected value:', buttonClick1);
    }
  }
</script>

<h1 class="text-[var(--color4)] text-[1.875rem] font-bold">{title}</h1>
  <p class="text-[var(--color7)] mt-2 mb-8 subtitle">{subtitle}</p>
  <div class="grid grid-cols-2 gap-4 mb-[32px]">
    <button class="flex justify-center items-center gap-2 min-h-[44px] rounded-[calc(0.5rem-2px)] bg-[var(--color1)] transition-colors duration-250 hover:bg-[var(--color6)] hover:cursor-pointer" onclick={handleClick1}>
      <img width="16px" height="16px" src={firstButtonIcon} alt="">
      <p class="text-sm text-[var(--color2)] font-medium">{firstButtonText}</p>
    </button>
    <button class="flex justify-center items-center gap-2 min-h-[44px] rounded-[calc(0.5rem-2px)] bg-white border border-[var(--color1)] transition-colors duration-250 hover:bg-[var(--color9)] hover:cursor-pointer" onclick={handleClick2}>
      <img src={secondButtonIcon} alt="">
      <p class="text-sm text-[var(--color1)] font-medium">{secondButtonText}</p>
    </button>
  </div>
