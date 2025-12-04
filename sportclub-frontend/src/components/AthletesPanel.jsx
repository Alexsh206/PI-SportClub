import React, { useEffect, useState } from "react";
import {
    getAllAthletes,
    updateAthlete,
    createAthlete,
    deleteAthlete
} from "../api/api";

export default function AthletesPanel() {
    const [athletes, setAthletes] = useState([]);
    const [selected, setSelected] = useState(null);

    const [form, setForm] = useState({
        full_name: "",
        team_id: "",
        coach_id: "",
        sport: "",
        id: ""
    });

    useEffect(() => {
        loadAthletes();
    }, []);

    const loadAthletes = () => {
        getAllAthletes().then(setAthletes);
    };

    const handleSelect = (ath) => {
        setSelected(ath.id);
        setForm({
            full_name: ath.fullName,
            team_id: ath.team?.id || "",
            coach_id: ath.team?.coach?.id || "",
            sport: ath.sportType || "",
            id: ath.id
        });
    };

    const handleInput = (e) => {
        setForm({
            ...form,
            [e.target.name]: e.target.value
        });
    };

    const handleAdd = async () => {
        await createAthlete({
            fullName: form.full_name,
            sportType: form.sport,
            teamId: form.team_id
        });

        loadAthletes();
        alert("Athlete created!");
    };

    const handleUpdate = async () => {
        await updateAthlete(form.id, {
            fullName: form.full_name,
            sportType: form.sport,
            teamId: form.team_id
        });

        loadAthletes();
        alert("Athlete updated!");
    };

    const handleDelete = async () => {
        if (!selected) return alert("Select athlete first!");
        await deleteAthlete(selected);
        loadAthletes();
        alert("Athlete deleted!");
    };

    return (
        <div className="athletes-wrapper">

            {/* LEFT COLUMN */}
            <div className="athletes-list-box">
                <h2>Athletes</h2>

                <div className="athletes-list">
                    {athletes.map((a) => (
                        <div
                            key={a.id}
                            className={`athlete-item ${selected === a.id ? "selected" : ""}`}
                            onClick={() => handleSelect(a)}
                        >
                            {a.fullName} — Team {a.team?.teamName || "-"} — Coach {a.team?.coach?.fullName || "-"} — {a.sportType}
                        </div>
                    ))}
                </div>

                <div className="ath-actions-row">
                    <button className="delete-btn" onClick={handleDelete}>Delete</button>
                    <button className="add-btn" onClick={handleAdd}>Add</button>
                </div>
            </div>

            {/* RIGHT COLUMN */}
            <div className="athlete-form-box">
                <h2>Athlete</h2>

                <label>Name</label>
                <input name="full_name" value={form.full_name} onChange={handleInput} />

                <label>Team</label>
                <input name="team_id" value={form.team_id} onChange={handleInput} />

                <label>Coach</label>
                <input name="coach_id" value={form.coach_id} disabled />

                <label>Sports</label>
                <input name="sport" value={form.sport} onChange={handleInput} />

                <label>ID</label>
                <input name="id" value={form.id} disabled />

                <button className="done-btn" onClick={handleUpdate}>Done</button>
            </div>
        </div>
    );
}
