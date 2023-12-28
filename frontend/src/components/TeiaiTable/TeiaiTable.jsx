import React, {useState} from "react";
import { Table, Pagination, } from 'react-bootstrap'; 
import "./TeiaiTable.css"

const TeiaiTable = ({tableData, tableHeaders, id}) => {
    //TABLE HEADERS
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

    //TABLE BODY
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

    //TABLE PAGINATION
    const [activePage, setActivePage] = useState(1);
    const [totalPages, setTotalPages] = useState(5);
    const tablePagination = () => { 
        let items = [];
        for (let number = 1; number <= totalPages; number++) {
            items.push(
                <Pagination.Item 
                    className={`${number === activePage ? "activePage" : "paginationItem"}`} 
                    active={number === activePage} //THIS BREAKS THE CSS
                    key={number} 
                    onClick={() => onClickPagination(number)}>
                    {number}
                </Pagination.Item>
            );
        }
        return items;
    }
    const onClickPagination = (number) => {
        setActivePage(number);
    }

    const onPaginationNext = () => {
        if(activePage<totalPages){
            setActivePage(activePage+1);
        }
    }

    const onPaginationPrev = () => {
        if(activePage>1){
            setActivePage(activePage-1);
        }
    }

    return (
        <>
            <Table responsive striped hover className={id}>
                <thead>{tableHeader()}</thead>
                <tbody>{tableBody()}</tbody>
            </Table>
            <Pagination className={"pagination"}>
                <Pagination.Prev onClick={() => onPaginationPrev()} className={"paginationItem"}/>
                {tablePagination()}
                <Pagination.Next onClick={() => onPaginationNext()} className={"paginationItem"}/>
            </Pagination>
        </>
    )      
}

export default TeiaiTable;