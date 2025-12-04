import React from "react";
import { useNavigate } from "react-router-dom";
import Navbar from "../components/Navbar";

export default function AdminPage() {
    const navigate = useNavigate();

    const menuItems = [
        { label: "Analytics", path: "/admin/analytics" },
        { label: "Create event", path: "/admin/create-event" },
        { label: "Teams", path: "/admin/teams" },
        { label: "Atheletes", path: "/admin/athletes" },
        { label: "Hall layout", path: "/admin/hall" },
        { label: "Users", path: "/admin/users" },
        { label: "Report", path: "/admin/report" },
        { label: "Sales opening", path: "/admin/sales" },
    ];

    return (
        <div className="admin-page">
            <Navbar />

            <div className="admin-container">

                {/* LEFT PANEL */}
                <div className="admin-sidebar">
                    <img
                        src="/logo.png"
                        alt="logo"
                        className="admin-logo"
                    />

                    <h2 className="admin-title">Admin panel</h2>

                    <button
                        className="admin-leave-btn"
                        onClick={() => navigate("/")}
                    >
                        Leave
                    </button>

                    <div className="admin-menu">
                        {menuItems.map((item, index) => (
                            <button
                                key={index}
                                className="admin-menu-btn"
                                onClick={() => navigate(item.path)}
                            >
                                {item.label}
                            </button>
                        ))}
                    </div>
                </div>

                {/* RIGHT PANEL */}
                <div className="admin-content">
                    <h1>Hello Admin, it’s your admin panel</h1>
                </div>

            </div>
        </div>
    );
}
