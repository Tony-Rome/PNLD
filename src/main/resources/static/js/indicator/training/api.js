const SUB_DIMENSION_BASE_URL = '/v1/training/institution/';
const TEACHER_BASE_URL = 'v1/training/teacher/';

export function getInstitutionSubDimensionData(yearRange){

    var queryParams = getQueryParams(yearRange);

    const url = SUB_DIMENSION_BASE_URL + queryParams;
    const response = fetch(url, {method: 'GET'})
        .then( (resp) => resp.json() )
        .then( (data) => {
            return data;
        });
    return response;
};

export function getTeacherSubDimensionData(yearRange){

    var queryParams = getQueryParams(yearRange);

    const url = TEACHER_BASE_URL + queryParams; 
    const response = fetch(url, {method: 'GET'})
        .then( (resp) => resp.json() )
        .then( (data) => {
            return data;
        });
    return response;
}

function getQueryParams(yearRange){
    var queryParams;

    if(Object.keys(yearRange).length === 2){
        queryParams = '?fromYear=' + yearRange['fromYear'] + '&toYear=' + yearRange['toYear'];
    }else{
        queryParams = '?fromYear=' + yearRange['year'] + '&toYear=' + yearRange['year'];
    }

    return queryParams
}