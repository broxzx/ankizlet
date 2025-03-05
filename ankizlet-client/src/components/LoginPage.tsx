import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function LoginPage() {
    const [login, setLogin] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState<string | null>(null);
    const navigate = useNavigate();

    const handleLogin = async (event: React.FormEvent) => {
        event.preventDefault();
        setError(null);

        try {
            const response = await axios.post("http://localhost:8082/login", {
                login,
                password,
            }, {
                headers: { "Content-Type": "application/json" },
            });

            const token = response.data;
            localStorage.setItem("jwtToken", token);
            navigate("/");
        } catch (error) {
            console.error("Login error:", error);
            setError("Ошибка входа: неверные учетные данные");
        }
    };

    return (
        <div className="flex flex-col items-center justify-center min-h-screen p-6 bg-gray-100">
            <h1 className="text-2xl font-bold mb-4">Вход в систему</h1>
            <form onSubmit={handleLogin} className="bg-white p-6 rounded-lg shadow-md w-96">
                <div className="mb-4">
                    <label className="block text-gray-700">Имя пользователя</label>
                    <input
                        type="text"
                        value={login}
                        onChange={(e) => setLogin(e.target.value)}
                        className="w-full p-2 border rounded-lg mt-1"
                        required
                    />
                </div>
                <div className="mb-4">
                    <label className="block text-gray-700">Пароль</label>
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        className="w-full p-2 border rounded-lg mt-1"
                        required
                    />
                </div>
                <button type="submit" className="w-full py-2 bg-blue-500 text-white rounded-lg">Войти</button>
                {error && <p className="text-red-500 mt-4">{error}</p>}
            </form>
        </div>
    );
}

export default LoginPage;
