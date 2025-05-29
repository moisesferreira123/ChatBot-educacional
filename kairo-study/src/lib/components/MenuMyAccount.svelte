<script lang='ts'>
  import { User, ChartNoAxesColumn, Settings, LogOut } from '@lucide/svelte';
  import { isMenuMyAccountOpen } from '$lib/stores/menuMyAccountStore';
	import { clickOutside } from '$lib/actions/clickOutside';
	import { goto } from '$app/navigation';

  function goToProfile() {
    isMenuMyAccountOpen.set(false);
    goto('/profile');
  }

  function logOut() {
    localStorage.removeItem('token');
    isMenuMyAccountOpen.set(false);
    goto('/');
  }
</script>

{#if $isMenuMyAccountOpen}
  <div use:clickOutside={() => isMenuMyAccountOpen.set(false)} class="custom-shadow fixed top-[60px] right-[90px] p-[4px] bg-white border border-[var(--color13)]">
    <h3 class="text-sm text-[var(--color14)] font-semibold pt-[6px] pb-[6px] pl-[8px] pr-[8px]">My Account</h3>
    <div class="flex flex-col">
      <button onclick={goToProfile} class="flex pt-[6px] pb-[6px] pl-[8px] pr-[8px] w-full font-medium cursor-pointer rounded transition-all duration-200 hover:bg-[var(--color8)]">
        <User size={16} color="var(--color14)" />
        <p class="ml-[8px] text-[var(--color14)] text-sm">Profile</p>
      </button>
      <button class="flex pt-[6px] pb-[6px] pl-[8px] pr-[8px] w-full font-medium cursor-pointer rounded transition-all duration-200 hover:bg-[var(--color8)]">
        <ChartNoAxesColumn size={16} color="var(--color14)" />
        <p class="ml-[8px] text-[var(--color14)] text-sm">Statistics</p>
      </button>
      <button class="flex mb-[4px] pt-[6px] pb-[6px] pl-[8px] pr-[8px] w-full font-medium cursor-pointer rounded transition-all duration-200 hover:bg-[var(--color8)]">
        <Settings size={16} color="var(--color14)" />
        <p class="ml-[8px] text-[var(--color14)] text-sm">Settings</p>
      </button>
      <span class="border-b border-[var(--color13)]"></span>
      <button onclick={logOut} class="flex mt-[4px] pt-[6px] pb-[6px] pl-[8px] pr-[8px] w-full font-medium cursor-pointer rounded transition-all duration-200 hover:bg-[var(--color8)]">
        <LogOut size={16} color="red" />
        <p class="ml-[8px] text-sm text-red-600 font-medium">Log Out</p>
      </button>
    </div>
  </div>
{/if}

<style>
  .custom-shadow {
    box-shadow: rgba(0, 0, 0, 0) 0px 0px 0px 0px, rgba(0, 0, 0, 0) 0px 0px 0px 0px, rgba(0, 0, 0, 0.1) 0px 4px 6px -1px, rgba(0, 0, 0, 0.1) 0px 2px 4px -2px;
    border-radius: calc(0.5rem - 2px);
  }
</style>