<script lang="ts">
  import { X, FileText, Trash2 } from '@lucide/svelte';
  import { page } from '$app/state';
  import { fetchSources, uploadSource, deleteSource } from '$lib/api/sources';
	import { onMount } from 'svelte';

  // Props to control visibility and handle closing
  let {show = $bindable(), onClose} = $props();

  // State to store the list of sources
  let sources: Source[] = $state([]);
  let loadingSources = $state(true);
  let uploading = $state(false);
  let uploadError: string | null = $state(null);

  // Interface for a Source (should match your backend DTO)
  interface Source {
    id: number;
    fileName: string;
    filePath: string; // Or a download URL if your backend provides one
    createdAt: string;
  }

  // Get the note ID from the page store
  let noteId = $derived(page.params.id);
  let token: string | null;

  async function fetchSourcesForNote() {
    loadingSources = true;
    uploadError = null; // Clear previous errors
    token = localStorage.getItem("token");
    if (!token) {
        // Handle missing token, maybe redirect to login
        console.error("Authentication token not found.");
        loadingSources = false;
        return;
    }
    try {
      sources = await fetchSources(noteId, token);
    } catch (error) {
      console.error("Error fetching sources:", error);
      // Handle error, maybe show a message to the user
      uploadError = "Failed to load sources.";
    } finally {
      loadingSources = false;
    }
  }

  //TODO: Melhorar essa logica para não ter que fazer o fetch toda vez que abrir o overlay
  onMount(() => {
    // Fetch sources when the component mounts
    fetchSourcesForNote();
  });

  // Function to handle file selection and upload
  async function handleFileUpload(event: Event) {
    const input = event.target as HTMLInputElement;
    if (!input.files || input.files.length === 0) {
      return; // No file selected
    }

    const file = input.files[0];
    uploading = true;
    uploadError = null; // Clear previous errors
    token = localStorage.getItem("token");
    if (!token) {
        console.error("Authentication token not found.");
        uploading = false;
        return;
    }

    // Create a FormData object
    const formData = new FormData();
    // Append the file to the FormData object. The key 'file' must match the @RequestParam name in your Spring Boot controller.
    formData.append('file', file);

    try {
      const newSource: Source = await uploadSource(noteId, formData, token);

      // Add the newly uploaded source to the list
      sources = [...sources, newSource];

      // Clear the file input so the same file can be uploaded again if needed
      input.value = '';

    } catch (error: any) {
      console.error("Error uploading file:", error);
       // Handle error, maybe show a message to the user
       uploadError = error.message || "Failed to upload file.";
    } finally {
      uploading = false;
    }
  }

  // Function to handle source deletion
  async function handleDeleteSource(sourceId: number) {
     token = localStorage.getItem("token");
     if (!token) {
         console.error("Authentication token not found.");
         return;
     }
     if (confirm("Are you sure you want to delete this source?")) {
         try {
             await deleteSource(sourceId, token);

             // Remove the source from the local list
             sources = sources.filter(source => source.id !== sourceId);

         } catch (error) {
             console.error("Error deleting source:", error);
             // Handle error
             alert("Failed to delete source.");
         }
     }
  }

  // Helper function to format date
  function formatDate(dateString: string): string {
    const options: Intl.DateTimeFormatOptions = { year: 'numeric', month: 'short', day: 'numeric' };
    return new Date(dateString).toLocaleDateString(undefined, options);
  }
