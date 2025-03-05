import './App.css'
import HomePage from "./components/HomePage.tsx";
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import Layout from "./components/Layout.tsx";
import LoginPage from "./components/LoginPage.tsx";

function App() {



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
