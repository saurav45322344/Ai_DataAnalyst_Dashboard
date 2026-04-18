
import { useState } from "react";
import axios from "axios";

//const API = import.meta.env.VITE_API_URL;

export default function ChatSection({ setChartData, setLoading }) {
  const [query, setQuery] = useState("");

  const handleAsk = async () => {
    if (!query) return;

    try {
      setLoading(true);

      const res = await axios.post("http://localhost:8080/api/ai/chart", {
        query,
      });

      console.log("API RESPONSE:", res.data);

      setChartData(res.data);
    } catch (err) {
      console.error(err);
      alert("Error: " + err.message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="bg-gray-900 p-4 rounded-xl">
      <h3 className="text-lg font-semibold mb-3">💬 Ask AI</h3>

      <div className="flex gap-3">
        <input
          value={query}
          onChange={(e) => setQuery(e.target.value)}
          placeholder="Try: count by country"
          className="border p-2 flex-1 rounded text-black"
        />

        <button
          onClick={handleAsk}
          className="bg-blue-600 px-4 rounded"
        >
          Ask
        </button>
      </div>
    </div>
  );
}