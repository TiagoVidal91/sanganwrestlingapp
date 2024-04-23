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
            <div className="row container-fluid images text-center mt-5 mb-5">
                <img className="col-sm-4" src={"https://rotatingsandwiches.com/wp-content/uploads/2023/02/club-sandwich.gif?w=480"} />
                <img className="col-sm-4" src={"https://64.media.tumblr.com/9972f2cc55366244b7c0139d174a7c56/aee619acbc704246-f1/s540x810/33d096dd984904305c1b233d5d2112d46442397d.gifv"} />
                <img className="col-sm-4" src={"https://rotatingsandwiches.com/wp-content/uploads/2023/02/club-sandwich.gif?w=480"} />
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