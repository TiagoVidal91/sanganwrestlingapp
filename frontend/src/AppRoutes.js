import LandingPage from "./pages/LandingPage";
import ScorePage from "./pages/ScorePage";

const AppRoutes = [
    {
        path: "/",
        element: <LandingPage />
    },
    { 
        path: "/score",
        element: <ScorePage />
    }
]

export default AppRoutes;