<script>
	import { toggleOverlay, closeOverlay } from '$lib/stores/overlayStore.svelte';
	import { goto } from '$app/navigation';
	import Overlay from './abstract/Overlay.svelte';
	import { fetchGetUserById } from '$lib/api/users';
	import { userData } from '$lib/stores/userDataStore';
	import { fetchAuthRegister } from '$lib/api/auth';

	const id = 'sign-in';
	const title = 'Get Started Today';
	const subtitle = 'Create your account and start learning smarter';

	let { fullName, email, password } = $state([ '', '', '' ]);

	async function handleSignIn() {
		let token;
		try {
			token = await fetchAuthRegister(fullName, email, password);
		} catch(e) {
			alert(`Error: ${e.message}`);
			return;
		}
		if (token !== '') {
		localStorage.setItem('token', token);
		try {
			const user = await fetchGetUserById(token);
			localStorage.setItem('userData', JSON.stringify(user));
			userData.set({ username: user.username, fullName: user.fullName, email: user.email });
		} catch (e) {
			alert(`Error: ${e.message}`);
		}
		closeOverlay();
		goto('/home');
		}
	}
</script>

{#snippet form()}
	<form action="#" class="form-style" onsubmit={handleSignIn}>
		<label for="full-name"
			>Full Name
			<input
				type="text"
				bind:value={fullName}
				name="full-name"
				placeholder="Enter your name"
				required
			/>
		</label><br />
		<label for="email"
			>Email
			<input type="email" bind:value={email} name="email" placeholder="Enter your email" required />
		</label><br />
		<label for="password"
			>Password
			<input
				type="password"
				bind:value={password}
				name="password"
				placeholder="Create a password"
				required
			/>
		</label><br />
		<button type="submit" class="create-account">Create Account</button>
	</form>
{/snippet}
{#snippet footer()}
	<p>
		Already have an account? <a
			class="sign-in-from-get-started"
			onclick={() => {
				toggleOverlay('login');
			}}>Sign in</a
		>
	</p>
{/snippet}

<Overlay {id} {title} {subtitle} {form} {footer} />

<style>
	.sign-in-from-get-started {
		color: var(--color1);
	}

	.sign-in-from-get-started:hover {
		text-decoration: underline;
		cursor: pointer;
	}

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

	p {
		margin-top: -20px;
		margin-bottom: 30px;
		font-size: small;
		color: var(--color3);
		text-align: center;
		font-weight: 500;
	}
</style>
