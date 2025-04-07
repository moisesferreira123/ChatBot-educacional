<script lang="ts">
  import Overlay from "./Overlay.svelte";
  const id = 'login';
  const title = 'Log in to your account';
  const subtitle = 'Continue where you left off in your learning';
</script>

{#snippet form()}
<form action="#" class="form-style">
  <label for="email">Email
    <input type="email" name="email" placeholder="Enter your email">
  </label><br>
  <label for="password">Password
    <input type="password" name="password" placeholder="Enter your password">
  </label><br>
  <button type="submit" class="get-started sign-in">Sign in</button>
</form>
{/snippet}

<Overlay {id} {title} {subtitle} {form} />
