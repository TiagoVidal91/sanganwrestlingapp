import LandingPage from "./pages/LandingPage";
import StreakPage from "./pages/StreakPage";

const AppRoutes = [
    {
        path: "/",
        element: <LandingPage />
    },
    { 
        path: "/streaks",
        element: <StreakPage />
    }
]

export default AppRoutes;