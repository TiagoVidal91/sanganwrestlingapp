import React, { useState, useEffect } from "react";
import "./TeiaiTable.css" 
import "bootstrap-icons/font/bootstrap-icons.css";


const TeiaiTable = ({tableData, tableHeaders, objectFields, numberPages, onOrderChange}) => {
    //TABLE HEADERS
    const tableHeader = () => {
        return (
        <tr data-id={0}>  
            {tableHeaders.map((header, index) => {
            return (  
                <th 
                    key={header+index} 
                    className={``}
                    onClick={(e) => onOrderIconClick(e, objectFields[index])}
                >
                    <div className={`tableHeader`}>
                        <div className={`headerText`}>{header}</div>
                        <i className={`paginationIcons ${orderBy.column===objectFields[index]? orderBy.order === "ASC" ? "bi bi-arrow-up" : "bi bi-arrow-down" : "bi bi-arrow-down-up"}`}></i>
                    </div>
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
                <tr data-id={index} key={index} className={`tableBody`}>
                    {cells}
                </tr>
            );
        });
    }

    //TABLE PAGINATION
    const [activePage, setActivePage] = useState(1);
    const [paginationState, setPaginationState] = useState({left: false, right: false});
    const pagesRow = () => { 
        let items = [];
        for (let number = 1; number <= numberPages; number++) {
             items.push(
                <h6
                    className={`paginationItem ${number === activePage ? "activePage" : ""}`} 
                    key={number} 
                    onClick={() => setActivePage(number)}
                >
                    {number}
                </h6>
            );
        }
        return (
            <div className={`pagesRow`}>
                {items}
            </div>
        );
    }

    const onPaginationNext = () => {
        if(activePage<numberPages){
            setActivePage(activePage+1);
        }
    }

    const onPaginationPrev = () => {
        if(activePage>1){
            setActivePage(activePage-1);
        }
    }

    const tablePagination = () => {
        return (
            <div className="paginationRow">
                <h6 className="paginationText">
                    Page {activePage}/{numberPages}
                </h6>
                {pagesRow()}
                <div className="paginationArrows">
                    <i
                        className={`paginationArrows bi bi-arrow-left ${paginationState.left? "disabledArrow" : ""} ` }
                        onClick={() => onPaginationPrev(activePage)}
                    ></i>
                    <i
                        className={`paginationArrows bi bi-arrow-right ${paginationState.right? "disabledArrow" : ""} ` }
                        onClick={() => onPaginationNext(activePage)}
                    ></i>
                </div>
            </div>
        )
    }

    useEffect(() => {
		let left = false;
		let right = false;
		if (activePage == 1) {
			left = true;
		}
		if (activePage == numberPages) {
			right = true;
		}
		setPaginationState({ left: left, right: right });
	}, [activePage, numberPages]);

    //COLUMN ORDER
    const [orderBy, setOrderBy] = useState({ order: "", column: "" });

    const onOrderIconClick = (e, header) => {
		e.preventDefault();
        let newOrder;
		if (orderBy.order === "" || orderBy.order === "DESC") {
			newOrder = "ASC";
		} else {
            newOrder = "DESC";
        }
		setOrderBy({ order: newOrder, column: header });
		onOrderChange(e, newOrder, header);
	};

    return (
        <>
            <table className={`teiaiTable`}>
                <thead>{tableHeader()}</thead>
                <tbody>{tableBody()}</tbody>
            </table>
            {tablePagination()}
        </>
    )      
}

export default TeiaiTable;