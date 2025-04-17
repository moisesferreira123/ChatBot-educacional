<script>
	import HeaderChat from "./HeaderChat.svelte";
	import MessagesChat from "./MessagesChat.svelte";
	import SendMessage from "./SendMessage.svelte";
  export let title;

</script>

<div class="chat-container">
	<HeaderChat title={title} />
	<MessagesChat />
	<SendMessage />
</div>

<style>
	.chat-container {
		display: flex;
		flex-direction: column;
		flex: 1;
	}

</style>