import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import MainPage from "./pages/MainPage";
import "./global.css";
import {AuthProvider} from "./auth/AuthProvider";
import LoginPage from "./pages/LoginPage";
import EventDetailsPage from "./pages/EventDetailsPage";
import AdminPage from "./pages/AdminPage";
import CoachPanel from "./components/CoachPanel";

function App() {
    return (
        <Router>
        <AuthProvider>
            <Routes>
                <Route path="/" element={<MainPage />} />
                <Route path="/login" element={<LoginPage />} />
                <Route path="/events/:id" element={<EventDetailsPage />} />
                <Route path="/admin" element={<AdminPage />} />
                <Route path="/coach" element={<CoachPanel />} />
            </Routes>
            </AuthProvider>
        </Router>
    );
}

export default App;
