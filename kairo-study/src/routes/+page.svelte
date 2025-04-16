<script lang="ts">
	import HamburgerMenu from '$lib/components/HamburgerMenu.svelte';
	import LoginOverlay from '$lib/components/LoginOverlay.svelte';
	import MobileMenu from '$lib/components/MobileMenu.svelte';
	import SignInOverlay from '$lib/components/SignInOverlay.svelte';

	//Images - TODO: Mudar pra webp no lugar de png
	// TODO: Entender como que faz pra importar as imagens usando Vite
	//import icon_kairo from "$app/images/icon-kairo.png"
  import { toggleOverlay } from '$lib/stores/overlayStore.svelte';
	const sign_in = "sign-in";
  const log_in = "login";

</script>

<nav class="navbar">
	<div class="kairo">
		<img width="36px" height="36px" src="/images/icon-kairo.png" alt="Kairo icon" />
		<p>Kairo</p>
	</div>
	<ul>
		<li><a class="features-navbar" href="#features">Features</a></li>
		<li><a class="about-navbar" href="#about">About</a></li>
		<li class="log-in" onclick={toggleOverlay(log_in)}>Log in</li>
		<li>
			<button
				class="get-started click-get-started"
				onclick={() => {toggleOverlay(sign_in)}}>Get Started</button
			>
		</li>
		<HamburgerMenu/>
	</ul>
</nav>
<MobileMenu/>
<main>
  <LoginOverlay />
	<SignInOverlay />
	<section class="presentation">
		<div class="text-presentation">
			<h1 class="color4">Your AI-Powered <br /> Study Companion</h1>
			<p class="color3">
				Ace your exams with personalized AI-quizzes and flashcards, tailored just for you. Transform
				your learning experience with Kairo.
			</p>
			<button
				class="get-started click-get-started"
				onclick={() => {toggleOverlay(sign_in)}}>Get Started</button
			>
		</div>
		<img width="400px" src="/images/Kairo-image.png" alt="Kairo image" />
	</section>
	<section id="features" class="features">
		<h2 class="color4">Powerful Learning Features</h2>
		<p class="color3" style="margin-bottom: 40px;">
			Kairo offers a comprehensive suite of tools designed to transform how you study and learn.
		</p>
		<div class="grid-container">
			<div class="item">
				<img src="/images/icon-AI-powers-summaries.png" alt="summaries icon" />
				<h3>AI-Powered Summaries</h3>
				<p>
					Get help from advanced AI to master any subject with smart summaries of complex topics.
				</p>
			</div>
			<div class="item">
				<img height="67px" src="/images/icon-flashcards.png" alt="flashcards icon" />
				<h3>Flashcards Generation</h3>
				<p>Automatic generation of flashcards by reading texts and PDFs.</p>
			</div>
			<div class="item">
				<img height="67px" src="/images/icon-Q&A.png" alt="Q&A icon" />
				<h3>Q&A Support</h3>
				<p>
					Ask questions and receive clear, concise answers to help you understand difficult
					concepts.
				</p>
			</div>
			<div class="item">
				<img height="67px" src="/images/icon-track-progress.png" alt="track progress icon" />
				<h3>Track Progress</h3>
				<p>Monitor your improvement and achieve your study goals with detailed analytics.</p>
			</div>
			<div class="item">
				<img
					style="height: 67px"
					src="/images/icon-drive-pdf.png"
					alt="Google Drive and PDF integration icon"
				/>
				<h3>Drive and PDF Integration</h3>
				<p>
					Upload study materials directly from Google Drive or PDFs to generate summaries and
					flashcards.
				</p>
			</div>
			<div class="item">
				<img height="67px" src="/images/icon-study-scheduler.png" alt="study scheduler icon" />
				<h3>Study Scheduler</h3>
				<p>Plan your study sessions with smart reminders and optimized scheduling.</p>
			</div>
			<div class="item">
				<img
					height="67px"
					src="/images/icon-personalized-summaries.png"
					alt="personalized summaries icon"
				/>
				<h3>Personalized Summaries</h3>
				<p>Choose your preferred style of summaries: technical, casual, analogies or mind maps.</p>
			</div>
			<div class="item">
				<img height="67px" src="/images/icon-test-simulation.png" alt="test simulation icon" />
				<h3>Test Simulation</h3>
				<p>Test your knowledge with timed mock exams to prepare you for real exams.</p>
			</div>
		</div>
	</section>
	<section id="about" class="about">
		<h2 class="color4">About Kairo</h2>
		<p class="color3" style="margin-bottom: 40px;">
			Transforming education with AI-powered learning assistance
		</p>
		<div class="our-mission">
			<h3 class="color4">Our Mission</h3>
			<p class="color3">
				Kairo was created with a simple but powerful mission: to make learning more effective,
				efficient, and accessible for everyone. We believe that with the right tools, anyone can
				master complex subjects and achieve academic excellence.
			</p>
			<p class="color3" style="margin-bottom: 0;">
				By combining cutting-edge AI technology with proven educational methods, we've built a
				platform that adapts to your unique learning style and helps you overcome academic
				challenges. Whether you're preparing for exams, trying to understand difficult concepts, or
				simply wanting to improve your knowledge, Kairo is your intelligent study companion.
			</p>
		</div>
		<div class="flex-container-about">
			<div class="item-flex">
				<img height="67px" src="/images/icon-smart-ai.png" alt="track progress icon" />
				<h3>Smart AI</h3>
				<p>Powered by advanced algorithms that understand your learning patterns</p>
			</div>
			<div class="item-flex">
				<img height="67px" src="/images/icon-research-beckend.png" alt="track progress icon" />
				<h3>Research-Backed</h3>
				<p>Built on proven educational methodologies for effective learning</p>
			</div>
			<div class="item-flex">
				<img height="67px" src="/images/icon-data-driven.png" alt="track progress icon" />
				<h3>Data-Driven</h3>
				<p>Continuously improving based on student usage and feedback</p>
			</div>
		</div>
	</section>
