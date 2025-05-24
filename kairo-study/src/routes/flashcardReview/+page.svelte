<script>
	import { fetchApplyReviewResult, fetchGetNextDueFlashcardByDeckId } from "$lib/api/flashcards";
	import { ArrowLeft } from "@lucide/svelte";
  import { flashcardReview } from '$lib/stores/flashcardStore';
	import { onMount } from "svelte";
	import { goto } from "$app/navigation";


  let flipped = false;
  let isTransitioning = false;
  let begin = true;
  let flashcard;
  let flashcardToView;
  let newFlashcard;
  let learningFlashcard;
  let reviewFlashcard;
  let token;

  const Answer = {
    WRONG: 0,
    HARD: 3,
    GOOD: 4,
    EASY: 5
  }

  async function getNextDueFlashcardByDeckId() {
    try {
      flashcard = await fetchGetNextDueFlashcardByDeckId($flashcardReview.id, token);
      // TODO: Mandar menssagem que revisou tudo.
      if(flashcard === null) goto("/flashcards");
      if(!begin) {
        flipCard();
      } else {
        begin = false;
      }
      flashcardToView = {...flashcard};
    } catch(e) {
      alert(e.message);
    }
  }

  async function applyReviewResult(answer) {
    try {
      await fetchApplyReviewResult(flashcard.id, answer, token);
      getNextDueFlashcardByDeckId();
    } catch(e) {
      alert(e.message);
    }
  }

  function flipCard() {
    if (isTransitioning) return; // evitar clique múltiplo
		flipped = !flipped;
		isTransitioning = true;
  }

  function wrongAnswer() {
    applyReviewResult(Answer.WRONG);

  }

  function hardAnswer() {
    applyReviewResult(Answer.HARD);

  }

  function goodAnswer() {
    applyReviewResult(Answer.GOOD);

  }

  function easyAnswer() {
    applyReviewResult(Answer.EASY);

  }

  function handleTransitionEnd() {
		isTransitioning = false;
	}

  onMount(() => {
    token = localStorage.getItem("token");
    getNextDueFlashcardByDeckId();
  });

</script>

{#if flashcardToView}
  <div class="flex flex-col h-[100vh]">
    <div class="bg-white shadow-sm border-b border-b-(--border) p-4">
      <div class="flex justify-between items-center w-full px-[2rem] mx-auto gap-6">
        <div class="flex items-center gap-4">
          <button onclick = {() => goto("/flashcards")} class="flex items-center justify-center gap-2 whitespace-nowrap text-gray-600 font-medium text-sm py-2 px-4 rounded-md h-10 cursor-pointer hover:text-gray-900 hover:bg-(--accent)">
            <ArrowLeft class="mr-2" size={16} />
            Voltar
          </button>
          <div class="h-6 w-[1px] bg-gray-300"></div>
          <div class="text-xl font-semibold text-gray-900">Flashcard Review</div>
        </div>
        <div class="flex gap-2 ">
          <div class="flex items-center rounded-full px-2.5 py-0.5 text-xs font-semibold gap-1 bg-blue-100 text-blue-800">New: 0</div>
          <div class="flex items-center rounded-full px-2.5 py-0.5 text-xs font-semibold gap-1 bg-red-100 text-red-800">Learning: 0 </div>
          <div class="flex items-center rounded-full px-2.5 py-0.5 text-xs font-semibold gap-1 bg-green-100 text-green-800">Review: 0</div>
        </div>
      </div>
    </div>
    <div class="flex flex-1 items-center justify-center flex-1 w-full  mx-auto my-auto px-4 py-6 bg-(--bg-flashcard-review)">
      <div class="perspective-[1000px] mb-8 w-full max-w-2xl">
        <div ontransitionend={handleTransitionEnd} class:rotate-y-180={flipped} class="relative rounded-lg transform-3d transition-transform duration-700 shadow-sm bg-white text-(--color14) border rounded-lg cursor-pointer w-full h-96 border-(--border)">
          <!-- Flashcard front -->
          {#if !flipped && !isTransitioning}
          <div role="button" tabindex="0" onkeydown={(e) => e.key === 'Enter'}  onclick={flipCard} class="inset-0 absolute backface-hidden p-8 border-2 border-blue-200 rounded-lg flex flex-col items-center justify-center w-full h-full">
            <div class="text-center">
              <div class="inline-block px-3 py-1 bg-blue-100 text-blue-800 text-sm font-semibold rounded-full mb-6">Question</div>
              <h2 class="text-2xl font-bold text-gray-900 mb-4 leading-[1.625] max-h-[250px] overflow-hidden overflow-y-auto">{flashcardToView.front}</h2>
              <p class="text-gray-500 text-sm mt-8 font-medium">Click to see the answer</p>
            </div>
          </div>
          {/if}
          <!-- Flashcard back -->
          {#if flipped && !isTransitioning}
          <div class="inset-0 absolute backface-hidden rotate-y-180 cursor-default p-8 border-2 border-green-200 rounded-lg flex flex-col items-center justify-center w-full h-full">
            <div class="flex flex-col flex-1 justify-center">
              <div class="text-center mb-6">
                <div class="inline-block px-3 py-1 bg-green-100 text-green-800 text-sm font-semibold rounded-full mb-6">Answer</div>
                <p class="text-lg text-gray-900 leading-[1.625] overflow-hidden overflow-y-auto max-h-[120px]">{flashcardToView.back}</p>
              </div>
            </div>
            <div class="w-full border-t border-t-(--border) pt-6">
              <p class="text-center text-gray-600 mb-4 text-sm font-medium">How do you rate your knowledge on this flashcard?</p>
              <div class="grid grid-cols-2 md:grid-cols-4 gap-3">
                <button onclick={wrongAnswer} class="inline-flex items-center justify-center text-red-700 border border-red-200 bg-red-50 font-medium text-sm py-2 px-4 rounded-md whitespace-nowrap gap-2 h-10 cursor-pointer">
                  <div class="text-xs md:text-sm">❌ Wrong</div>
                </button>
                <button onclick={hardAnswer} class="inline-flex items-center justify-center text-orange-700 border border-orange-200 bg-orange-50 font-medium text-sm py-2 px-4 rounded-md whitespace-nowrap gap-2 h-10 cursor-pointer">
                  <span class="text-xs md:text-sm">😓 Hard</span>
                </button>
                <button onclick={goodAnswer} class="inline-flex items-center justify-center text-blue-700 border border-blue-200 bg-blue-50 font-medium text-sm py-2 px-4 rounded-md whitespace-nowrap gap-2 h-10 cursor-pointer">
                  <span class="text-xs md:text-sm">👍 Good</span>
                </button>
                <button onclick={easyAnswer} class="inline-flex items-center justify-center text-green-700 border border-green-200 bg-green-50 font-medium text-sm py-2 px-4 rounded-md whitespace-nowrap gap-2 h-10 cursor-pointer">
                  <span class="text-xs md:text-sm">✅ Easy</span>
                </button>
              </div>
            </div>
          </div>
        {/if}
        </div>
      </div>
    </div>
  </div>
{/if}