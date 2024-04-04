import "./TeiaiButton.css";

const TeiaiButton = ({
    label = "", 
    classNames,
    ...others
}) => {
    return (
        <>
            <button type="button" className={"teiaiButton " + classNames}{...others}>
                <p>{label}</p>
            </button>
        </>
    )
}

export default TeiaiButton;