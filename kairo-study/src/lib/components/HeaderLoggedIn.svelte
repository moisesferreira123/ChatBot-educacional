<script>
	// import HamburgerMenu from "./HamburgerMenu.svelte";
	// TODO: Colocar o primeiro nome do usuário para aparecer na navbar.
  import { fullName } from '$lib/stores/userDataStore.svelte';
  //Icons from Lucide
  import { House, MessageSquare, BookOpen ,FileText, User } from '@lucide/svelte';

  $: firstName = $fullName.split(' ')[0];

  const navItems= [
    {
      icon: House,
      text: "Home",
      href: "/home"
    },
    {
      icon: MessageSquare,
      text: "Chat",
      href: "/chat"
    },
    {
      icon: BookOpen,
      text: "Home",
      href: "/flashcards"
    },
    {
      icon: FileText,
      text: "Notes",
      href: "/notes"
    },
    {
      icon: User,
      text: "User",
      href: "/home"
    }
  ];
</script>


<nav class="fixed flex justify-between !px-20 items-center w-full h-20 bg-white border-b border-gray-200">
  <div class="flex items-center">
    <img class="!ml-[25px] !mr-[10px]" width="36px" height="36px" src="/images/icon-kairo.png" alt="Kairo icon" />
    <p class="font-bold text-[18px] text-[var(--color4)]">Kairo</p>
  </div>
  <ul class="flex items-center mr-[30px] text-[var(--color4)] list-none">
    {#snippet navIcon({icon,text,href})}
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