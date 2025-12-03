import React, { useState, useEffect } from "react";
import Navbar from "../components/Navbar";
import EventCard from "../components/EventCard";

// 🔥 імпорт з api.js
import { getAllEvents } from "../api/api";

export default function MainPage() {
    const [events, setEvents] = useState([]);

    useEffect(() => {
        getAllEvents()
            .then(data => setEvents(data))
            .catch(err => console.error("Failed to load events:", err));
    }, []);

    return (
        <div className="main-page">
            <Navbar/>

            <h1 className="main-title">Events</h1>

            <div className="events-grid">
                {events.map(event => (
                    <EventCard key={event.id} event={event} />
                ))}
            </div>
        </div>
    );
}
