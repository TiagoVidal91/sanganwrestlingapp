import data from '../mockData.json';
import Table from '../components/Table';

const StreakPage = () => {
    console.log(data);
    var headers= ["Name", "Brand", "Locker room", "Wins", "Loses", "Matches Total"];
    var objectFields=  ["wrestlerName", "brandId", "lockerId", "numberOfWins", "numberOfLosses", "numberOfMatches"];
    return (
        <>
            <h1>Score!!!!</h1>
            <Table tableData={data} tableHeaders={headers} objectFields={objectFields}/>
        </>
    )
}
export default StreakPage