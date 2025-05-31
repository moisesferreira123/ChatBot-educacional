<script>
  let  { title } = $props();
</script>

<section>
  <div class="title">
    {#if title === "Smart Summary"}
      <img src="/images/icons8-brief-gray-24.png" alt="Icon">
    {:else if title === "Automatic Flashcards"}
      <img src="/images/icons8-flashcards-gray-24.png" alt="Icon">
    {:else if title === "Questions and Answers"}
      <img src="/images/icons8-faq-gray-24.png" alt="Icon">
    {:else if title === "Content Suggestions"}
      <img src="/images/icons8-camera-gray-24.png" alt="Icon">
    {:else if title === "Test Simulation"}
      <img src="/images/icons8-exam-gray-24.png" alt="Icon">
    {/if}
    <h2>{title}</h2>
  </div>
  {#if title === "Smart Summary"}
  <div class="dropdown">
    <label for="summaryType">Summary style:</label>
    <select id="summaryType">
      <option value="technical">Technical</option>
      <option value="informal">Informal</option>
      <option value="analogies">Analogies</option>
      <option value="mindMaps">Mind maps</option>
      <option value="topics">Topics</option>
    </select>
  </div>
  {:else if title === "Automatic Flashcards"}
    <button class="uploadFile">
      <img src="/images/icons8-upload-file-16.png" alt="">
      <p>Upload File</p>
    </button>
  {/if}
</section>

<style>
  section {
    width: 100%;
    background-color: var(--white);
    border-bottom: 1px solid var(--color13);
    align-self: flex-start;
    padding: 16px;
    border-top-left-radius: calc(0.5rem - 2px);
    border-top-right-radius: calc(0.5rem - 2px);
  }

  .title {
    display: flex;
    align-items: center;
  }

  h2 {
    color: var(--color4);
    font-size: 1rem;
    font-weight: 600;
    margin-left: 8px;
    line-height: 24px;
  }

  .dropdown {
    margin-top: 8px;
  }

  label {
    color: var(--color7);
    font-size: calc(14/16 * 1rem);
    line-height: 20px;
    margin-right: 8px;
  }

  select {
    padding: 8px 12px;
    border: 1px solid var(--color13);
    font-size: calc(14/16 * 1rem);
    color: var(--color14);
    height: 40px;
    width: 160px;
    border-radius: calc(0.5rem - 2px);
    cursor: pointer;
  }

  .uploadFile {
    display: flex;
    align-items: center;
    color: var(--color14);
    font-size: calc(14/16 * 1rem);
    font-weight: 500;
    line-height: 20px;
    height: 36px;
    padding: 0px 12px;
    margin-top: 8px;
    border: 1px solid var(--color13);
    gap: 1rem;
    border-radius: calc(0.5rem - 2px);
    transition: 125ms;
  }

  .uploadFile:hover {
    background-color: var(--color8);
    transition: 125ms;
    cursor: pointer;
  }  

</style>