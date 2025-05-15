import Sysprompt from '$lib/extensions/sysprompt.txt?raw';

export async function fetchAIStream(userPrompt: string, onChunk: (chunk: string) => void, token: string) {
  try {
    const apiEndpoint = 'http://localhost:8080/api/ai/complete'; // Use relative path if frontend and backend are served from the same origin

    // Prepare the request body
    const requestBody = {
      systemPrompt: Sysprompt,
      userPrompt: userPrompt
    };

    console.log("Token:", token);

    // Make the POST request to the backend
    const response = await fetch(apiEndpoint, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
        // Add Accept header for SSE
        // 'Accept': 'text/event-stream' // While good practice, fetch often handles this for streams
      },
      body: JSON.stringify(requestBody)
    });

    // Check for HTTP errors
    if (!response.ok) {
      const errorText = await response.text();
      throw new Error(`HTTP error! status: ${response.status}, body: ${errorText}`);
    }

    // Ensure the response body is a readable stream
    if (!response.body) {
      throw new Error('Response body is not a readable stream.');
    }

    
    onChunk(await response.text());
    let buffer = ''; // Buffer to hold incomplete lines
    
      /* TODO: Descobrir como receber o Flux<String> como um ssse no client side
       // Use a TextDecoderStream to decode the byte chunks into strings
         const reader = response.body 
        .pipeThrough(new TextDecoderStream())
        .getReader();

    // Read the stream chunk by chunk
    while (true) {
      const { value, done } = await reader.read();

      if (done) {
        // Process any remaining data in the buffer
        if (buffer.length > 0) {
            // Depending on your SSE format, you might need to handle the last buffer content.
            // For simple data: messages ending with \n\n, this might not be a full event.
            // If your last event is guaranteed to end correctly, process buffer.
            // Otherwise, you might just ignore it or handle it as a potential partial event.
            // For basic text chunks, you might just yield the remaining buffer:
            // if (buffer.trim().length > 0) {
            //   onChunk(buffer);
            // }
             console.log('Stream finished.'); // Or handle end of stream
        }
        break; // Stream is finished
      }

      // Append the new chunk to the buffer
      buffer += value;

      // Process the buffer line by line, looking for SSE events
      // SSE events are terminated by a double newline (\n\n)
      const events = buffer.split('\n\n');
      buffer = events.pop() || ''; // The last element is either an incomplete event or an empty string after the last \n\n

      for (const event of events) {
        // Each 'event' string here is a potential SSE event block
        // It might contain 'event:', 'data:', 'id:', 'retry:' lines
        // You need to parse this block to extract the 'data'
        const lines = event.split('\n');
        let data = '';
        let eventType = 'message'; // Default SSE event type

        for (const line of lines) {
          if (line.startsWith('data: ')) {
            data += line.substring('data: '.length) + '\n';
          } else if (line.startsWith('event: ')) {
            eventType = line.substring('event: '.length);
          }
          // You can also handle 'id:' and 'retry:' if needed
        }

        // Remove the trailing newline from the data
        if (data.endsWith('\n')) {
          data = data.slice(0, -1);
        }

        // If there is data, call the onChunk callback
        if (data.length > 0) {
          // You might want to pass the eventType as well if your onChunk handler can use it
          onChunk(data);
        }
      }
    } */

  } catch (error) {
    console.error('Error fetching AI completion from backend:', error);
    // You might want to call onChunk with an error indicator or throw
    throw error;
  }
}

/* 
import OpenAI from 'openai';

const API_KEY = import.meta.env.VITE_DEEPSEEK_API_KEY;


const client = new OpenAI({
  baseURL: 'https://api.deepseek.com/v1',
  apiKey: API_KEY,
  dangerouslyAllowBrowser: true,
});

export async function fetchAIStream(prompt : string, onChunk : (chunk: string) => void) {
  try {
    prompt = Sysprompt.replace(/{{prompt}}/g, prompt);
    console.log('Prompt:', prompt);

    const stream = await client.chat.completions.create({
      model: 'deepseek-chat',
      temperature: 0.7,
      stream: true,
      messages: [{ role: 'user', content: prompt }],
    });

    for await (const chunk of stream) {
      const content = chunk.choices[0]?.delta?.content || '';
      if (content) onChunk(content);
    }
  } catch (error) {
    console.error('Stream failed:', error);
    throw error;
  }
} */