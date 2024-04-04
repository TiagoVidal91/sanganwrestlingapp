import TeiaiButton from "../components/TeiaiButton/TeiaiButton";

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
                <img className="col-sm-4" src={"https://media.discordapp.net/attachments/669930050144960562/1224818249720533162/Tumblr_l_513528425914830.gif?ex=661edfe2&is=660c6ae2&hm=186f0b5996b0e3747ed15ac86e054c6fdaf1c98357b782d8400fb076c9b6afb1&=&width=675&height=376"} />
                <img className="col-sm-4" src={"https://rotatingsandwiches.com/wp-content/uploads/2023/02/club-sandwich.gif?w=480"} />
            </div>

            <div className="componentTime container-fluid row text-center">
                <div className="col-12">
                    <TeiaiButton onClick={()=>console.log("cocó")} label={"Primary"} classNames={"primaryButton"}/>
                    <TeiaiButton onClick={()=>console.log("xixi")} label={"Secondary"} classNames={"secondaryButton"}/>
                </div>
            </div>
        </div>
    )
}

export default Playground;