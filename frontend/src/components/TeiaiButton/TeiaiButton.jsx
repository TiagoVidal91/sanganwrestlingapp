import "./TeiaiButton.css";

const TeiaiButton = ({
    label = "", 
    classNames,
    ...others
}) => {
    return (
        <button 
            type="button" 
            className={"teiaiButton " + classNames}
            {...others}
        >
            {label}
        </button>
        
    )
}

export default TeiaiButton;