import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import Navbar from "../components/Navbar";
import { getHallByEvent } from "../api/api";

export default function SeatSelectionPage() {
    const { id } = useParams();
    const [hall, setHall] = useState(null);
    const [selectedSeat, setSelectedSeat] = useState(null);

    useEffect(() => {
        getHallByEvent(id)
            .then(data => setHall(data))
            .catch(err => console.error("Failed to load hall:", err));
    }, [id]);

    if (!hall) {
        return (
            <div>
                <Navbar />
                <h2 style={{ textAlign: "center", marginTop: "40px" }}>Loading...</h2>
            </div>
        );
    }

    const rows = hall.rowsCount;
    const seats = hall.seatsInRow;

    return (
        <div className="seat-page">
            <Navbar />

            <h1 className="seat-title">Select a seat</h1>

            <div className="seat-grid">
                {[...Array(rows)].map((_, rowIndex) => (
                    <div className="seat-row" key={rowIndex}>
                        {[...Array(seats)].map((_, seatIndex) => {
                            const seatId = `${rowIndex + 1}-${seatIndex + 1}`;

                            return (
                                <button
                                    key={seatId}
                                    className={
                                        selectedSeat === seatId
                                            ? "seat selected"
                                            : "seat"
                                    }
                                    onClick={() => setSelectedSeat(seatId)}
                                >
                                    {seatIndex + 1}
                                </button>
                            );
                        })}
                    </div>
                ))}
            </div>

            {selectedSeat && (
                <div className="seat-info">
                    <h3>You selected seat: {selectedSeat}</h3>
                </div>
            )}
        </div>
    );
}
