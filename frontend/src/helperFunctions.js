import CONSTANTS from "./CONSTANTS.json"

const brandIdHelper = (value) => {
    var brand;
    if(value===CONSTANTS.BRAND.SANGEN){
        brand = "Sangen";
    } else if (value===CONSTANTS.BRAND.SMACKDOWN){
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

const filterData = (data, objectFields) => {
    return data.map((dataPoint) => Object.keys(dataPoint).filter(key => objectFields.includes(key)).map((key) => {
        return (key === "brandId" ? brandIdHelper(dataPoint[key]) : 
        key === "lockerId" ? lockerroomIdHelper(dataPoint[key]) : dataPoint[key])}
    ));
}

export { brandIdHelper, lockerroomIdHelper, filterData };