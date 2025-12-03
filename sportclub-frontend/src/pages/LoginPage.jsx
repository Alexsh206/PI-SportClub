import React, { useState } from "react";
import { useAuth } from "../auth/AuthProvider";
import Navbar from "../components/Navbar";  // ⬅ Додаємо Navbar

export default function LoginPage() {
    const { login } = useAuth();
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        await login({ email, password });
    };

    return (
        <>
            <Navbar />  {/* ⬅ Панель зверху для LoginPage */}

            <div className="login-page">
                <div className="login-box">
                    <h2 className="login-title">Log in</h2>

                    <form onSubmit={handleSubmit}>
                        <label className="login-label">Email</label>
                        <input
                            className="login-input"
                            type="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                        />

                        <label className="login-label">Password</label>
                        <input
                            className="login-input"
                            type="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                        />

                        <p className="login-small-text">Don’t have account?</p>

                        <button type="submit" className="login-btn">
                            GO
                        </button>
                    </form>
                </div>
            </div>
        </>
    );
}