</main>
<footer><p>@ 2025 Kairo. All rights reserved.</p></footer>

<style>
	

	:global html {
		scroll-behavior: smooth;
	}

	.color3 {
		color: var(--color3);
	}

	.color4 {
		color: var(--color4);
	}

	a {
		text-decoration: none;
		color: inherit;
	}

	main {
		padding-top: 72px;
	}

	:global body {
		font-size: 15px;
		font-family: 'Montserrat', serif;
		font-optical-sizing: auto;
		font-weight: 600px;
		font-style: normal;
	}

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
		gap: 30px;
		margin-right: 30px;
		color: var(--color3);
		font-weight: 500;
	}

	.navbar ul a:hover {
		color: var(--color1);
	}

	.log-in:hover {
		color: var(--color1);
		cursor: pointer;
	}

	.get-started {
		border: none;
		background-color: var(--color1);
		padding: 10px 16px;
		font-size: 14px;
		color: white;
		font-family: 'Montserrat', serif;
		font-weight: 600;
		border-radius: 10px;
	}

	.get-started:hover {
		cursor: pointer;
		background-color: var(--color6);
	}

	.presentation {
		display: flex;
		justify-content: space-evenly;
		align-items: center;
		background-color: var(--color2);
		/* width: 100vw; */
		/*height: 100vh; */
		padding: 20px 20px 80px 20px;
	}

	.text-presentation {
		display: flex;
		flex-direction: column;
		align-items: center;
		text-align: center;
		width: 400px;
		/* margin-left: 25px; */
		font-size: 16px;
	}

	.text-presentation h1 {
		font-size: 40px;
		margin-bottom: 30px;
		font-weight: 700;
	}

	.text-presentation p {
		margin-bottom: 30px;
	}

	.presentation .get-started {
		padding: 18px 20px;
		font-size: 16px;
	}

	.features {
		padding: 80px 20px;
		text-align: center;
	}

	.features h2 {
		font-size: 30px;
		margin-bottom: 20px;
	}

	.grid-container {
		display: grid;
		gap: 1.5rem;
		grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
		padding: 1rem;
	}

	.item {
		display: flex;
		flex-direction: column;
		align-items: center;
		box-shadow: 0px 6px 12px rgba(1, 1, 44, 0.1);
		border-radius: 10px;
		padding: 15px 25px;
	}

	.item p {
		color: var(--color3);
		margin: 10px;
	}

	.item h3 {
		color: var(--color4);
		margin-top: 10px;
	}

	.about {
		display: flex;
		flex-direction: column;
		align-items: center;
		background-color: var(--color2);
		padding: 80px 20px;
		text-align: center;
	}

	.about h2 {
		font-size: 30px;
		margin-bottom: 20px;
	}

	.our-mission {
		margin: 0px 5px 40px 5px;
		max-width: 900px;
		text-align: start;
		background-color: white;
		box-shadow: 0px 6px 12px rgba(1, 1, 44, 0.1);
		border-radius: 10px;
		padding: 32px;
	}

	.our-mission h3 {
		margin-bottom: 24px;
		font-size: 25px;
	}

	.our-mission p {
		margin-bottom: 24px;
	}

	.flex-container-about {
		display: flex;
		flex-direction: row;
		max-width: 900px;
	}

	.item-flex {
		display: flex;
		flex-direction: column;
		align-items: center;
		flex: 1;
		background-color: white;
		margin: 0px 10px;
		box-shadow: 0px 6px 12px rgba(1, 1, 44, 0.1);
		border-radius: 10px;
		padding: 15px 25px;
	}

	.item-flex p {
		color: var(--color3);
		margin: 10px;
	}

	.item-flex h3 {
		color: var(--color4);
		margin-top: 10px;
	}

	footer {
		background-color: var(--color4);
		color: var(--color5);
		text-align: center;
	}

	@media (max-width: 840px) {
		.presentation img {
			width: 300px;
		}

		.presentation {
			padding-top: 50px;
		}
	}

	@media (max-width: 590px) {
		.presentation img {
			display: none;
		}

		.presentation {
			padding-top: 120px;
			align-items: center;
		}

		.flex-container-about {
			flex-direction: column;
		}

		.item-flex {
			margin-bottom: 20px;
		}
	}

	@media (max-width: 545px) {
		.navbar ul {
			gap: 10px;
		}

		.navbar .features-navbar {
			display: none;
		}

		.navbar .about-navbar {
			display: none;
		}

		.navbar .log-in {
			display: none;
		}
	}
</style>
