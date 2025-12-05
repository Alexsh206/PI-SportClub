import React, { useState, useEffect } from "react";
import { getAllEvents, createHallFull } from "../api/api";
import { useNavigate } from "react-router-dom";

export default function HallLayoutPage() {
    const navigate = useNavigate();

    const [events, setEvents] = useState([]);
    const [selectedEvent, setSelectedEvent] = useState("");

    const [rows, setRows] = useState("");
    const [seatsInRow, setSeatsInRow] = useState("");

    // VIP
    const [vipFrom, setVipFrom] = useState("");
    const [vipTo, setVipTo] = useState("");
    const [vipPrice, setVipPrice] = useState("");

    // Standard
    const [standardFrom, setStandardFrom] = useState("");
    const [standardTo, setStandardTo] = useState("");
    const [standardPrice, setStandardPrice] = useState("");

    // Economy
    const [economyFrom, setEconomyFrom] = useState("");
    const [economyTo, setEconomyTo] = useState("");
    const [economyPrice, setEconomyPrice] = useState("");

    const [loading, setLoading] = useState(false);
    const [msg, setMsg] = useState("");

    useEffect(() => {
        getAllEvents()
            .then(setEvents)
            .catch(err => console.error("Failed to load events", err));
    }, []);

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!selectedEvent) {
            setMsg("Select event first!");
            return;
        }

        const payload = {
            eventId: Number(selectedEvent),
            rows: Number(rows),
            seatsInRow: Number(seatsInRow),

            vipFrom: Number(vipFrom),
            vipTo: Number(vipTo),
            vipPrice: Number(vipPrice),

            standardFrom: Number(standardFrom),
            standardTo: Number(standardTo),
            standardPrice: Number(standardPrice),

            economyFrom: Number(economyFrom),
            economyTo: Number(economyTo),
            economyPrice: Number(economyPrice)
        };

        setLoading(true);
        setMsg("");

        try {
            await createHallFull(payload);
            setMsg("Hall created successfully!");
            setTimeout(() => navigate("/admin"), 1000);
        } catch (err) {
            console.error(err);
            setMsg("Error: " + err.message);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="hall-layout-page">

            <div className="hall-layout-wrapper">
                <h1>Create hall layout</h1>

                <form className="hall-form" onSubmit={handleSubmit}>

                    {/* ====================== EVENT ===================== */}
                    <div className="form-section">
                        <label>Event</label>
                        <select
                            className="input"
                            value={selectedEvent}
                            onChange={(e) => setSelectedEvent(e.target.value)}
                        >
                            <option value="">Select event</option>
                            {events.map(ev => (
                                <option key={ev.id} value={ev.id}>
                                    {ev.title}
                                </option>
                            ))}
                        </select>

                        <div className="two-cols">
                            <div>
                                <label>Total rows</label>
                                <input
                                    type="number"
                                    className="input"
                                    value={rows}
                                    onChange={(e) => setRows(e.target.value)}
                                />
                            </div>

                            <div>
                                <label>Seats in each row</label>
                                <input
                                    type="number"
                                    className="input"
                                    value={seatsInRow}
                                    onChange={(e) => setSeatsInRow(e.target.value)}
                                />
                            </div>
                        </div>
                    </div>

                    {/* ====================== VIP ===================== */}
                    <div className="category-block vip">
                        <h2>VIP category</h2>

                        <label>Rows (from — to)</label>
                        <div className="two-cols">
                            <input
                                type="number"
                                className="input"
                                placeholder="From"
                                value={vipFrom}
                                onChange={(e) => setVipFrom(e.target.value)}
                            />
                            <input
                                type="number"
                                className="input"
                                placeholder="To"
                                value={vipTo}
                                onChange={(e) => setVipTo(e.target.value)}
                            />
                        </div>

                        <label>Price</label>
                        <input
                            type="number"
                            className="input"
                            value={vipPrice}
                            onChange={(e) => setVipPrice(e.target.value)}
                        />
                    </div>

                    {/* ====================== STANDARD ===================== */}
                    <div className="category-block standard">
                        <h2>Standard category</h2>

                        <label>Rows (from — to)</label>
                        <div className="two-cols">
                            <input
                                type="number"
                                className="input"
                                placeholder="From"
                                value={standardFrom}
                                onChange={(e) => setStandardFrom(e.target.value)}
                            />
                            <input
                                type="number"
                                className="input"
                                placeholder="To"
                                value={standardTo}
                                onChange={(e) => setStandardTo(e.target.value)}
                            />
                        </div>

                        <label>Price</label>
                        <input
                            type="number"
                            className="input"
                            value={standardPrice}
                            onChange={(e) => setStandardPrice(e.target.value)}
                        />
                    </div>

                    {/* ====================== ECONOMY ===================== */}
                    <div className="category-block economy">
                        <h2>Economy category</h2>

                        <label>Rows (from — to)</label>
                        <div className="two-cols">
                            <input
                                type="number"
                                className="input"
                                placeholder="From"
                                value={economyFrom}
                                onChange={(e) => setEconomyFrom(e.target.value)}
                            />
                            <input
                                type="number"
                                className="input"
                                placeholder="To"
                                value={economyTo}
                                onChange={(e) => setEconomyTo(e.target.value)}
                            />
                        </div>

                        <label>Price</label>
                        <input
                            type="number"
                            className="input"
                            value={economyPrice}
                            onChange={(e) => setEconomyPrice(e.target.value)}
                        />
                    </div>

                    {/* MESSAGE */}
                    {msg && <p className="msg">{msg}</p>}

                    {/* SUBMIT BUTTON */}
                    <button className="submit-btn" disabled={loading}>
                        {loading ? "Generating..." : "Generate"}
                    </button>

                </form>
            </div>
        </div>
    );
}