</script>

  <aside class="left-0 top-0 bottom-0 flex-shrink-0 w-80 border-neutral-300 rounded-md bg-white shadow-lg z-50 transform transition-transform duration-300 ease-in-out {show ? 'translate-x-0' : '-translate-x-full'} pt-6">
    <header class="flex justify-between items-center p-4 border-b border-neutral-300">
      <h2 class="text-lg font-semibold text-gray-900">Documents</h2>
      <button class="text-gray-500 hover:text-black" onclick={onClose}>
        <X size={20} />
      </button>
    </header>
    <div class="p-4">
      <div class="flex flex-col items-center justify-between h-64 {sources.length === 0 ? 'border border-dashed border-neutral-300' :''} rounded-md text-gray-500">
        {#if sources.length > 0}
          <h3 class="text-md font-semibold mb-2">Attached Documents:</h3>
          <ul class="w-full">
          {#each sources as source (source.id)}
              <li class="flex items-center justify-between p-2 border my-1 rounded border-neutral-200 text-sm text-gray-800">
                <FileText size={16} class="mr-2 text-blue-500" />
                <div class="flex flex-col justify-between">
                  <strong><a href={source.filePath} target="_blank" rel="noopener noreferrer" class="flex-1 truncate hover:underline">
                    {source.fileName}
                  </a></strong>
                  <span class="text-xs text-gray-500 ml-2 shrink-0">{formatDate(source.createdAt)}</span>
                </div>
                <button class="text-red-500 hover:text-red-700 ml-2 shrink-0" onclick={() => handleDeleteSource(source.id)}>
                    <Trash2 size={16}/>
                </button>
              </li>
          {/each}
          </ul>
        {:else}
          <FileText size = 50 class="pb-2"/>
          <strong>No documents attached</strong>
          <p class="text-sm text-center">Upload documents to get context for your notes</p>
        {/if}
        <label for="file-upload" class="mt-4 bg-[var(--color1)] text-white px-4 py-2 rounded hover:bg-[var(--color1)]/90">
            + Add Documents
          <input id="file-upload" type="file" class="hidden" onchange={handleFileUpload} disabled={uploading} />
        </label>
        </div>
    </div>
  </aside>

<!-- 
{#if show}
  <div class="fixed inset-0 bg-black/50 z-40" onclick={onClose}></div> <aside class="fixed left-0 top-0 bottom-0 w-64 bg-white shadow-lg z-50 transform transition-transform duration-300 ease-in-out {show ? 'translate-x-0' : '-translate-x-full'} pt-20 flex flex-col h-screen">
    <header class="flex justify-between items-center p-4 border-b border-neutral-300 shrink-0">
      <h2 class="text-lg font-semibold text-gray-900">Documents</h2>
      <button class="text-gray-500 hover:text-black" onclick={onClose}>
        <X size={20} />
      </button>
    </header>

    <div class="p-4 flex-1 overflow-y-auto flex flex-col">
      <label for="file-upload" class="flex flex-col items-center justify-center px-4 py-6 border border-dashed border-neutral-300 rounded-md text-gray-500 cursor-pointer hover:bg-neutral-100 transition-colors">
        <FileText size = 50 class="pb-2"/>
        <strong>No documents attached</strong>
        <p class="text-sm text-center">Upload documents to get context for your notes</p>
        <button class="mt-4 bg-[var(--color1)] text-white px-4 py-2 rounded hover:bg-[var(--color1)]/90">
          + Add Documents
        </button>
        <UploadCloud size={30} class="mb-2" />
        <p class="text-sm text-center">Click to upload a file</p>
        <input id="file-upload" type="file" class="hidden" onchange={handleFileUpload} disabled={uploading} />
      </label>

      {#if uploading}
        <p class="text-center text-blue-600 mt-2">Uploading...</p>
      {/if}

      {#if uploadError}
        <p class="text-center text-red-600 mt-2 text-sm">{uploadError}</p>
      {/if}

      <div class="mt-4 flex-1">
        {#if loadingSources}
          <p class="text-center text-gray-500">Loading sources...</p>
        {:else if sources.length === 0}
          <div class="flex flex-col items-center justify-center h-full text-gray-500">
             <p>No documents attached</p>
             <p class="text-sm text-center">Upload documents to get context for your notes</p>
          </div>
        {:else}
          <h3 class="text-md font-semibold mb-2">Attached Documents:</h3>
          <ul>
            {#each sources as source (source.id)}
              <li class="flex items-center justify-between p-2 border-b border-neutral-200 text-sm text-gray-800">
                <a href={source.filePath} target="_blank" rel="noopener noreferrer" class="flex-1 truncate hover:underline">
                  {source.fileName}
                </a>
                <span class="text-xs text-gray-500 ml-2 shrink-0">{formatDate(source.createdAt)}</span>
                <button class="text-red-500 hover:text-red-700 ml-2 shrink-0" onclick={() => handleDeleteSource(source.id)}>
                    Delete
                </button>
              </li>
            {/each}
          </ul>
        {/if}
      </div>
    </div>
  </aside>
{/if}
 -->