import { Link, useNavigate } from "react-router-dom";

export default function Navbar({ user, setUser }) {
  const navigate = useNavigate();

  const logout = () => {
    localStorage.removeItem("user");
    setUser(null);
    navigate("/login");
  };

  const role = user?.role?.toUpperCase(); // safety + normalize

  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-primary px-3">
      
      <Link className="navbar-brand fw-bold" 
            to={role === "ADMIN" ? "/dashboard" : "/milk-entry"}>
        🐄 Milk Collection
      </Link>

      <div className="collapse navbar-collapse">
        <ul className="navbar-nav ms-auto">
          {/* -------- ADMIN ONLY ITEMS -------- */}
          {role === "ADMIN" && (
            <>
              <li className="nav-item">
                <Link className="nav-link" to="/dashboard">Dashboard</Link>
              </li>

              <li className="nav-item">
                <Link className="nav-link" to="/farmers">Farmers List</Link>
              </li>

              <li className="nav-item">
                <Link className="nav-link" to="/summary">Monthly Summary</Link>
              </li>
            </>
          )}

          {/* -------- COMMON ITEMS (Admin + Employee) -------- */}
          <li className="nav-item">
            <Link className="nav-link" to="/milk-entry">Add Milk Entry</Link>
          </li>

          <li className="nav-item">
            <Link className="nav-link" to="/add-farmer">Add Farmer</Link>
          </li>

          <li className="nav-item">
            <Link className="nav-link" to="/add-deduction">Add Deduction</Link>
          </li>

          <li className="nav-item">
            <Link className="nav-link" to="/milk-records">Milk Records</Link>
          </li>

          

          {/* Logout */}
          {role && (
            <li className="nav-item">
              <button className="btn btn-light btn-sm ms-2" onClick={logout}>
                Logout
              </button>
            </li>
          )}

        </ul>
      </div>
    </nav>
  );
}
