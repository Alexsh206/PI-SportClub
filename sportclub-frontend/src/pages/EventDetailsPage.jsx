import React, { useEffect, useState } from "react";
import {useParams, useNavigate, Link} from "react-router-dom";
import Navbar from "../components/Navbar";
import { useAuth } from "../auth/AuthProvider";

import {
    getEventById,
    getParticipantsByEvent
} from "../api/api";

export default function EventDetailsPage() {
    const { id } = useParams();
    const navigate = useNavigate();
    const { user } = useAuth();

    const [event, setEvent] = useState(null);
    const [participants, setParticipants] = useState([]);

    // Перевірка ролі
    useEffect(() => {
        if (!user) {
            navigate("/login");
            return;
        }
        if (user.role !== "spectator") {
            navigate("/forbidden");
            return;
        }
    }, [user]);

    // Завантаження даних івента
    useEffect(() => {
        getEventById(id).then(setEvent);
        getParticipantsByEvent(id).then(setParticipants);
    }, [id]);

    if (!event) return <div>Loading...</div>;

    return (
        <div className="event-details-page">
            <Navbar />

            <div className="event-details-container">
                <div className="event-left">
                    <h1 className="event-title">{event.title}</h1>

                    <p className="event-info">{event.sportType}</p>
                    <p className="event-info">{event.location}</p>
                    <p className="event-info">
                        {event.date} • {event.time?.slice(0, 5)}
                    </p>

                    <h2>Participants</h2>
                    <ul>
                        {participants.map(p => (
                            <li key={p.id}>
                                {p.participantType === "team"
                                    ? `Team: ${p.team?.name}`
                                    : `Athlete: ${p.athlete?.fullName}`}
                            </li>
                        ))}
                    </ul>
                </div>

                <div className="event-right">
                    <div className="event-image-placeholder">Image</div>
                </div>
            </div>

            <div className="event-buy-container">
                <Link to={`/event/${event.id}/seats`}>
                    <button>Buy now</button>
                </Link>
            </div>
        </div>
    );
}
