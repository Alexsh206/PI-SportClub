import React, { useEffect, useState } from "react";
import { getAllTeams, createTeam, updateTeam, deleteTeam } from "../api/api";

export default function TeamsPanel() {
    const [teams, setTeams] = useState([]);
    const [selected, setSelected] = useState(null);

    const [form, setForm] = useState({
        team_name: "",
        coach_id: "",
        athletes: "",
        statistics: "",
        id: ""
    });

    useEffect(() => {
        loadTeams();
    }, []);

    const loadTeams = () => {
        getAllTeams().then(setTeams);
    };

    const handleSelect = (team) => {
        setSelected(team.id);
        setForm({
            team_name: team.team_name,
            coach_id: team.coach_id,
            athletes: "Athletes…", // можна оновити списком пізніше
            statistics: "Statistics…",
            id: team.id
        });
    };

    const handleInput = (e) => {
        setForm({
            ...form,
            [e.target.name]: e.target.value
        });
    };

    const handleAdd = async () => {
        await createTeam({
            teamName: form.team_name,
            coachId: form.coach_id
        });
        loadTeams();
        alert("Team created!");
    };

    const handleUpdate = async () => {
        await updateTeam(form.id, {
            teamName: form.team_name,
            coachId: form.coach_id
        });
        loadTeams();
        alert("Updated!");
    };

    const handleDelete = async () => {
        if (!selected) return alert("Select team first!");
        await deleteTeam(selected);
        setSelected(null);
        loadTeams();
        alert("Deleted!");
    };

    return (
        <div className="teams-wrapper">

            {/* LEFT: LIST OF TEAMS */}
            <div className="teams-list-box">
                <h2>Teams</h2>

                <div className="teams-list">
                    {teams.map((t) => (
                        <div
                            key={t.id}
                            className={`team-item ${selected === t.id ? "selected" : ""}`}
                            onClick={() => handleSelect(t)}
                        >
                            {t.team_name} — Coach {t.coach_id} — Athletes…
                        </div>
                    ))}
                </div>

                <div className="actions-row">
                    <button className="delete-btn" onClick={handleDelete}>Delete</button>
                    <button className="add-btn" onClick={handleAdd}>Add</button>
                </div>
            </div>

            {/* RIGHT: TEAM FORM */}
            <div className="team-form-box">
                <h2>Team {form.id || ""}</h2>

                <label>Name</label>
                <input name="team_name" value={form.team_name} onChange={handleInput} />

                <label>Coach</label>
                <input name="coach_id" value={form.coach_id} onChange={handleInput} />

                <label>Athletes</label>
                <input name="athletes" value={form.athletes} readOnly />

                <label>Statistics</label>
                <input name="statistics" value={form.statistics} readOnly />

                <label>ID</label>
                <input name="id" value={form.id} disabled />

                <button className="done-btn" onClick={handleUpdate}>
                    Done
                </button>
            </div>
        </div>
    );
}
