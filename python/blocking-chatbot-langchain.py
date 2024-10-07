from dotenv import load_dotenv
import os

from langchain_mistralai import ChatMistralAI
from langchain_core.prompts import ChatPromptTemplate

# access the environment variables from the .env file
load_dotenv(dotenv_path='../.env')

model = ChatMistralAI(model="Mistral-7B-Instruct-v0.2", 
                        api_key=os.getenv('OVH_AI_ENDPOINTS_ACCESS_TOKEN'),
                        endpoint='https://mistral-7b-instruct-v02.endpoints.kepler.ai.cloud.ovh.net/api/openai_compat/v1', 
                        max_tokens=512)

prompt = ChatPromptTemplate.from_messages([
  ("system", "You are Nestor, a virtual assistant. Answer to the question."),
  ("human", "{question}"),
])

chain = prompt | model

response = chain.invoke("What is OVHcloud?")

print(f"🤖: {response.content}")