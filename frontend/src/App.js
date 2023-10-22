import {Route, Routes } from "react-router-dom";
import React from "react";
import AppRoutes from "./AppRoutes";
import LandingPage from "./pages/LandingPage";


function App() {
  return (
    <>
    {/* <NavBar /> */}
    <Routes>
      {AppRoutes.map((route, index) => {
        console.log(route);
        const {element, ... rest } = route;
        return <Route path={index} key={index} {...rest} element={element} />;
      })} 
    </Routes>
    {/* <Footer /> */}
    </>
  );
}

export default App;
