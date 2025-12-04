import React, { useState } from "react";
import Navbar from "../components/Navbar";


import AnalyticsPanel from "../components/AnalyticsPanel";
import CreateEventPanel from "../components/CreateEventPanel";
import TeamsPanel from "../components/TeamsPanel";
import AthletesPanel from "../components/AthletesPanel";
import UsersPanel from "../components/UsersPanel";
import ReportPanel from "../components/ReportPanel";
import SalesOpeningPanel from "../components/SalesOpeningPanel";

export default function AdminPage() {
    const [panel, setPanel] = useState(null);

    const menuItems = [
        { label: "Analytics", panel: "analytics" },
        { label: "Create event", panel: "create-event" },
        { label: "Teams", panel: "teams" },
        { label: "Atheletes", panel: "athletes" },
        { label: "Hall layout", panel: "hall" },
        { label: "Users", panel: "users" },
        { label: "Report", panel: "report" },
        { label: "Sales opening", panel: "sales" },
    ];

    return (
        <div className="admin-page">
            <Navbar />

            <div className="admin-container">

                {/* LEFT SIDEBAR */}
                <div className="admin-sidebar">
                    <img src="/logo.png" alt="logo" className="admin-logo" />

                    <h2 className="admin-title">Admin panel</h2>

                    <button className="admin-leave-btn" onClick={() => setPanel(null)}>
                        Leave
                    </button>

                    <div className="admin-menu">
                        {menuItems.map((item, index) => (
                            <button
                                key={index}
                                className="admin-menu-btn"
                                onClick={() => setPanel(item.panel)}
                            >
                                {item.label}
                            </button>
                        ))}
                    </div>
                </div>

                {/* RIGHT CONTENT */}
                <div className="admin-content">

                    {panel === "analytics" && <AnalyticsPanel />}
                    {panel === "create-event" && <CreateEventPanel />}
                    {panel === "teams" && <TeamsPanel />}
                    {panel === "athletes" && <AthletesPanel />}
                    {panel === "users" && <UsersPanel />}
                    {panel === "report" && <ReportPanel />}
                    {panel === "sales" && <SalesOpeningPanel />}
                </div>

            </div>
        </div>
    );
}
