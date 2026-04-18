export default function Sidebar() {
  return (
    <div className="w-64 h-screen bg-gray-900 text-white p-5">
      <h2 className="text-2xl font-bold mb-10">🚀 AI Dashboard</h2>

      <ul className="space-y-4">
        <li className="hover:text-blue-400 cursor-pointer">📊 Dashboard</li>
        <li className="hover:text-blue-400 cursor-pointer">📁 Upload</li>
        <li className="hover:text-blue-400 cursor-pointer">💬 Chat</li>
      </ul>
    </div>
  );
}