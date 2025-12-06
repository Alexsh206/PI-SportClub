import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import Navbar from "../components/Navbar";
import {
    getHallByEvent,
    getSeatsByHall,
    reserveSeat
} from "../api/api";

export default function SeatSelectionPage() {
    const { id } = useParams(); // eventId
    const navigate = useNavigate();

    const [hall, setHall] = useState(null);
    const [seats, setSeats] = useState([]);
    const [selectedSeats, setSelectedSeats] = useState([]);

    useEffect(() => {
        async function load() {
            try {
                const hallData = await getHallByEvent(id);
                setHall(hallData);

                const hallId = hallData.id;
                const seatData = await getSeatsByHall(hallId);

                setSeats(seatData);
            } catch (err) {
                console.error("Failed to load hall or seats:", err);
            }
        }

        load();
    }, [id]);

    if (!hall || seats.length === 0) {
        return (
            <div>
                <Navbar />
                <h2 className="seat-loading">Loading hall layout...</h2>
            </div>
        );
    }

    const rows = {};
    seats.forEach(seat => {
        if (!rows[seat.rowNumber]) rows[seat.rowNumber] = [];
        rows[seat.rowNumber].push(seat);
    });

    const toggleSeat = (seat) => {
        if (seat.status === "SOLD") return;

        const exists = selectedSeats.some(s => s.id === seat.id);

        if (exists) {
            setSelectedSeats(prev => prev.filter(s => s.id !== seat.id));
        } else {
            setSelectedSeats(prev => [...prev, seat]);
        }
    };

    const totalPrice = selectedSeats.reduce((sum, seat) => sum + seat.price, 0);

    // ------------------------------
    // 🔥 БРОНЮВАННЯ + РЕДІРЕКТ НА PAYMENT
    // ------------------------------
    const proceedToPayment = async () => {
        try {
            for (const seat of selectedSeats) {
                await reserveSeat(seat.id);   // резервуємо
            }

            // 🔥 ПЕРЕЗАГРУЖАЄМО СТАТУСИ
            const updatedSeats = await getSeatsByHall(hall.id);
            setSeats(updatedSeats);

            const seatIds = selectedSeats.map(s => s.id).join(",");
            navigate(`/payment?event=${id}&seats=${seatIds}&amount=${totalPrice}`);

        } catch (err) {
            console.error(err);
            alert("Reservation failed: " + err.message);
        }
    };



    return (
        <div className="seat-page">
            <Navbar />

            <h1 className="seat-title">Select seats</h1>

            <div className="seat-grid">
                {Object.keys(rows).map(row => (
                    <div key={row} className="seat-row">
                        <div className="row-label">Row {row}</div>

                        {rows[row].map(seat => {
                            const isSelected = selectedSeats.some(s => s.id === seat.id);

                            return (
                                <div
                                    key={seat.id}
                                    className={
                                        `seat 
                                        ${isSelected ? "selected" : ""} 
                                        ${seat.status === "SOLD" ? "sold" : ""} 
                                        ${seat.status === "RESERVED" ? "reserved" : ""}`
                                    }
                                    onClick={() => toggleSeat(seat)}
                                >
                                    {seat.seatNumber}
                                </div>
                            );
                        })}
                    </div>
                ))}
            </div>

            <div className="seat-summary">
                <h2>Selected seats: {selectedSeats.length}</h2>
                <h2>Total price: {totalPrice} ₴</h2>

                {selectedSeats.length > 0 && (
                    <button className="buy-btn" onClick={proceedToPayment}>
                        Proceed to checkout
                    </button>
                )}
            </div>
        </div>
    );
}
