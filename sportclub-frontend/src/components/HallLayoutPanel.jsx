import React, { useState } from "react";
import { createHallLayout } from "../api/api";

export default function HallLayoutPage() {
    const [eventId, setEventId] = useState("");
    const [rows, setRows] = useState("");
    const [seats, setSeats] = useState("");
    const [message, setMessage] = useState("");

    const handleSubmit = async () => {
        try {
            const payload = {
                event: { id: Number(eventId) },
                rowsCount: Number(rows),
                seatsInRow: Number(seats)
            };

            await createHallLayout(payload);
            setMessage("Hall layout created successfully!");
        } catch (error) {
            setMessage("Error: " + error.message);
        }
    };

    return (
        <div className="hall-layout-page">

            <div className="hall-layout-form">
                <h1>Hall Layout</h1>

                <label>Event ID</label>
                <input value={eventId} onChange={e => setEventId(e.target.value)} />

                <label>Rows</label>
                <input value={rows} onChange={e => setRows(e.target.value)} />

                <label>Seats per row</label>
                <input value={seats} onChange={e => setSeats(e.target.value)} />

                <button onClick={handleSubmit} className="btn-done">
                    Done
                </button>

                {message && <p>{message}</p>}
            </div>
        </div>
    );
}
