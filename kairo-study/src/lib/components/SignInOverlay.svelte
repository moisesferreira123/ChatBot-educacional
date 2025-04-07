<script lang="ts">
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
  Already have an account? <a class="sign-in-from-get-started">Sign in</a>
</p>
{/snippet}

<Overlay {id} {title} {subtitle} {form} {footer}/>
