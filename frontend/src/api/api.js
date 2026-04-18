
const BASE_URL = "http://localhost:8080/api";


export const uploadFile = async (file) => {
  const formData = new FormData();
  formData.append("file", file);

  const res = await fetch(`${BASE_URL}/upload`, {
    method: "POST",
    body: formData,
  });

  if (!res.ok) throw new Error("Upload failed");
  return res.json();
};


export const sendMessage = async (query) => {
  const res = await fetch(`${BASE_URL}/ai/chat`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ query }),
  });

  if (!res.ok) throw new Error("Chat API failed");
  return res.json();
};


export const getChartData = async (query) => {
  const res = await fetch(`${BASE_URL}/ai/chart`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ query }),
  });

  if (!res.ok) throw new Error("Chart API failed");
  return res.json();
};