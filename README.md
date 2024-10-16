# 🔎 Why and What? 🔍

This repository contains the source code to discover how to use [OVHloud AI Endpoints](https://endpoints.ai.cloud.ovh.net/) to use AI as a library.  
The repository have a main branch with the all coded examples and other branches with the step by step of the tutorials.  
The special branch `live-coding` is used to do live coding sessions.

At any moment you can switch to another branch to go to a specific step of the tutorial (or merge the desired branch into the `live-coding branch` branch).

# ⚙️ Setup ⚙️

A `.env` file is needed in order to run the examples. You can copy the `.env.example` file and rename it as `.env`.

### 📝 Snippets 📝

The repository [.vscode](./.vscode) contains VSCode snippets to help you with the code.
One file by language: 
  - `python.code-snippets` for Python

Each of these files is created using the tools [snippets](https://github.com/bots-garden/snippets).
Here is an example of usage: 
```bash
snippets generate \
  --input ./.vscode/python-snippets.yml \
  --output ./.vscode/python.code-snippets
```

## 🗃️ Repository organisation 🗃️

### 🖼️ Slides 🖼️

The slides are in the [slides](./slides/) folder and are developed with [SliDesk](https://slidesk.github.io/slidesk-doc/docs/intro/).

### 🐍 Python 🐍

Python source files are in the [python](./python/) folder.

Create a virtual environment and install the dependencies:
```bash
cd python \
python3 -m venv .venv \
source .venv/bin/activate \
pip install -r requirements.txt \
```

### 🕸️ JavaScript 🕸️

JavaScript source files are in the [javascript](./javascript/) folder.

```bash
cd javascript \
npm install
```

### ☕️ Java ☕️

Java source files are in the [java](./java/) folder.

Java source files are in the [java](./java/) folder.

```bash
cd java \
mvn clean compile
```

### ☕️ Quarkus ⚡️️

Quarkus source files are in the [java-quarkus](./java-quarkus/) folder.

```bash
cd java-quarkus \
quarkus create app fr.wilda.ai:ai-as-lib --extension='quarkus-langchain4j-mistral-ai,rest' --no-wrapper \
```

#### 🤖 React client 🤖

Web client, written in React, is in the [react-client](./react-client/) folder.

```bash
cd react-client \
npm install \
npm start
```

Open [http://localhost:3000](http://localhost:3000) to see the client.