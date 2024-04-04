import LandingPage from "./pages/LandingPage";
import StreakPage from "./pages/StreakPage";
import Playground from "./pages/Playground";

const AppRoutes = [
    {
        path: "/",
        element: <LandingPage />
    },
    { 
        path: "/streaks",
        element: <StreakPage />
    },
    {
        path: "/playground",
        element: <Playground />
    }
]

export default AppRoutes;