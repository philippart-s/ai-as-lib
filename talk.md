## 🎙️ How to give this talk 🎙️

## 🛠️ Prerequisites 🛠️

- 🔐 Valid OVHcloud AI Endpoints token
- 📝 set th token in a `.env` file create by copying the `.env.example` file
- ⬇️ be sure that all Python, JavaScript and Java dependencies are installed see [README](./README.md) for mote details.
  - activate the Python virtuel environment: `source .venv/bin/activate`

## 🐍 Python demos 🐍

- 📂 go to [python](./python) folder
- 🛠️ source the environment: `source ../.env`

### 🛑 Blocking chatbot with no Framework 🛑

- ⬇️ In the [requirements.txt](./python/requirements.txt) file, declare the dependencies: `py-01-simple-requirements`
- 📁 open the [blocking-chatbot-without-fwk](./python/blocking-chatbot-without-fwk.py)
- 📝 use the snippets as written in the comments
- ⚡️ run the script: `cd python` && `python blocking-chatbot-without-fwk.py`

### 🛑 Blocking chatbot with LangChain 🛑

- ⬇️ In the [requirements.txt](./python/requirements.txt) file, declare the dependencies: `py-07-langchain-blk-requirements`
- 📁 open the [blocking-chatbot-langchain](./python/blocking-chatbot-langchain.py)
- 📝 use the snippets as written in the comments
- ⚡️ run the script: `cd python` && `python blocking-chatbot-langchain.py`

## 🕸️ JavaScript demos 🕸️

### 💬 Streaming chatbot with LangChain 💬

- ⬇️ In the [package.json](./javascript/package.json) file, declare the dependencies: `js-01-str-dependencies`
- 📁 open the [chatbot-streaming](./javascript/chatbot-streaming.js)
- 📝 use the snippets as written in the comments
- ⚡️ run the script: `cd javascript` && `node chatbot-streaming.js`

## ☕️ Java demos ☕️

### 🧠 Memory chatbot with LangChain4j 🧠

- ⬇️ In the [pom.xml](./java/pom.xml) file, declare the dependencies: `java-01-mem-dependencies`
- 📁 open the [MemoryStreamingChatbot](./java/src/main/java/com/ovhcloud/examples/aiendpoints/MemoryStreamingChatbot.java)
- 📝 use the snippets as written in the comments
- ⚡️ run the main class with VSCode debug view and `DEBUG CONSOLE` view
- 🫣 comment the line 37
- ⚡️ run the main class with VSCode debug view and `DEBUG CONSOLE` view

### 🗃️ RAG with LangChain4j 🗃️

- ⬇️ In the [pom.xml](./java/pom.xml) file, declare the dependencies: `java-07-rag-dependency`
- 📁 open the [RAGStreamingChatbot](./java/src/main/java/com/ovhcloud/examples/aiendpoints/RAGStreamingChatbot.java)
- 📝 use the snippets as written in the comments
- ⚡️ run the main class with VSCode debug view and `DEBUG CONSOLE` view
- 🫣 comment the line 76
- ⚡️ run the main class with VSCode debug view and `DEBUG CONSOLE` view

### ❤️ Sentiment analysis ❤️

- ⬇️ In the [pom.xml](./java/pom.xml) file, declare the dependencies: `java-14-sentiment-dependency`
- 📁 open the [SentimentsAnalysis](./java/src/main/java/fr/wilda/ai/nlp/SentimentsAnalysis.java)
- 📝 use the snippets as written in the comments
- ⚡️ run the main class with VSCode debug view and `DEBUG CONSOLE` view

## ⚡️ Quarkus demos ☕️

### 🤖 Virtual assistant 🤖

- ✨ create an empty project in the `tmp` folder: `quarkus create app fr.wilda.ai:ai-as-lib --extension='quarkus-langchain4j-mistral-ai,rest,quarkus-websockets-next' --no-wrapper`
- 📁 open the [application.properties](./java-quarkus/src/main/resources/application.properties) file
- 📝 use the snippets `quarkus-01-ai-langchain4j` 
- 📁 open the [AIEndpointService](./java-quarkus/src/main/java/fr/wilda/ai/service/AIEndpointService.java) interface 
- 📝 use the snippets as written in the comments
- 📁 open the [Jarvis](./java-quarkus/src/main/java/fr/wilda/ai/Jarvis.java) interface
- 📝 use the snippets as written in the comments
- 📁 open the [application.properties](./java-quarkus/src/main/resources/application.properties) file
- 📝 use the snippets `quarkus-07-ws-cors` 
- ⚡️ run the web server: `cd react-client && npm start`

