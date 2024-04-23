import data from '../mockData.json';
import TeiaiTable from '../components/TeiaiTable/TeiaiTable';
import {filterData} from "../helperFunctions";
import {useState, useEffect} from "react";

const StreakPage = () => {
    var headers= ["Name", "Brand", "Locker room", "Wins", "Loses", "Matches Total", "Percentage Win", "Streak"];
    var objectFields=  ["inRingName", "brandId", "lockerId", "numberOfWins", "numberOfLosses", "numberOfMatches", "percentageOfWins", "wrestlingStreak"];
    const [tableData, setTableData] = useState([]);

    useEffect(() => {
        var filteredData = filterData(data.content, objectFields);
        setTableData(filteredData);
    }, [])
    const onOrderChange = (e, order, header) => {
        e.preventDefault();
        //TEST
       let sortedData;
        if(order==="ASC"){
            sortedData = tableData.sort();
        } else {
            sortedData = tableData.reverse();
        }

        setTableData(sortedData);
        console.log(header);
    }
    return (
        <>
            <h1>Score!!!!</h1>
            <TeiaiTable tableData={tableData} tableHeaders={headers} objectFields={objectFields} id={"streakTable"} numberPages={5} onOrderChange={onOrderChange}/>
        </>
    )
}
export default StreakPage

