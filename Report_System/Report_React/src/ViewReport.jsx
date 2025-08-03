import axios from "axios";
import { useState } from "react";

function ViewReport(){
      const [date, setDate] = useState("");
  const [reports, setReports] = useState([]);

  const fetchReports = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8081/reports/seereport/${date}`,{
            headers:{
                Authorization:`Bearer ${localStorage.getItem("token")}`
            }
        }
      );
      setReports(response.data);
    } catch (error) {
      console.error("Error fetching reports:", error);
    }
  };

  return (
 <div style={{ padding: "40px", fontFamily: "Inter, sans-serif", backgroundColor: "#f5f7fa", minHeight: "100vh" }}>
      <div style={{ maxWidth: "900px", margin: "0 auto" }}>
        <h1 style={{ fontSize: "30px", fontWeight: 700, color: "#2d2f33", marginBottom: "20px" }}>
          🗂️ REPORT VIEWER
        </h1>

       
        <div style={{ display: "flex", alignItems: "center", gap: "12px", marginBottom: "30px" }}>
          <input
            type="date"
            value={date}
            onChange={(e) => setDate(e.target.value)}
            style={{
              padding: "12px",
              border: "1px solid #ccc",
              borderRadius: "8px",
              fontSize: "16px",
              backgroundColor: "#fff",
              flex: "1",
              boxShadow: "0 1px 3px rgba(0,0,0,0.05)",
            }}
          />
          <button
            onClick={fetchReports}
            style={{
              padding: "12px 24px",
              backgroundColor: "#4f46e5",
              color: "white",
              border: "none",
              borderRadius: "8px",
              fontSize: "16px",
              cursor: "pointer",
              boxShadow: "0 2px 6px rgba(0, 0, 0, 0.2)",
            }}
          >
            🔍 View Reports
          </button>
        </div>

        
        {reports.length === 0 ? (
          <p style={{ color: "#888", fontSize: "17px" }}>No reports found for the selected date.</p>
        ) : (
          reports.map((report) => {
            const profileUrl = `http://localhost:8081/${report.photo.replaceAll("\\", "/")}`;
            const evidenceUrl = `http://localhost:8081/${report.evidence.replaceAll("\\", "/")}`;

            return (
              <div
                key={report.problem_id}
                style={{
                  backgroundColor: "#ffffff",
                  borderRadius: "12px",
                  padding: "20px",
                  marginBottom: "25px",
                  boxShadow: "0 4px 12px rgba(0,0,0,0.07)",
                  transition: "transform 0.2s ease",
                }}
              >
                <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center" }}>
                  <h3 style={{ margin: "0", fontSize: "20px", fontWeight: "600", color: "#222" }}>
                    👤 {report.username}
                  </h3>
                  <span
                    style={{
                      backgroundColor: "#e0e7ff",
                      color: "#3730a3",
                      padding: "5px 10px",
                      borderRadius: "20px",
                      fontSize: "14px",
                    }}
                  >
                    {report.date}
                  </span>
                </div>

                <p style={{ marginTop: "12px", color: "#444", fontSize: "16px" }}>
                  📝 <strong>Description:</strong> {report.description}
                </p>

                {/* Image Grid */}
                <div
                  style={{
                    display: "flex",
                    gap: "20px",
                    marginTop: "15px",
                    flexWrap: "wrap",
                  }}
                >
                  {/* Profile */}
                  <div style={{ flex: "1" }}>
                    <p style={{ fontWeight: 500, color: "#333", marginBottom: "6px" }}>Profile Photo</p>
                    <img
                      src={profileUrl}
                      alt="Profile"
                      style={{
                        width: "100%",
                        maxWidth: "180px",
                        borderRadius: "10px",
                        objectFit: "cover",
                        border: "1px solid #ddd",
                        transition: "transform 0.3s",
                        cursor: "pointer",
                      }}
                      onClick={() => window.open(profileUrl, "_blank")}
                      onMouseOver={(e) => (e.currentTarget.style.transform = "scale(1.03)")}
                      onMouseOut={(e) => (e.currentTarget.style.transform = "scale(1)")}
                    />
                  </div>

                  {/* Evidence */}
                  <div style={{ flex: "1" }}>
                    <p style={{ fontWeight: 500, color: "#333", marginBottom: "6px" }}>Evidence Image</p>
                    <img
                      src={evidenceUrl}
                      alt="Evidence"
                      style={{
                        width: "100%",
                        maxWidth: "180px",
                        borderRadius: "10px",
                        objectFit: "cover",
                        border: "1px solid #ddd",
                        transition: "transform 0.3s",
                        cursor: "pointer",
                      }}
                      onClick={() => window.open(evidenceUrl, "_blank")}
                      onMouseOver={(e) => (e.currentTarget.style.transform = "scale(1.03)")}
                      onMouseOut={(e) => (e.currentTarget.style.transform = "scale(1)")}
                    />
                  </div>
                </div>
              </div>
            );
          })
        )}
      </div>
    </div>
  );

}
export default ViewReport;