<script lang="ts">
  import Overlay from "./Overlay.svelte";
	import { goto } from "$app/navigation";
	import { closeOverlay } from '$lib/stores/overlayStore.svelte';

	let email: string, password: string;

	async function handleLogin() {
		const response = await fetch('http://localhost:8080/auth/login', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({
        email: email,
        password: password
      })
		});

		email = '';
		password = '';

		if (response.ok) {
      const data = await response.json();
      // Armazena o token no localStorage ou em um store
      localStorage.setItem('token', data.token);
			closeOverlay();
      goto('/home');
    } else {
      alert('Invalid email or password');
    }
	}
	

  const id = 'login';
  const title = 'Log in to your account';
  const subtitle = 'Continue where you left off in your learning';
</script>

{#snippet form()}
<form action="#" class="form-style">
  <label for="email">Email
    <input type="email" name="email" bind:value={email} placeholder="Enter your email" required>
  </label><br>
  <label for="password">Password
    <input type="password" name="password" bind:value={password} placeholder="Enter your password" required>
  </label><br>
  <button type="submit" class="sign-in" on:click={handleLogin}>Sign in</button>
</form>
{/snippet}

<Overlay {id} {title} {subtitle} {form} />

<style>
  .form-style {
		margin: 30px 30px;
	}

	.form-style label {
		color: var(--color3);
		font-weight: 500;
	}

	.form-style input {
		width: 100%;
		height: 40px;
		font-size: 16px;
		margin-bottom: 15px;
		padding-left: 10px;
		border: 1px solid var(--color5);
	}

	.form-style button {
		width: 100%;
		margin-top: 10px;
		border: none;
		background-color: var(--color1);
		padding: 10px 16px;
		font-size: 14px;
		color: white;
		font-family: 'Montserrat', serif;
		font-weight: 600;
		border-radius: 10px;
	}

	.form-style button:hover {
		cursor: pointer;
		background-color: var(--color6);
	}

</style>