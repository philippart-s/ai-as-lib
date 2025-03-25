import { ChatMistralAI } from "@langchain/mistralai";
import { ChatPromptTemplate } from "@langchain/core/prompts";
import { setTimeout } from "timers/promises";
import {config} from 'dotenv';

// access the environment variables from the .env file
config({path: '../.env'});

// js-02-str-model
const model = new ChatMistralAI({
  modelName: process.env.OVH_AI_ENDPOINTS_MODEL_NAME,
  apiKey: process.env.OVH_AI_ENDPOINTS_ACCESS_TOKEN,
  serverURL: "https://mistral-7b-instruct-v0-3.endpoints.kepler.ai.cloud.ovh.net/api/openai_compat",
  temperature:0,
  maxTokens: 512
});

// js-03-str-prompt
const promptTemplate = ChatPromptTemplate.fromMessages([
  ["system", "You are Nestor, a virtual assistant. Answer to the question."],
  ["human", "{question}"]
]);

// js-04-str-chain
const chain = promptTemplate.pipe(model);

// js-05-str-call
const stream = await chain.stream({ question: "What is OVHcloud?" });
console.log("Nestor 🤖: ");

for await (const chunk of stream) {
  // Timeout to simulate a human answering the question
  await setTimeout(50);
  process.stdout.write(chunk.content);
}