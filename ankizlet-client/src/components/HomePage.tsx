import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import './HomePage.css';

interface ErrorResponse {
    response?: {
        data?: {
            message?: string;
        };
    };
}

function HomePage() {
    const navigate = useNavigate();
    const [menuOpen, setMenuOpen] = useState(false);
    const [isLoading, setIsLoading] = useState(true);


    useEffect(() => {
        localStorage.removeItem("jwtToken");
        const token = localStorage.getItem("jwtToken");
        if (!token) {
            navigate("/login"); // Перенаправление и удаление из истории переходов
        } else {
            console.log(token);
            setIsLoading(false);
        }
    }, [navigate]);

    if (isLoading) {
        return null; // Или можно вернуть <LoadingSpinner />
    }

    const checkSecurity = async (): Promise<void> => {
        const token = localStorage.getItem("jwtToken");
        if (!token) {
            setError("Нет токена, войдите в систему");
            navigate("/login");
            return;
        }

        try {
            const response = await axios.get<{ message: string }>("http://localhost:8082/products", {
                headers: { Authorization: `Bearer ${token}` }
            });
            setMessage(response.data.message);
            setError(null);
        } catch (err) {
            const errorResponse = err as ErrorResponse;
            setError("Ошибка доступа: " + (errorResponse.response?.data?.message || "Неизвестная ошибка"));
        }
    };

    return (
        <>
            <header>
                <div className="avatar-container" onClick={() => setMenuOpen(!menuOpen)}>
                    <img src="avatar.png" alt="User Avatar" className="avatar" />
                    <div className={`auth-menu ${menuOpen ? "show" : ""}`}>
                        <button>Login</button>
                        <button>Register</button>
                    </div>
                </div>
            </header>

            <main>
                <h1>Your Sets</h1>
                <div className="sets-container">
                    <div className="set-card">
                        <h2>Biology Basics</h2>
                        <p>15 terms</p>
                        <p>Created by: JohnDoe</p>
                    </div>
                    <div className="set-card">
                        <h2>History 101</h2>
                        <p>20 terms</p>
                        <p>Created by: JaneSmith</p>
                    </div>
                </div>
                <button className="create-set" onClick={checkSecurity}>+</button>
            </main>
        </>
    );
}

export default HomePage;
