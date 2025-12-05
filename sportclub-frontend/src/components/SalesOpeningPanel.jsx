import React, { useState } from "react";

export default function SalesOpeningPanel() {
    const [eventName, setEventName] = useState("");
    const [dateTime, setDateTime] = useState("");
    const [place, setPlace] = useState("");
    const [teams, setTeams] = useState("");
    const [info, setInfo] = useState("");

    const handleAdd = () => {
        if (!eventName || !dateTime || !place || !teams) {
            alert("Please fill all required fields!");
            return;
        }

        const ticketOpening = {
            eventName,
            dateTime,
            place,
            teams,
            info,
        };

        console.log("Ticket sales opening created:", ticketOpening);

        alert("Ticket sales opening successfully created!");

        setEventName("");
        setDateTime("");
        setPlace("");
        setTeams("");
        setInfo("");
    };

    return (
        <div className="sales-panel">
            <h1>Ticket sales opening</h1>

            <div className="sales-section">

                <div className="sales-row">
                    <label>Event</label>
                    <input
                        className="sales-input"
                        value={eventName}
                        onChange={(e) => setEventName(e.target.value)}
                    />
                </div>

                <div className="sales-row">
                    <label>Date/time</label>
                    <input
                        type="datetime-local"
                        className="sales-input"
                        value={dateTime}
                        onChange={(e) => setDateTime(e.target.value)}
                    />
                </div>

                <div className="sales-row">
                    <label>Place</label>
                    <input
                        className="sales-input"
                        value={place}
                        onChange={(e) => setPlace(e.target.value)}
                    />
                </div>

                <div className="sales-row">
                    <label>Teams</label>
                    <input
                        className="sales-input"
                        value={teams}
                        onChange={(e) => setTeams(e.target.value)}
                    />
                </div>

                <div className="sales-row">
                    <label>More info</label>
                    <textarea
                        className="sales-input"
                        value={info}
                        onChange={(e) => setInfo(e.target.value)}
                    />
                </div>

                <button className="sales-add-btn" onClick={handleAdd}>
                    Add
                </button>
            </div>
        </div>
    );
}
