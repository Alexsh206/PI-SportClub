import React from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../auth/AuthProvider";

export default function Navbar() {
    const navigate = useNavigate();
    const { user, logout } = useAuth();

    const handleLogout = () => {
        logout();
        navigate("/");
    };

    return (
        <nav className="navbar">
            <div className="navbar-left">
                <img
                    src="/logo.png"
                    alt="Sport Arena Logo"
                    className="logo"
                    onClick={() => navigate("/")}
                />

                <button className="nav-btn" onClick={() => navigate("/events")}>Events</button>
                <button className="nav-btn" onClick={() => navigate("/search")}>Search</button>
                <button className="nav-btn" onClick={() => navigate("/online")}>Watch online</button>
                <button className="nav-btn" onClick={() => navigate("/sports")}>Sports</button>
            </div>

            <div className="navbar-right">
                {user ? (
                    <>
                        <button className="nav-btn" onClick={() => navigate("/profile")}>Profile</button>
                        <button className="nav-btn" onClick={handleLogout}>Log out</button>
                    </>
                ) : (
                    <>
                        <button className="nav-btn" onClick={() => navigate("/login")}>Log in</button>
                        <button className="nav-btn" onClick={() => navigate("/register")}>Sign in</button>
                    </>
                )}
            </div>
        </nav>
    );
}
