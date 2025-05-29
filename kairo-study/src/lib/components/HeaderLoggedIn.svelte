<script>
	// import HamburgerMenu from "./HamburgerMenu.svelte";
  //Icons from Lucide
  import { House, BookOpen, User } from '@lucide/svelte';
  import { isMenuMyAccountOpen } from '$lib/stores/menuMyAccountStore';
  import MenuMyAccount from './MenuMyAccount.svelte';
	import { userData } from '$lib/stores/userDataStore';

  let firstName = 'User'; // Valor padrão

  $: firstName = $userData.username;

  function onClick() {
    isMenuMyAccountOpen.update(open => !open);
  }

  const navItems= [
    {
      icon: House,
      text: "Home",
      href: "/home"
    },
    {
      icon: BookOpen,
      text: "Flashcards",
      href: "/flashcards"
    }
  ];
</script>

<!-- TODO: arrumar os overflows do editor e da pagina -->
<nav class="fixed flex justify-between !px-20 items-center z-10  w-full h-17 bg-white border-b border-gray-200">
  <div class="flex items-center">
    <img class="!ml-[25px] !mr-[10px]" width="36px" height="36px" src="/images/icon-kairo-removebg-preview.webp" alt="Kairo icon" />
    <p class="font-bold text-[18px] text-[var(--color4)]">Kairo</p>
  </div>
  <ul class="flex items-center mr-[30px] text-[var(--color4)] list-none">
    {#snippet navIcon({icon,text,href=null})}
    <li>
      <a href={ href } class="flex flex-row gap-3 px-3 py-2.5 ml-4 rounded transition-all duration-200 hover:bg-[var(--color8)]">
        <svelte:component this={icon} size={16} />      
        <p class="leading-4 text-sm p-0 m-0 font-medium"> { text } </p>
      </a>
    </li>
    {/snippet}
    {#each navItems as Item}
      {@render navIcon(Item)}      
    {/each}
    <li>
      <button onclick={ onClick } data-user-button class="flex flex-row gap-3 px-3 py-2.5 ml-4 rounded transition-all duration-200 hover:bg-[var(--color8)] cursor-pointer">
        <User size={16} />      
        <p class="leading-4 text-sm p-0 m-0 font-medium"> { firstName } </p>
      </button>
      <MenuMyAccount />
    </li>
  </ul>
</nav>

<style>
  /* Nao era pra precisar disso mas tou esquecendo alguma coisa no tailwind que se tirar isso n funciona */
  li a {
    display: flex;
    flex-direction: row;
    gap: 12px;
    padding: 10px 12px;
    margin-left: 16px;
    border-radius: 5px;
		transition: 125ms;
  }
</style>