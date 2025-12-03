import React from "react";
import {useNavigate} from "react-router-dom";

export default function Navbar() {
    const navigate = useNavigate();
    return (
        <nav className="navbar">
            <div className="navbar-left">
                <img
                    src="/logo.png"
                    alt="Sport Arena Logo"
                    className="logo"
                />

                <button className="nav-btn">Events</button>
                <button className="nav-btn">Search</button>
                <button className="nav-btn">Watch online</button>
                <button className="nav-btn">Sports</button>
            </div>

            <div className="navbar-right">
                <button className="nav-btn" onClick={() => navigate("/login")}>Log in</button>
                <button className="nav-btn">Sign in</button>
            </div>
        </nav>
    );
}
