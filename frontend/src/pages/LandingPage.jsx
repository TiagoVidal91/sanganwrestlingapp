import { useAuth0 } from "@auth0/auth0-react";

const LandingPage = () => {
    const { loginWithRedirect } = useAuth0();
    console.log("landingPage");
    
    return (
        <>
        <h1>Hi!!! :3</h1>
        <div>
            <button onClick={() => loginWithRedirect()}>Log In</button>
        </div>
        </>
        
    )
}

export default LandingPage;