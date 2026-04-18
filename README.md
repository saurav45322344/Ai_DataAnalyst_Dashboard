# Ai_Data_Analyst_Dashboard

📊 AI BI Copilot Dashboard

An AI-powered DataAnalyst Dashboard that allows users to upload CSV data and ask questions in natural language to generate charts, summaries, and insights automatically.

🚀 🚀 Live Concept

👉 Upload CSV → Ask Questions → Get Insights

🧠 Key Features
✅ Natural Language Query (AI Chat)
📊 Automatic Chart Generation (Bar, Line, Pie)
🧾 AI-generated Summary & Insights
📁 CSV Upload & Processing
⚡ Fast Data Access using Caching
🔄 Single API /chat for all operations

🏗️ Tech Stack
🔹 Frontend
React.js
Recharts
Tailwind CSS
🔹 Backend
Spring Boot
Java
REST APIs
🔹 AI Integration
Ollama (Local LLM)
Model: phi3

🔹 Database
MySQL
📂 Project Structure
ai-dashboard/
│
├── backend/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   └── entity/
│
├── frontend/
│   ├── components/
│   ├── pages/
│   └── services/

⚙️ How It Works

1️⃣ User uploads CSV file
2️⃣ Backend parses and stores data
3️⃣ AI understands user query
4️⃣ System decides:

chart 📊
summary 🧠

5️⃣ Response sent to frontend
6️⃣ Chart + summary displayed

🔥 Example Queries
{ "query": "transaction by channel" }
{ "query": "UPI transactions by city" }
{ "query": "customers by country" }
{ "query": "give summary of data" }
📊 Sample Output
Bar chart → transactions by channel
Pie chart → customers by country
Summary → AI-generated insights
🧠 AI Flow
User Query → AI Intent Detection → Column Mapping → Aggregation → Chart/Insight
🔧 Setup Instructions
🔹 Backend (Spring Boot)
