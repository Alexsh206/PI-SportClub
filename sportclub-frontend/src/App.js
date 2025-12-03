import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import MainPage from "./pages/MainPage";
import "./global.css";
import {AuthProvider} from "./auth/AuthProvider";
import LoginPage from "./pages/LoginPage";

function App() {
    return (
        <Router>
        <AuthProvider>
            <Routes>
                <Route path="/" element={<MainPage />} />
                <Route path="/login" element={<LoginPage />} />

            </Routes>
            </AuthProvider>
        </Router>
    );
}

export default App;
