import './App.css'
import { useEffect } from "react";
import {createBrowserRouter, RouterProvider, useNavigate} from "react-router-dom";
import Layout from "./components/Layout.tsx";
import LoginPage from "./components/LoginPage.tsx";
import HomePage from "./components/HomePage.tsx";

function App() {

    const navigate = useNavigate();

    useEffect(() => {
        const token = localStorage.getItem("jwtToken");
        if (!token) {
            navigate("/login");
        }
    }, []);

    const router = createBrowserRouter([
        {
            path: "/",
            element: <Layout/>,
            children: [
                {
                    "path": "/",
                    element: <HomePage/>
                },
                {
                    "path": "/login",
                    element: <LoginPage />
                }
            ]
        }
    ]);

  
    return (
        <>
          <div className="App">
              <RouterProvider router={router} />
          </div>
        </>
    )
}

export default App
