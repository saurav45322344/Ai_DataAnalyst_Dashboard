import axios from "axios";

// export default function UploadSection() {
//   const handleUpload = async (e) => {
//     const file = e.target.files[0];

//     const formData = new FormData();
//     formData.append("file", file);

//     try {
//       await axios.post("http://localhost:8080/api/upload", formData);
//       alert("Upload Successful ✅");
//     } catch (err) {
//       console.error(err);
//       alert("Upload Failed ❌");
//     }
//   };

//   return (
//     <div style={{ padding: "20px" }}>
//       <h3>Upload CSV</h3>
//       <input type="file" onChange={handleUpload} />
//     </div>
//   );
// }



export default function UploadSection() {
  const handleUpload = async (e) => {
    const file = e.target.files[0];
    if (!file) return;

    const formData = new FormData();
    formData.append("file", file);

    try {
      await fetch("http://localhost:8080/api/upload", {
        method: "POST",
        body: formData,
      });
      alert("Upload Successful ✅");
    } catch {
      alert("Upload Failed ❌");
    }
  };

//   return (
//     <div>
//       <h3 className="text-lg font-semibold mb-3">📁 Upload CSV</h3>
//       <input type="file" onChange={handleUpload} />
//     </div>
//   );
// }
//addon extra
  return (
    <div className="bg-gray-900 p-4 rounded-xl">
      <h3 className="text-lg font-semibold mb-3">📁 Upload CSV</h3>
      <input type="file" onChange={handleUpload} />
    </div>
  );
}