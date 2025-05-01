<script lang='ts'>
  import { fullName } from '$lib/stores/userDataStore.svelte';

  // TODO: Colocar isso depois de integrar o banco de dados
  let firstName = $derived($fullName.split(' ')[0]);

  type Props = {
    title: string;
    subtitle: string;
    firstButtonIcon: string;
    firstButtonText: string;
    secondButtonIcon: string;
    secondButtonText: string;
  };

  let { title, subtitle, firstButtonIcon, firstButtonText, secondButtonIcon, secondButtonText } = $props();
</script>

<h1 class="text-[var(--color4)] text-[1.875rem] font-bold">{title}</h1>
  <p class="text-[var(--color7)] mt-2 mb-8 subtitle">{subtitle}</p>
  <div class="grid grid-cols-2 gap-4 mb-[32px]">
    <button class="flex justify-center items-center gap-2 min-h-[44px] rounded-[calc(0.5rem-2px)] bg-[var(--color1)] transition-colors duration-250 hover:bg-[var(--color6)] hover:cursor-pointer">
      <img width="16px" height="16px" src={firstButtonIcon} alt="">
      <p class="text-sm text-[var(--color2)] font-medium">{firstButtonText}</p>
    </button>
    <button class="flex justify-center items-center gap-2 min-h-[44px] rounded-[calc(0.5rem-2px)] bg-white border border-[var(--color1)] transition-colors duration-250 hover:bg-[var(--color9)] hover:cursor-pointer">
      <img src={secondButtonIcon} alt="">
      <p class="text-sm text-[var(--color1)] font-medium">{secondButtonText}</p>
    </button>
  </div>
