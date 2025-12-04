import React, { createContext, useState, useContext, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import * as api from "../api/api";

const AuthContext = createContext({
    user: null,
    isAuthenticated: false,
    login: async () => false,
    logout: () => {},
});

export function AuthProvider({ children }) {
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);
    const nav = useNavigate();

    useEffect(() => {
        const savedUser = localStorage.getItem("user");
        const token = localStorage.getItem("token");

        if (savedUser && token) {
            setUser(JSON.parse(savedUser));
        }
        setLoading(false);
    }, []);

    if (loading) return <div>Завантаження...</div>;

    const login = async ({ email, password }) => {
        try {
            const resp = await api.login({ email, password });

            if (resp?.status === 200) {
                const { token, role, id, fullName, email: userEmail, team } = resp.data;

                const profile = {
                    id,
                    role,
                    fullName,
                    email: userEmail,
                    team: team || null
                };

                localStorage.setItem("token", token);
                localStorage.setItem("user", JSON.stringify(profile));

                setUser(profile);

                if (role === "spectator") {
                    nav("/", { replace: true });
                }
                if (role === "sportsman") {
                    nav("/athlete", { replace: true });
                }
                if (role === "admin") {
                    nav("/admin", { replace: true });
                }

                return true;
            }
        } catch (err) {
            console.error("Login failed:", err);
        }

        return false;
    };

    /* ================================
                LOGOUT
    ================================= */
    const logout = () => {
        localStorage.removeItem("token");
        localStorage.removeItem("user");
        setUser(null);
        nav("/login", { replace: true });
    };

    return (
        <AuthContext.Provider
            value={{
                user,
                isAuthenticated: !!user,
                login,
                logout,
            }}
        >
            {children}
        </AuthContext.Provider>
    );
}

export const useAuth = () => useContext(AuthContext);
