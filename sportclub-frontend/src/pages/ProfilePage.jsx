import React, { useEffect, useState } from "react";
import Navbar from "../components/Navbar";
import { useAuth } from "../auth/AuthProvider";
import { getTicketsBySpectator } from "../api/api";

export default function ProfilePage() {
    const { user } = useAuth();
    const [tickets, setTickets] = useState([]);

    useEffect(() => {
        async function load() {
            if (!user || !user.id) return;

            const data = await getTicketsBySpectator(user.id);
            setTickets(data);
        }

        load();
    }, [user]);

    if (!user) {
        return (
            <div>
                <Navbar />
                <h2 style={{ textAlign: "center", marginTop: "40px" }}>
                    Please log in to view your profile
                </h2>
            </div>
        );
    }

    return (
        <div className="profile-page">
            <Navbar />

            <div className="profile-header">
                <h1>My Profile</h1>
                <p><strong>Email:</strong> {user.email}</p>
                <p><strong>User ID:</strong> {user.id}</p>
            </div>

            <div className="ticket-section">
                <h2>My Tickets</h2>

                {tickets.length === 0 ? (
                    <p>You have no tickets yet.</p>
                ) : (
                    <div className="ticket-list">
                        {tickets.map(ticket => (
                            <div key={ticket.id} className="ticket-card">
                                <h3>{ticket.event.title}</h3>

                                <p>
                                    <strong>Seat:</strong>
                                    Row {ticket.seat.rowNumber}, Seat {ticket.seat.seatNumber}
                                </p>

                                <p><strong>Type:</strong> {ticket.seat.seatType}</p>
                                <p><strong>Price:</strong> {ticket.seat.price} ₴</p>

                                <p>
                                    <strong>Purchased:</strong>{" "}
                                    {ticket.purchaseDate ? ticket.purchaseDate.replace("T", " ") : "—"}
                                </p>

                                <p>
                                    <strong>Status:</strong>{" "}
                                    <span className={`status-${ticket.status.toLowerCase()}`}>
                                        {ticket.status}
                                    </span>
                                </p>
                            </div>
                        ))}
                    </div>
                )}
            </div>
        </div>
    );
}
