import React from "react";
import { useLocation } from "react-router-dom";
import Navbar from "../components/Navbar";

export default function PaymentPage() {
    const { search } = useLocation();

    const params = new URLSearchParams(search);

    const eventId = params.get("event");
    const seatIdsRaw = params.get("seats");
    const amount = Number(params.get("amount"));

    const seatIds = seatIdsRaw ? seatIdsRaw.split(",").map(Number) : [];

    return (
        <div>
            <Navbar />

            <h1 style={{ marginLeft: "20px" }}>Confirm Your Purchase</h1>

            <div style={{ padding: "20px", fontSize: "20px" }}>
                <h2>Selected seats</h2>

                {seatIds.length === 0 ? (
                    <p>No seats selected</p>
                ) : (
                    <ul>
                        {seatIds.map(id => (
                            <li key={id}>Seat ID: {id}</li>
                        ))}
                    </ul>
                )}

                <h2>Total: {amount} ₴</h2>

                <button
                    style={{
                        marginTop: "20px",
                        padding: "12px 25px",
                        background: "#0077ff",
                        color: "white",
                        border: "none",
                        borderRadius: "10px",
                        fontSize: "20px",
                        cursor: "pointer"
                    }}
                    onClick={() => alert("Payment will be implemented")}
                >
                    Pay now
                </button>
            </div>
        </div>
    );
}
