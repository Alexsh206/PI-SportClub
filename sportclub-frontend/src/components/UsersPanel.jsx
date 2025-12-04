import React, { useEffect, useState } from "react";
import { getAllUsers, blockUser, unblockUser } from "../api/api";

export default function UsersPanel() {
    const [users, setUsers] = useState([]);
    const [selected, setSelected] = useState(null);
    const [current, setCurrent] = useState(null);

    useEffect(() => {
        loadUsers();
    }, []);

    const loadUsers = () => {
        getAllUsers().then(setUsers);
    };

    const selectUser = (user) => {
        setSelected(user.id);
        setCurrent(user);
    };

    const handleBlock = async () => {
        if (!current) return;
        await blockUser(current.id);
        loadUsers();
    };

    const handleUnblock = async () => {
        if (!current) return;
        await unblockUser(current.id);
        loadUsers();
    };

    return (
        <div className="users-panel-wrapper">

            {/* LEFT — USERS LIST */}
            <div className="users-list-box">
                <h2>Users list</h2>

                <div className="users-scroll">
                    {users.map((u) => (
                        <div
                            key={u.id}
                            className={`user-item ${selected === u.id ? "selected" : ""}`}
                            onClick={() => selectUser(u)}
                        >
                            {u.fullName} — id {u.id} — status({u.status})
                        </div>
                    ))}
                </div>
            </div>

            {/* RIGHT — SELECTED USER INFO */}
            <div className="user-info-box">
                {current ? (
                    <>
                        <div className="user-avatar"></div>

                        <h2>{current.fullName}</h2>

                        <p>id {current.id}</p>
                        <p>status: {current.status}</p>

                        <div className="user-actions">
                            <button className="block-btn" onClick={handleBlock}>Block</button>
                            <button className="unblock-btn" onClick={handleUnblock}>Unblock</button>
                        </div>
                    </>
                ) : (
                    <p className="user-placeholder">Select user</p>
                )}
            </div>

        </div>
    );
}
