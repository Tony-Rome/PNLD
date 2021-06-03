import {transformRegionName} from '../utils.js';
import {getChart} from './chart.js';
import {getInfoRegion} from './consume.js';

const institutionList = document.getElementsByName('institution');

const PARTICIPANT_INSTITUTION_NUMBER = 0;
const FIRST_TIME_INSTITUTION_PERCENTAGE = 1;
const PARTICIPANT_INSTITUTION_PERCENTAGE = 2;

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

    let currentYear = new Date().getFullYear();
    let response = await getInfoRegion(currentYear);
    let trainingInfoRegionsRaw = response['trainingInfoRegions'];

    let trainingInfoRegions = transformRegionName(trainingInfoRegionsRaw);

    let labels = [];
    let data = [];
    let backgroundColor = [];
    let borderColor = [];
    let datasets = [];
    let dataset = {};

    trainingInfoRegions.forEach( (element, index) => {

        let regionName = element['regionName'];
        let institutionNumber = element['institutionNumberPNLD'];
        let randomColorDict = randomColorFunction();

        labels.push(regionName);

        data.push(institutionNumber);
        backgroundColor.push(randomColorDict['backgroundColor']);
        borderColor.push(randomColorDict['borderColor']);

    });

    dataset['data'] = data;
    dataset['backgroundColor'] = backgroundColor;
    dataset['borderColor'] = borderColor;

    datasets.push(dataset);

    getChart(labels, datasets, currentYear);

}

function firstTimeInstitutionPercentage(){}

function participantInstitutionPercentage(){}

function chartFunction() {

    //TODO: Mejorar filtrado agregando nombre de subdimension (inst o doc).

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