<script lang="ts">
	import { toggleOverlay } from "$lib/stores/overlayStore.svelte";
  import Overlay from "./Overlay.svelte";
  const id = 'sign-in';
  const title = 'Get Started Today';
  const subtitle = 'Create your account and start learning smarter';
</script>

{#snippet form()}
<form action="#" class="form-style" slot="overlay-form">
  <label for="email"
    >Email
    <input type="email" name="email" placeholder="Enter your email" />
  </label><br />
  <label for="password"
    >Password
    <input type="password" name="password" placeholder="Enter your password" />
  </label><br />
  <button type="submit" class="get-started sign-in">Sign in</button>
</form>
{/snippet}
{#snippet footer()}
<p>
  Already have an account? <a class="sign-in-from-get-started" onclick={() => {toggleOverlay('login')}}>Sign in</a>
</p>
{/snippet}

<Overlay {id} {title} {subtitle} {form} {footer}/>

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
	}

	.form-style button {
		width: 100%;
		margin-top: 10px;
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
