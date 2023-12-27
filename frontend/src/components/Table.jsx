import React from "react";
import {brandIdHelper, lockerroomIdHelper} from "../helperFunctions";

const Table = ({tableData, tableHeaders, objectFields}) => {
    const tableHeader = () => {
        return (
        <tr data-id={0}>  
            {tableHeaders.map((header, index) => {
            return (  
                  <th key={index}>{header}</th>
            )})}
        </tr>
        )
    }

    const tableBody = () => {
        return tableData.map((dataPoint,index) => {
            const cells = Object.keys(dataPoint).filter(key => objectFields ? objectFields.includes(key) : null ).map((key, keyIndex) => {
                return <td key={key + keyIndex}>
                    {key === "brandId" ? brandIdHelper(dataPoint[key]) : 
                        key === "lockerId" ? lockerroomIdHelper(dataPoint[key]) : 
                            dataPoint[key]}
                </td>;
            });
            return (
                <tr data-id={index} key={index}>
                    {cells}
                </tr>
            );
        });
    }


    return (
        <table>
            <thead>{tableHeader()}</thead>
            <tbody>{tableBody()}</tbody>
        </table>
    )      
}

export default Table;