import React, { useState, useEffect } from "react";
import { Table, Pagination } from 'react-bootstrap'; 
import "./TeiaiTable.css" 
import "bootstrap-icons/font/bootstrap-icons.css";


const TeiaiTable = ({tableData, tableHeaders, objectFields, id, numberPages, onOrderChange, fullWidth}) => {
    //TABLE HEADERS
    const tableHeader = () => {
        return (
        <tr data-id={0}>  
            {tableHeaders.map((header, index) => {
            return (  
                <th key={header+index} onClick={(e) => onOrderIconClick(e, objectFields[index])}>
                    {header}
                    <i className={`paginationIcons ${orderBy.column===objectFields[index]? orderBy.order === "asc" ? "bi bi-arrow-up" : "bi bi-arrow-down" : "bi bi-arrow-down-up"}`}></i>
                </th>
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
    const [totalPages, setTotalPages] = useState(numberPages);
    const [paginationState, setPaginationState] = useState({left: false, right: false});
    const tablePagination = () => { 
        let items = [];
        for (let number = 1; number <= totalPages; number++) {
            items.push(
                <Pagination.Item 
                    className={`${number === activePage ? "activePage" : "paginationItem"}`} 
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

    useEffect(() => {
		let left = false;
		let right = false;
		if (activePage > 1 && activePage <= totalPages) {
			left = true;
		}
		if (activePage >= 1 && activePage < totalPages) {
			right = true;
		}
		setPaginationState({ left: left, right: right });
	}, [activePage, totalPages]);

    //COLUMN ORDER
    const [orderBy, setOrderBy] = useState({ order: "", column: "" });

    const onOrderIconClick = (e, header) => {
		e.preventDefault();
        let newOrder;
		if (orderBy.order === "" || orderBy.order === "desc") {
			newOrder = "asc";
		} else {
            newOrder = "desc";
        }
		setOrderBy({ order: newOrder, column: header });
		onOrderChange(e, newOrder, header);
	};


    return (
        <div className={fullWidth? "teiaiTableWidth" : "teiaiTableFullWidth"}>
            <Table responsive striped hover className={`teiaiTable ${id}`}>
                <thead>{tableHeader()}</thead>
                <tbody>{tableBody()}</tbody>
            </Table>
            <Pagination className={"teiaiTablePagination"}>
                <Pagination.Prev onClick={() => onPaginationPrev()} className={"teiaiTablePaginationItem"} disabled={paginationState.right}/>
                {tablePagination()}
                <Pagination.Next onClick={() => onPaginationNext()} className={"teiaiTablePaginationItem"} disabled={paginationState.left}/>
            </Pagination>
        </div>
    )      
}

export default TeiaiTable;