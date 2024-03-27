import data from '../mockData.json';
import TeiaiTable from '../components/TeiaiTable/TeiaiTable';
import {filterData} from "../helperFunctions";
import {useState, useEffect} from "react";
import { getWrestlers } from '../DataService';

const StreakPage = () => {
    var headers= ["Name", "Brand", "Locker room", "Wins", "Loses", "Matches Total", "Percentage Win", "Streak"];
    var objectFields=  ["inRingName", "brandId", "lockerId", "numberOfWins", "numberOfLosses", "numberOfMatches", "percentageOfWins", "wrestlingStreak"];
    const [tableData, setTableData] = useState([]);
    const data2 = getWrestlers();
    console.log(data2);
    useEffect(() => {
        var filteredData = filterData(data.content, objectFields);
        setTableData(filteredData);
    }, [])
    const onOrderChange = (e, order, header) => {
        e.preventDefault();
        //TEST
       let sortedData;
        if(order==="asc"){
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
            <TeiaiTable tableData={tableData} tableHeaders={headers} objectFields={objectFields} id={"streakTable"} numberPages={data.totalPages} onOrderChange={onOrderChange}/>
        </>
    )
}
export default StreakPage

