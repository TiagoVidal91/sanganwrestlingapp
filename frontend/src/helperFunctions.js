import CONSTANTS from "./CONSTANTS.json"

const brandIdHelper = (value) => {
    console.log("brand")
    var brand;
    if(value===CONSTANTS.BRAND.SANGEN){
        brand = "Sangen";
    } else if (value == CONSTANTS.BRAND.SMACKLDOWN){
        brand = "Smackdown";
    };
    return brand;
}

const lockerroomIdHelper = (value) => {
    var lockerroom;
    if(value ===CONSTANTS.LOCKERROOM.MEN){
        lockerroom = "Men";
    } else if (value === CONSTANTS.LOCKERROOM.WOMEN){
        lockerroom = "Women";
    }
    return lockerroom;
}

export { brandIdHelper, lockerroomIdHelper };