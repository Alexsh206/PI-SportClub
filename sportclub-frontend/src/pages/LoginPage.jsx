import React, { useState } from "react";
import { useAuth } from "../auth/AuthProvider";
import { useNavigate } from "react-router-dom";
import Navbar from "../components/Navbar";

export default function LoginPage() {
    const { login } = useAuth();
    const navigate = useNavigate();

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError("");

        try {
            const res = await login({ email, password });

            if (!res || !res.data) {
                setError("Login failed");
                return;
            }

            const role = res.data.role;

            if (role === "admin") {
                navigate("/admin");
            } else if (role === "spectator") {
                navigate("/");
            }

        } catch (err) {
            console.error(err);
            setError("Invalid email or password");
        }
    };

    return (
        <>
            <Navbar />

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

                        {error && <p className="error-text">{error}</p>}

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
