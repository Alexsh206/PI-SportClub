import React, { useEffect, useState } from "react";
import { getEventById } from "../api/api";

export default function EventCard({ event }) {
    const [fullEvent, setFullEvent] = useState(null);

    useEffect(() => {
        if (!event?.id) return;

        getEventById(event.id)
            .then(data => setFullEvent(data))
            .catch(err => console.error("Failed to load event:", err));
    }, [event?.id]);

    if (!fullEvent) {
        return (
            <div className="event-card">
                <p>Loading...</p>
            </div>
        );
    }

    return (
        <div className="event-card">
            <h2 className="event-title">{fullEvent.title}</h2>

            <p className="event-subtext">{fullEvent.sport_type}</p>

            <p className="event-subtext">{fullEvent.location}</p>

            <p className="event-subtext">
                {fullEvent.event_date} · {fullEvent.event_time?.slice(0, 5)}
            </p>

            <button className="show-more-btn">Show more</button>
        </div>
    );
}
