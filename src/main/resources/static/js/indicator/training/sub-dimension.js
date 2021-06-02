import {getChart} from './chart.js';

const SUB_DIMENSION_BASE_URL = '/v1/capacitaciones/establecimientos/';
const institutionList = document.getElementsByName('institution');

const PARTICIPANT_INSTITUTION_NUMBER = 0;
const FIRST_TIME_INSTITUTION_PERCENTAGE = 1;
const PARTICIPANT_INSTITUTION_PERCENTAGE = 2;

function getInfoRegion(){

    let fromYearParam = 2021;
    let toYearParam = 2021;
    const url = SUB_DIMENSION_BASE_URL + '?fromYear=' + fromYearParam + '&toYear=' + toYearParam;
    const response = fetch(url, {method: 'GET'})
        .then( (resp) => resp.json() )
        .then( (data) => {
            return data;
        });
    return response;
};

function randomColorFunction(){
    let r = Math.floor(Math.random() * 255);
    let g = Math.floor(Math.random() * 255);
    let b = Math.floor(Math.random() * 255);

    const COLOR_SOLID = 1;
    const COLOR_TRANSPARENT = 0.2;

    let randomColorDict = {};
    randomColorDict['backgroundColor'] = "rgba(" + r + "," + g + "," + b + "," + COLOR_TRANSPARENT + ")"
    randomColorDict['borderColor'] = "rgba(" + r + "," + g + "," + b + "," + COLOR_SOLID + ")"

    return randomColorDict;
};

async function participantInstitutionNumber() {
    let response = await getInfoRegion();
    let infoRegionsResponse = response['trainingInfoRegions'];

    let labels = [];
    let data = [];
    let backgroundColor = [];
    let borderColor = [];
    let datasets = [];
    let dataset = {};

    for(let i = 0 ; i <8; i++){
        infoRegionsResponse.forEach( (element, index) => {

            let regionName = element['name'];
            let schoolNumber = element['numberInstitutionsInPNLD'];
            let randomColorDict = randomColorFunction();

            labels.push(regionName);

            data.push(schoolNumber);
            backgroundColor.push(randomColorDict['backgroundColor']);
            borderColor.push(randomColorDict['borderColor']);

        });

    }
    dataset['data'] = data;
    dataset['backgroundColor'] = backgroundColor;
    dataset['borderColor'] = borderColor;

    datasets.push(dataset);

    getChart(labels, datasets);

}

function firstTimeInstitutionPercentage(){}

function participantInstitutionPercentage(){}

function chartFunction() {

    switch(parseInt(this.value)){
        case PARTICIPANT_INSTITUTION_NUMBER:
            participantInstitutionNumber();
            break;
        case FIRST_TIME_INSTITUTION_PERCENTAGE:
            firstTimeInstitutionPercentage;
            break;
        case PARTICIPANT_INSTITUTION_PERCENTAGE:
            participantInstitutionPercentage;
            break;
    }
}

institutionList.forEach((e,i) => {
    e.addEventListener('click', chartFunction);
});