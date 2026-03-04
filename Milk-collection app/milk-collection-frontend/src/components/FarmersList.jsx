import { useEffect, useState } from "react";
import axios from "axios";
import "./farmers.css";  // ⭐ import style file

export default function FarmersList() {
  const [farmers, setFarmers] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:7777/api/farmers")
      .then((res) => setFarmers(res.data))
      .catch(() => alert("Failed to load farmers"));
  }, []);

  return (
    <div className="farmers-wrapper">

      <div className="container-fluid mt-4 content-box">
        <h2 className="fw-bold mb-4 text-primary text-center">
          👨‍🌾 Farmers Directory
        </h2>

        <div className="table-responsive glass-table">
          <table className="table table-bordered table-hover text-center align-middle">
            <thead className="table-primary">
              <tr>
                <th>Farmer ID</th>
                <th>Name</th>
                <th>Phone</th>
                <th>Village</th>
                <th>Created Date</th>
                <th>Total Milk (L)</th>
                <th>Total Milk Price (₹)</th>
                <th>Deductions (₹)</th>
                <th>Net Payable (₹)</th>
              </tr>
            </thead>

            <tbody>
              {farmers.length > 0 ? (
                farmers.map((f, i) => (
                  <tr key={i}>
                    <td>{f.farmerId ?? "-"}</td>
                    <td className="fw-bold">{f.name}</td>
                    <td>{f.phone}</td>
                    <td>{f.village}</td>
                    <td>{f.createdAt ? f.createdAt.slice(0, 10) : "-"}</td>
                    <td>{f.totalMilk ?? 0}</td>
                    <td>{f.totalPrice ?? 0}</td>
                    <td>{f.deductions ?? 0}</td>
                    <td>{f.netPayable ?? 0}</td>
                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan="9" className="text-muted">
                    No farmers found
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      </div>

    </div>
  );
}
