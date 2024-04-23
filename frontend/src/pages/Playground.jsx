import { TeiaiButton } from "../components/index";

const Playground = () => {
    return (
        <div>
            <div className="goBack">
                <a href="/">Let me out!!!</a> <p></p>
            </div>
            <div className="header text-center mt-5">
                <h1>Nero's Playground</h1>
            </div>

            <div className="componentTime container-fluid row text-center mt-5">
                <div className="col-12">
                    <TeiaiButton onClick={()=>console.log("Sangen")} label={"Sangen"} classNames={"primaryButton sangenButton h3Button"}/>
                    <TeiaiButton onClick={()=>console.log("Smackdown")} label={"Smackdown"} classNames={"primaryButton smackdownButton h3Button"}/>
                    <TeiaiButton onClick={()=>console.log("Primary")} label={"Primary"} classNames={"primaryButton h3Button"}/>
                    <TeiaiButton onClick={()=>console.log("Secondary")} label={"Secondary"} classNames={"secondaryButton h3Button"}/>
                </div>
            </div>
        </div>
    )
}

export default Playground;