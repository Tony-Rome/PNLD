const SUB_DIMENSION_BASE_URL = '/v1/capacitaciones/establecimientos/';

export function getInstitutionSubDimensionData(yearRange){

    var queryParams;

    if(typeof(yearRange) === "object"){
        queryParams = '?fromYear=' + yearRange['fromYear'] + '&toYear=' + yearRange['toYear'];
    }else{
        queryParams = '?fromYear=' + yearRange + '&toYear=' + yearRange;
    }

    const url = SUB_DIMENSION_BASE_URL + queryParams;
    const response = fetch(url, {method: 'GET'})
        .then( (resp) => resp.json() )
        .then( (data) => {
            return data;
        });
    return response;
};