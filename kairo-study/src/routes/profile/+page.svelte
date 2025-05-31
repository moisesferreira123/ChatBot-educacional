<script>
	import { goto } from "$app/navigation";
	import { fetchDeleteUser, fetchUpdateUserPassword, fetchUpdateUserPersonalInformations } from "$lib/api/users";
	import DeleteWarningOverlay from "$lib/components/DeleteWarningOverlay.svelte";
	import HeaderLoggedIn from "$lib/components/HeaderLoggedIn.svelte";
	import { deleteWarningOverlay } from "$lib/stores/overlayStore.svelte";
	import { userData } from "$lib/stores/userDataStore";
	import { Camera, Lock, Save, Trash2, User } from "@lucide/svelte";
	import { onMount } from "svelte";
  
  let fullName = '';
  let username = '';
  let email = '';

  // TODO: Tentar colocar as coisas de erro no front.
  let updateUserInformationsError = false;
  let updateUserPasswordError = false;

  let currentPassword = '', newPassword = '', confirmNewPassword = '';

  let token;

  async function updateUserPersonalInformations() {
    try {
      const userInformations = await fetchUpdateUserPersonalInformations(fullName, username, email, token); 
      fullName = userInformations.fullName;
      username = userInformations.username;
      email = userInformations.email;
      localStorage.setItem('userData', JSON.stringify(userInformations));
      userData.set({username, fullName, email});
    } catch(e) {
      alert(`Error: ${e.message}`);
    }
  }

  async function updateUserPassword() {
    try {
      await fetchUpdateUserPassword(currentPassword, newPassword, confirmNewPassword, token);
      currentPassword = '', newPassword = '', confirmNewPassword = '';
    } catch(e) {
      alert(`Error: ${e.message}`);
    }
  }

  async function deleteUser() {
    try {
      await fetchDeleteUser(token);
      deleteWarningOverlay.set(null);
      localStorage.clear();
      goto("/");
    } catch(e) {
      alert(`Error: ${e.message}`);
    }
  }

  onMount(() => {
    fullName = $userData.fullName;
    username = $userData.username;
    email = $userData.email;
    token = localStorage.getItem("token");
  });
</script>

