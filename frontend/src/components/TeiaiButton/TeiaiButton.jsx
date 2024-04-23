import "./TeiaiButton.css";

const TeiaiButton = ({
    label = "", 
    classNames,
    ...others
}) => {
    return (
        <>
            <button 
                type="button" 
                className={"teiaiButton " + classNames}
                {...others}
            >
                <span>{label}</span>
            </button>
        </>
    )
}

export default TeiaiButton;