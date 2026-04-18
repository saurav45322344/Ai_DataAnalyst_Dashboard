
import {
  LineChart,
  Line,
  BarChart,
  Bar,
  PieChart,
  Pie,
  Cell,
  XAxis,
  YAxis,
  Tooltip,
  CartesianGrid,
  ResponsiveContainer,
} from "recharts";

const COLORS = ["#00C49F", "#FFBB28", "#FF8042", "#0088FE", "#FF4444"];

export default function ChartSection({ chartData, loading }) {
  if (loading) {
    return (
      <div className="p-5 text-center">
        <p className="text-blue-400 animate-pulse">
          ⏳ Generating chart...
        </p>
      </div>
    );
  }

  if (!chartData || !chartData.data) {
    return (
      <div className="text-center text-gray-400 mt-5">
        No chart data yet 📭
      </div>
    );
  }

  const { type, data, summary } = chartData;
  const limitedData = data.slice(0, 10);

  return (
    <div className="bg-gray-900 text-white p-5 rounded-xl shadow-lg">
      {summary && (
        <div className="bg-gray-800 p-4 rounded mb-4">
          <h3 className="font-semibold mb-2">🧠 AI Insights</h3>
          <p className="whitespace-pre-line">{summary}</p>
        </div>
      )}

      <h3 className="text-lg font-semibold mb-4">
        📊 AI Generated Chart
      </h3>

      <div style={{ width: "100%", height: 400 }}>
        <ResponsiveContainer>
          {type === "line" && (
            <LineChart data={limitedData}>
              <CartesianGrid strokeDasharray="3 3" />
              <XAxis dataKey="x" />
              <YAxis />
              <Tooltip />
              <Line dataKey="y" stroke="#00C49F" />
            </LineChart>
          )}

          {type === "bar" && (
            <BarChart data={limitedData}>
              <CartesianGrid strokeDasharray="3 3" />
              <XAxis dataKey="x" />
              <YAxis />
              <Tooltip />
              <Bar dataKey="y" fill="#0088FE" />
            </BarChart>
          )}

          {type === "pie" && (
            <PieChart>
              <Tooltip />
              <Pie data={limitedData} dataKey="y" nameKey="x">
                {limitedData.map((_, i) => (
                  <Cell key={i} fill={COLORS[i % COLORS.length]} />
                ))}
              </Pie>
            </PieChart>
          )}
        </ResponsiveContainer>
      </div>
    </div>
  );
}