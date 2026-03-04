import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import { useState, useEffect } from "react";
import "bootstrap-icons/font/bootstrap-icons.css";

import Navbar from "./components/Navbar";
import Login from "./components/Login";
import Dashboard from "./components/Dashboard";
import AddMilk from "./components/AddMilk";
import FarmersList from "./components/FarmersList";
import AddFarmer from "./components/AddFarmer";
import MilkList from "./components/MilkList";
import MonthlySummary from "./components/MonthlySummary";
import DeductionsList from "./components/DeductionsList";
import AddDeduction from "./components/AddDeduction";
import Footer from "./components/Footer";

function PrivateRoute({ children, allowedRole }) {
  const stored = JSON.parse(localStorage.getItem("user"));

  if (!stored) return <Navigate to="/login" />;

  const userRole = stored.role?.toUpperCase();
  const allowed = allowedRole?.toUpperCase();

  if (allowed && userRole !== allowed) {
    return <Navigate to="/milk-entry" />;
  }

  return children;
}

function App() {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true); 

  useEffect(() => {
    const stored = localStorage.getItem("user");
    if (stored) setUser(JSON.parse(stored));
    setLoading(false);
  }, []);
if (loading) return null; 
  return (
    <BrowserRouter>

      {user && <Navbar user={user} setUser={setUser} />}

      <Routes>
        <Route path="/login" element={<Login setUser={setUser} />} />

        <Route
          path="/dashboard"
          element={
            <PrivateRoute allowedRole="ADMIN">
              <Dashboard />
            </PrivateRoute>
          }
        />

        <Route
          path="/milk-entry"
          element={
            <PrivateRoute >
              <AddMilk />
            </PrivateRoute>
          }
        />

        <Route
          path="/farmers"
          element={
            <PrivateRoute>
              <FarmersList />
            </PrivateRoute>
          }
        />

        <Route
          path="/add-farmer"
          element={
            <PrivateRoute>
              <AddFarmer />
            </PrivateRoute>
          }
        />

        <Route
          path="/milk-records"
          element={
            <PrivateRoute>
              <MilkList />
            </PrivateRoute>
          }
        />

        <Route
          path="/deductions"
          element={
            <PrivateRoute>
              <DeductionsList />
            </PrivateRoute>
          }
        />

        <Route
          path="/add-deduction"
          element={
            <PrivateRoute>
              <AddDeduction />
            </PrivateRoute>
          }
        />

        <Route
          path="/summary"
          element={
            <PrivateRoute>
              <MonthlySummary />
            </PrivateRoute>
          }
        />
       
        <Route path="*" element={<Navigate to="/login" />} />
      </Routes>{user && <Footer user={user} setUser={setUser} />}
    </BrowserRouter>
    
  );
}

export default App;