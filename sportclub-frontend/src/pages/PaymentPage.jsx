import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import Navbar from "../components/Navbar";
import { getSeatById, payForSeat } from "../api/api";
import { useAuth } from "../auth/AuthProvider";

export default function PaymentPage() {
    const { user } = useAuth();
    const location = useLocation();
    const navigate = useNavigate();

    const query = new URLSearchParams(location.search);

    const eventId = query.get("event");
    const seatIds = query.get("seats")?.split(",") || [];
    const amount = query.get("amount") || 0;

    const [seatDetails, setSeatDetails] = useState([]);

    useEffect(() => {
        async function load() {
            const list = [];
            for (const id of seatIds) {
                const seat = await getSeatById(id);
                list.push(seat);
            }
            setSeatDetails(list);
        }
        load();
    }, []);

    const handlePayment = async () => {
        try {
            if (!user || !user.id) {
                alert("User not logged in!");
                return;
            }

            const spectatorId = user.id; // 🔥 АВТОМАТИЧНО

            for (const seat of seatDetails) {
                await payForSeat({
                    spectatorId,
                    eventId,
                    seatId: seat.id,
                    amount: seat.price
                });
            }

            alert("Оплата успішна!");
            navigate("/profile");

        } catch (err) {
            alert("Payment failed: " + err.message);
        }
    };

    return (
        <div>
            <Navbar />

            <h1>Confirm Your Purchase</h1>

            <div>
                <h3>Selected seats:</h3>
                <ul>
                    {seatDetails.map(s => (
                        <li key={s.id}>
                            Row {s.rowNumber}, Seat {s.seatNumber} — {s.price} ₴
                        </li>
                    ))}
                </ul>

                <h3>Total: {amount} ₴</h3>

                <button onClick={handlePayment} className="buy-btn">
                    Pay now
                </button>
            </div>
        </div>
    );
}
