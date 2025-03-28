from dotenv import load_dotenv
import requests
import os

# access the environment variables from the .env file
load_dotenv(dotenv_path='../.env')

# py-02-simple-request
url = f"{os.getenv('OVH_AI_ENDPOINTS_MODEL_URL')}/chat/completions"
payload = {
    "max_tokens": 512,
    "messages": [
        # py-03-simple-sys-msg
        {
            "content": "You are Nestor, a virtual assistant. Answer to the question.",
            "role": "system"
        },
        # py-04-simple-usr-msg
        {
            "content": "What is OvHcloud?",
            "role": "user"
        }
    ],
    "model": os.getenv('OVH_AI_ENDPOINTS_MODEL_NAME'),
    "temperature": 0,
}

# py-05-simple-token
headers = {
    "Content-Type": "application/json",
    "Authorization": f"Bearer {os.getenv('OVH_AI_ENDPOINTS_ACCESS_TOKEN')}",
}

# py-06-simple-call
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
