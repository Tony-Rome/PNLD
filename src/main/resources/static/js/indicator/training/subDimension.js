import {getChart} from './chart.js';

const SUB_DIMENSION_BASE_URL = '/v1/capacitaciones/establecimientos/';
const institutionList = document.getElementsByName('institution');

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


async function chartFunction() {

    let response = await getInfoRegion();
    let infoRegionsResponse = response['trainingInfoRegions'];

    let labels = [];
    let data = [];
    let backgroundColor = [];
    let borderColor = [];
    let datasets = [];
    let dataset = {};

    infoRegionsResponse.forEach( (element, index) => {

        let regionName = element['name'];
        let schoolNumber = element['numberInstitutionsInPNLD'];
        let randomColorDict = randomColorFunction();

        labels.push(regionName);

        data.push(schoolNumber);
        backgroundColor.push(randomColorDict['backgroundColor']);
        borderColor.push(randomColorDict['borderColor']);

    });

    dataset['data'] = data;
    dataset['backgroundColor'] = backgroundColor;
    dataset['borderColor'] = borderColor;

    datasets.push(dataset);

    getChart(labels, datasets);

};

institutionList.forEach((e,i) => {
    e.addEventListener('click', chartFunction);
});