<script>
	import HeaderChat from "./HeaderChat.svelte";
	import MessagesChat from "./MessagesChat.svelte";
	import SendMessage from "./SendMessage.svelte";
	let title = $props();
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
		border-radius: calc(0.5rem - 2px);
		box-shadow: rgba(0, 0, 0, 0.05) 0px 1px 2px 0px;
	}

</style>