{#if $deleteWarningOverlay}
  <DeleteWarningOverlay
    alertTitle = {$deleteWarningOverlay.alertTitle}
    alertMessage = {$deleteWarningOverlay.alertMessage}
    cancelButton = {() => deleteWarningOverlay.set(null)}
    deleteButton = {$deleteWarningOverlay.deleteButton}
  />
{/if}
{#if $userData}
<HeaderLoggedIn />
<main class="p-20 pt-22">
  <div class="max-w-4xl mx-auto space-y-6">
    <div class="flex items-center space-x-4">
      <!-- Colocar possibilidade de imagem -->
      <div class="h-12 w-12 rounded-full flex items-center justify-center text-white text-xl font-bold bg-gradient-to-r from-indigo-500 to-blue-600">
        <User size={24} />
      </div>
      <div>
        <h1 class="text-3xl font-bold text-(--color14)">My Profile</h1>
        <p class="text-gray-500 font-medium">Manage your personal information</p>
      </div>
    </div>
    <div class="profile grid grid-cols-3 gap-3">
      <div class="rounded-lg border border-(--color13) bg-white shadow-sm col-span-1">
        <div class="profile-photo flex flex-col space-y-1.5 p-6">
          <h3 class="text-2xl font-semibold leading-none tracking-tight">Profile Photo</h3>
          <p class="text-sm text-gray-500 font-normal">Update your profile picture</p>
        </div>
        <div class="p-6 pt-0">
          <div class="flex flex-col items-center space-y-4">
            <div class="relative">
              <span class="relative flex shrink-0 overflow-hidden rounded-full h-32 w-32">
                <span class="flex h-full w-full items-center justify-center rounded-full text-2xl text-white bg-gradient-to-r from-indigo-500 to-blue-600">U</span>
              </span>
              <button class="inline-flex items-center justify-center gap-2 whitespace-nowrap text-sm font-medium transition-colors bg-white hover:bg-white/80 absolute bottom-0 right-0 rounded-full h-10 w-10 shadow-lg cursor-pointer">
                <Camera size={16} />
              </button>
            </div>
            <p class="text-center text-sm text-gray-600 font-normal">Click on the camera icon to change your photo</p>
          </div>
        </div>
      </div>
      <div class="personal-information rounded-lg border border-(--color13) bg-white shadow-sm col-span-2">
        <div class="flex flex-col space-y-1.5 p-6">
          <h3 class="text-2xl font-semibold leading-none tracking-tight flex items-center gap-2 text-(--color14)">
            <div class="shrink-0 font-bold"><User size={20} /></div>
            Personal Information
          </h3>
          <p class="text-sm text-gray-500 font-normal">Update your basic information</p>
        </div>
        <div class="p-6 pt-0">
          <form action="" class="space-y-4">
            <div class="space-y-2">
              <label for="full-name" class="text-sm font-semibold leading-none text-(--color14)">Full Name</label>
              <input bind:value={fullName} id="full-name" type="text" class="flex h-10 w-full rounded-md border border-(--color13) outline-none focus:border-(--color14) px-3 py-2 text-base font-medium md:text-sm placeholder:font-normal" placeholder="Enter your full name">
            </div>
            <div class="space-y-2">
              <label for="username" class="text-sm font-semibold leading-none text-(--color14)">Username</label>
              <input bind:value={username} id="username" type="text" class="flex h-10 w-full rounded-md border border-(--color13) outline-none focus:border-(--color14) px-3 py-2 text-base font-medium md:text-sm placeholder:font-normal" placeholder="Enter your username">
            </div>
            <div class="space-y-2">
              <label for="email" class="text-sm font-semibold leading-none text-(--color14)">Email</label>
              <input bind:value={email} id="email" type="email" class="flex h-10 w-full rounded-md border border-(--color13) outline-none focus:border-(--color14) px-3 py-2 text-base font-medium md:text-sm placeholder:font-normal" placeholder="Enter your email">
            </div>
            <button onclick={updateUserPersonalInformations} class="inline-flex items-center justify-center gap-3 whitespace-nowrap rounded-md text-sm font-semibold transition-colors bg-(--color1) text-white hover:bg-(--color1)/85 h-10 px-4 py-2 w-full cursor-pointer mt-2">
              <Save size={16} />
              Save Changes
            </button>
          </form>
        </div>
      </div>
    </div>
    <div class="rounded-lg border border-(--color13) bg-white shadow-sm">
      <div class="flex flex-col space-y-1.5 p-6">
        <h3 class="text-2xl font-semibold leading-none tracking-tight flex items-center gap-2 text-(--color14)">
          <Lock size={20} />
          Change Password
        </h3>
        <p class="text-sm text-gray-500 font-normal">Update your password to keep your account secure</p>
      </div>
      <div class="p-6 pt-0">
        <form action="" class="space-y-4">
          <div class="space-y-2">
            <label for="current-password" class="text-sm font-semibold leading-none text-(--color14)">Current Password</label>
            <input bind:value={currentPassword} id="current-password" type="password" class="flex h-10 w-full rounded-md border border-(--color13) outline-none focus:border-(--color14) px-3 py-2 text-base font-medium md:text-sm placeholder:font-normal" placeholder="Enter your current password">
          </div>
          <div class="space-y-2">
            <label for="new-password" class="text-sm font-semibold leading-none text-(--color14)">New Password</label>
            <input bind:value={newPassword} id="new-password" type="password" class="flex h-10 w-full rounded-md border border-(--color13) outline-none focus:border-(--color14) px-3 py-2 text-base font-medium md:text-sm placeholder:font-normal" placeholder="Enter your new password">
          </div>
          <div class="space-y-2">
            <label for="confirm-new-password" class="text-sm font-semibold leading-none text-(--color14)">Confirm New Password</label>
            <input bind:value={confirmNewPassword} id="confirm-new-password" type="password" class="flex h-10 w-full rounded-md border border-(--color13) outline-none focus:border-(--color14) px-3 py-2 text-base font-medium md:text-sm placeholder:font-normal" placeholder="Confirm your new password">
          </div>
          <button onclick={updateUserPassword} class="inline-flex items-center justify-center gap-3 whitespace-nowrap rounded-md text-sm font-semibold transition-colors bg-(--color1) text-white hover:bg-(--color1)/85 h-10 px-4 py-2 w-full cursor-pointer mt-2">
            <Lock size={16} />
            Update Password
          </button>
        </form>
      </div>
    </div>
    <div class="flex flex-row-reverse ">
      <button 
        onclick={() => deleteWarningOverlay.set({alertTitle: "Are you Sure?", alertMessage: "This action cannot be undone. It will permanently delete the user and all of their notes, decks, and flashcards.", deleteButton: deleteUser})}
        class="inline-flex items-center justify-center gap-3 whitespace-nowrap rounded-md text-sm font-semibold transition-colors bg-red-500 text-white hover:bg-red-500/85 h-10 px-4 py-2 cursor-pointer"
      >
        <Trash2 size={16} />
        Delete Account
      </button>
    </div>
  </div>
</main>
{/if}

<style>
  @media(max-width: 796px) {
    .profile {
      grid-template-columns: repeat(1,minmax(0,1fr));
    }

    .profile-photo {
      grid-column: span 1 / span 1;
    }

    .personal-information {
      grid-column: span 1 / span 1;
    }
  }
</style>