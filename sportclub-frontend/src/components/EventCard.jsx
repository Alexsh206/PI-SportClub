import React from "react";
import { Link } from "react-router-dom";

export default function EventCard({ event }) {
    return (
        <div className="event-card">

            {/* Нижній блок з текстом */}
            <div className="event-card-content">
                <h2 className="event-card-title">{event.title}</h2>

                <div className="event-card-info">
                    <p>{event.sportType}</p>
                    <p>{event.location}</p>
                    <p>{event.date} · {event.time?.slice(0, 5)}</p>
                </div>

                <Link to={`/events/${event.id}`} className="event-card-btn">
                    Show more
                </Link>
            </div>
        </div>
    );
}
