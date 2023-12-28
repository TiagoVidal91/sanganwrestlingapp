import React from "react";
import Table from 'react-bootstrap/Table'; 

const TeiaiTable = ({tableData, tableHeaders, id}) => {
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
            const cells = Object.keys(dataPoint).map((key, keyIndex) => {
                return <td key={key + keyIndex}>
                    {dataPoint[key]}
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
        <Table responsive striped hover>
            <thead>{tableHeader()}</thead>
            <tbody>{tableBody()}</tbody>
        </Table>
    )      
}

export default TeiaiTable;