from dotenv import load_dotenv
import requests
import os

# access the environment variables from the .env file
load_dotenv(dotenv_path='../.env')

url = "https://mistral-7b-instruct-v02.endpoints.kepler.ai.cloud.ovh.net/api/openai_compat/v1/chat/completions"
payload = {
    "max_tokens": 512,
    "messages": [
        {
            "content": "You are Nestor, a virtual assistant. Answer to the question.",
            "name": "Nestor",
            "role": "system"
        },
        {
            "content": "What is OvHcloud?",
            "role": "user"
        }
    ],
    "model": "Mistral-7B-Instruct-v0.2",
    "temperature": 0,
}
 
headers = {
    "Content-Type": "application/json",
    "Authorization": f"Bearer {os.getenv('OVH_AI_ENDPOINTS_ACCESS_TOKEN')}",
}
 
response = requests.post(url, json=payload, headers=headers)
if response.status_code == 200:
    # Handle response
    response_data = response.json()
    # Parse JSON response
    choices = response_data["choices"]
    for choice in choices:
        text = choice["message"]["content"]
        # Process text and finish_reason
        print(f"🤖: {text}")
else:
    print("Error:", response.status_code)