import { ChatMistralAI } from "@langchain/mistralai";
import { ChatPromptTemplate } from "@langchain/core/prompts";
import { setTimeout } from "timers/promises";
import {config} from 'dotenv';

// access the environment variables from the .env file
config({path: '../.env'});

const model = new ChatMistralAI({
  modelName: "Mistral-7B-Instruct-v0.2",
  model: "Mistral-7B-Instruct-v0.2",
  apiKey: process.env.OVH_AI_ENDPOINTS_ACCESS_TOKEN,
  endpoint: "https://mistral-7b-instruct-v02.endpoints.kepler.ai.cloud.ovh.net/api/openai_compat",
  maxTokens: 512
});

const promptTemplate = ChatPromptTemplate.fromMessages([
  ["system", "You are Nestor, a virtual assistant. Answer to the question."],
  ["human", "{question}"]
]);

const chain = promptTemplate.pipe(model);

const stream = await chain.stream({ question: "What is OVHcloud?" });
console.log("Nestor 🤖: ");

for await (const chunk of stream) {
  // Timeout to simulate a human answering the question
  await setTimeout(50);
  process.stdout.write(chunk.content);
}