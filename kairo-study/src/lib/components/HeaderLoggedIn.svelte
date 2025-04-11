<script>
  // import HamburgerMenu from "./HamburgerMenu.svelte";
</script>

<nav class="navbar">
	<div class="kairo">
		<img width="36px" height="36px" src="/images/icon-kairo.png" alt="Kairo icon" />
		<p>Kairo</p>
	</div>
	<ul>
		<li>
      <a href="/Home">
        <img width="16px" height="16px" src="/images/icons8-home-16.png" alt="Home icon" />
        <p>Home</p>
      </a>
    </li>
		<li>
      <a href="/Chat">
        <img width="16px" height="16px" src="/images/icons8-chat-16.png" alt="Chat icon" />
        <p>Chat</p>
      </a>
    </li>
		<li>
      <a href="/Flashcards">
        <img width="16px" height="16px" src="/images/icons8-flashcards-16.png" alt="Flashcards icon" />
        <p>Flashcards</p>
      </a>
    </li>
		<li>
      <a class="user">
        <img width="16px" height="16px" src="/images/icons8-user-16.png" alt="User icon" />
        <p>User</p>
      </a>
      <!-- Colocar um menu parecido com o do Hanburguer -->
    </li>
		<!-- <HamburgerMenu/> -->
	</ul>
</nav>

<style>
  .navbar {
		position: fixed;
		display: flex;
		justify-content: space-between;
		align-items: center;
		width: 100%;
		height: 72px;
		background-color: rgb(255, 255, 255);
		border-bottom: 1px solid rgb(224, 219, 219);
	}

	.kairo {
		display: flex;
		align-items: center;
	}

	.kairo img {
		margin-left: 25px;
		margin-right: 10px;
	}

	.kairo p {
		font-weight: 700;
		font-size: 18px;
		color: var(--color4);
	}

	.navbar ul {
		list-style-type: none;
		display: flex;
		align-items: center;
		margin-right: 30px;
		color: var(--color4);
	}

	.navbar ul a:hover {
		background-color: var(--background-nav-logged-in);
	}

  li a {
    display: flex;
    flex-direction: row;
    gap: 12px;
    padding: 10px 12px;
    margin-left: 16px;
    border-radius: 5px;
  }

  

  li p {
    line-height: 16px;
    font-size: 15px;
    padding: 0;
    margin: 0;
    font-weight: 500;
  }

  .user:hover {
    cursor: pointer;
  }

</style>