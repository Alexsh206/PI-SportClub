import React, { useEffect, useState } from "react";
import { getAllEvents, createEvent } from "../api/api";

export default function CreateEventPanel() {
    const [events, setEvents] = useState([]);
    const [form, setForm] = useState({
        title: "",
        date: "",
        time: "",
        location: "",
        sport_type: "",
        tournament_name: "",
        status: "scheduled"
    });

    // Load all events
    useEffect(() => {
        loadEvents();
    }, []);

    const loadEvents = () => {
        getAllEvents().then(setEvents);
    };

    const handleInput = (e) => {
        setForm({
            ...form,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async () => {
        try {
            await createEvent({
                title: form.title,
                sportType: form.sport_type,
                tournamentName: form.tournament_name,
                date: form.date,
                time: form.time,
                location: form.location,
                status: "scheduled"
            });

            loadEvents(); // refresh list
            alert("Event created successfully");

            setForm({
                title: "",
                date: "",
                time: "",
                location: "",
                sport_type: "",
                tournament_name: "",
                status: "scheduled"
            });

        } catch (err) {
            console.error(err);
            alert("Error creating event");
        }
    };

    return (
        <div className="create-event-wrapper">

            {/* EVENTS LIST */}
            <div className="event-list-box">
                <h2>Events</h2>

                <div className="event-list">
                    {events.map(e => (
                        <div key={e.id} className="event-item">
                            {`${e.title} | ${e.date} ${e.time?.slice(0,5)} | ${e.location}`}
                        </div>
                    ))}
                </div>

                <div className="actions-row">
                    <button className="delete-btn">Delete</button>
                    <button className="add-btn" onClick={handleSubmit}>Add</button>
                </div>
            </div>

            {/* EVENT CREATION FORM */}
            <div className="event-create-box">
                <h2>Event creating</h2>

                <label>Event name</label>
                <input name="title" value={form.title} onChange={handleInput} />

                <label>Date/Time</label>
                <div className="flex-row">
                    <input
                        type="date"
                        name="date"
                        value={form.date}
                        onChange={handleInput}
                        className="half-input"
                    />
                    <input
                        type="time"
                        name="time"
                        value={form.time}
                        onChange={handleInput}
                        className="half-input"
                    />
                </div>

                <label>Place</label>
                <input name="location" value={form.location} onChange={handleInput} />

                <label>Sport</label>
                <input name="sport_type" value={form.sport_type} onChange={handleInput} />

                <label>Tournament</label>
                <input name="tournament_name" value={form.tournament_name} onChange={handleInput} />

                <button className="done-btn" onClick={handleSubmit}>
                    Done
                </button>
            </div>
        </div>
    );
}
