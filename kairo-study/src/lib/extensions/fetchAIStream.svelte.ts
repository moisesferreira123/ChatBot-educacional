//TODO: Change this into a Backend API call
import Sysprompt from '$lib/extensions/sysprompt.txt?raw';
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
}