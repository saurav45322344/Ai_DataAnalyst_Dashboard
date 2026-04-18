
import { useState } from "react";
import axios from "axios";
import {
  BarChart, Bar, XAxis, YAxis, Tooltip, CartesianGrid,
  LineChart, Line, PieChart, Pie, Cell, ResponsiveContainer
} from "recharts";

export default function Dashboard() {
  const [query, setQuery] = useState("");
  const [data, setData] = useState([]);
  const [summary, setSummary] = useState("");
  const [type, setType] = useState("bar");
  const [loading, setLoading] = useState(false);

  const API = "http://localhost:8080/api";

  const handleUpload = async (e) => {
    const file = e.target.files[0];
    if (!file) return;

    const formData = new FormData();
    formData.append("file", file);

    try {
      //await axios.post(`${API}/upload`, formData);
await axios.post("http://localhost:8080/api/upload", formData);
      alert("File uploaded ✅");

      handleAutoAsk();

    } catch (err) {
      console.error(err);
      alert("Upload failed ❌");
    }
  };

  
  const handleAutoAsk = async () => {
    try {
      setLoading(true);

      const res = await axios.post(`${API}/ai/chart`, {
      //const res = await axios.post("http://localhost:8080/api/ai/chart", {
        query: "count by first column"
      });

      setData(res.data.data || []);
      setSummary(res.data.summary || "");
      setType(res.data.type || "bar");

    } catch (err) {
      console.error(err);
      alert("Error after upload");
    } finally {
      setLoading(false);
    }
  };

 
  const handleAsk = async () => {
    if (!query) return;

    try {
      setLoading(true);

      const res = await axios.post("http://localhost:8080/api/ai/chart", { query });

      console.log("API:", res.data);

      setData(res.data.data || []);
      setSummary(res.data.summary || "");
      setType(res.data.type || "bar");

    } catch (err) {
      console.error(err);
      alert("Error fetching data");
    } finally {
      setLoading(false);
    }
  };

  
  const renderChart = () => {
    if (!data || data.length === 0)
      return <p className="text-gray-400">No data</p>;

    if (type === "line") {
      return (
        <ResponsiveContainer width="100%" height={300}>
          <LineChart data={data}>
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="x" />
            <YAxis />
            <Tooltip />
            <Line dataKey="y" stroke="#38bdf8" />
          </LineChart>
        </ResponsiveContainer>
      );
    }

    if (type === "pie") {
      return (
        <ResponsiveContainer width="100%" height={300}>
          <PieChart>
            <Pie data={data} dataKey="y" nameKey="x">
              {data.map((_, i) => (
                <Cell key={i} fill={`hsl(${i * 40},70%,60%)`} />
              ))}
            </Pie>
            <Tooltip />
          </PieChart>
        </ResponsiveContainer>
      );
    }

    return (
      <ResponsiveContainer width="100%" height={300}>
        <BarChart data={data}>
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="x" />
          <YAxis />
          <Tooltip />
          <Bar dataKey="y" fill="#6366f1" />
        </BarChart>
      </ResponsiveContainer>
    );
  };

  return (
    <div className="flex min-h-screen bg-gradient-to-br from-slate-900 to-slate-800 text-white">
      
      {/* Sidebar */}
      <div className="w-64 bg-slate-950 p-6 shadow-xl">
        <h2 className="text-xl font-bold mb-6">⚡ AI Dashboard</h2>
        <nav className="space-y-3 text-gray-400">
          <p className="hover:text-white cursor-pointer">Dashboard</p>
          <p className="hover:text-white cursor-pointer">Analytics</p>
          <p className="hover:text-white cursor-pointer">Reports</p>
        </nav>
      </div>

      {/* Main */}
      <div className="flex-1 p-8">
        <h1 className="text-2xl font-semibold mb-6">📊 AI Analytics</h1>

        {/* 🔥 UPLOAD SECTION */}
        <div className="mb-6 bg-slate-900 p-4 rounded-xl">
          <h3 className="mb-2 font-semibold">📁 Upload CSV</h3>
          <input type="file" onChange={handleUpload} />
        </div>

        {/* INPUT */}
        <div className="flex gap-3 mb-6">
          <input
            value={query}
            onChange={(e) => setQuery(e.target.value)}
            placeholder="Ask your data..."
            className="flex-1 px-4 py-2 rounded-xl bg-slate-700"
          />
          <button
            onClick={handleAsk}
            className="px-6 py-2 bg-indigo-600 rounded-xl"
          >
            Ask AI
          </button>
        </div>

        {/* LOADING */}
        {loading && <p className="text-indigo-400">Thinking...</p>}

        {/* CHART */}
        <div className="bg-slate-900 p-6 rounded-2xl shadow-lg">
          {renderChart()}
        </div>

        {/* SUMMARY */}
        {summary && (
          <div className="mt-6 bg-slate-900 p-6 rounded-2xl shadow-lg">
            <h3 className="text-lg font-semibold mb-2">🧠 AI Insights</h3>
            <p className="text-gray-300">{summary}</p>
          </div>
        )}
      </div>
    </div>
  );
}
