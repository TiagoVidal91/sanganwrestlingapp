import axios from "axios";

const BASE_URL = "http://localhost:8080";

export function getHttpHeaders() {
	let init = {
		headers: {
			Accept: "application/json",
			"Content-Type": "application/json;",
		},
	};
	return init;
}

export async function getWrestlers(
	//orderBy = null,
	pageNumber = 1,
	pageSize = 10
) {
	let url = `${BASE_URL}/teiai-api/wrestler/findAllWrestlersByParam`;

	let gotFilterInformationOnUrl = false;
	// if (orderBy !== null && orderBy !== "") {
	// 	url += `${gotFilterInformationOnUrl ? "&" : "?"}OrderBy=${orderBy}`;
    //  gotFilterInformationOnUrl = true;
	// }
	if (pageNumber !== null && pageNumber !== "") {
		url += `${gotFilterInformationOnUrl ? "&" : "?"}PageNumber=${pageNumber}`;
		gotFilterInformationOnUrl = true;
	}
	if (pageSize !== null && pageSize !== "") {
		url += `${gotFilterInformationOnUrl ? "&" : "?"}PageSize=${pageSize}`;
	}
    
	let config = getHttpHeaders();
	return await axios.get(url, config);
}