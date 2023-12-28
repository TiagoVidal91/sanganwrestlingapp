import data from '../mockData.json';
import TeiaiTable from '../components/TeiaiTable/TeiaiTable';
import {filterData} from "../helperFunctions";

const StreakPage = () => {
    var headers= ["Name", "Brand", "Locker room", "Wins", "Loses", "Matches Total"];
    var objectFields=  ["wrestlerName", "brandId", "lockerId", "numberOfWins", "numberOfLosses", "numberOfMatches"];
    var filteredData = filterData(data, objectFields);
    return (
        <>
            <h1>Score!!!!</h1>
            <TeiaiTable tableData={filteredData} tableHeaders={headers} objectFields={objectFields} id={"streakTable"}/>
        </>
    )
}
export default StreakPage

