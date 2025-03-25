from dotenv import load_dotenv
import os

from langchain_mistralai import ChatMistralAI
from langchain_core.prompts import ChatPromptTemplate

# access the environment variables from the .env file
load_dotenv(dotenv_path='../.env')

# py-08-langchain-blk-model
model = ChatMistralAI(model=os.getenv('OVH_AI_ENDPOINTS_MODEL_NAME'), 
                        api_key=os.getenv('OVH_AI_ENDPOINTS_ACCESS_TOKEN'),
                        endpoint=os.getenv('OVH_AI_ENDPOINTS_MODEL_URL'), 
                        temperature=0,
                        max_tokens=512)

# py-09-langchain-blk-prompt
prompt = ChatPromptTemplate.from_messages([
  ("system", "You are Nestor, a virtual assistant. Answer to the question."),
  ("human", "{question}"),
])

# py-10-langchain-blk-chain
chain = prompt | model

# py-11-langchain-blk-call
response = chain.invoke("What is OVHcloud?")

print(f"🤖: {response.content}")