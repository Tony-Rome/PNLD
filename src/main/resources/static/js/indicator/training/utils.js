import {transformRegionName, randomColorFunction, defineYearsQueryParams} from '../utils.js';
import {getYearsSelected, getRegionsSelected, getGendersSelected} from '../filter.js';
import {getSubDimensionSelected} from '../sub-dimension.js';
import {getInstitutionSubDimensionData} from './api.js';
import {getChart} from './chart.js';

const PARTICIPANT_INSTITUTION_NUMBER = 0;
const FIRST_TIME_INSTITUTION_PERCENTAGE = 1;
const PARTICIPANT_INSTITUTION_PERCENTAGE = 2;

//TODO: Agregar constantes subdimensiones con docentes

export async function selectChart(){

    let subDimensionSelected = getSubDimensionSelected();

    var chartOption = parseInt(subDimensionSelected['chart'])

    var yearsSelected = getYearsSelected();
    var queryParams = defineYearsQueryParams(yearsSelected);


    if(subDimensionSelected['name'] === 'institution'){ //TODO: Mover a funcion colegio

        var response = await getInstitutionSubDimensionData(queryParams);
        var dataRaw = response['trainingInfoRegions']; //TODO: Cambiar nombre parámetro
        var data = transformRegionName(dataRaw);

        if(chartOption === PARTICIPANT_INSTITUTION_NUMBER){

            participantInstitutionNumber(data, queryParams);

        }
        if(chartOption === FIRST_TIME_INSTITUTION_PERCENTAGE){
            console.log(1);
        }
        if(chartOption === PARTICIPANT_INSTITUTION_PERCENTAGE){
            console.log(2);
        }

    }
    if(subDimensionSelected['name'] === 'teacher'){ //TODO: Mover a funcion docente
        //TODO: filtro según valor de chart
    }
}

function selectCharByInstitution(){}
function selectChartByTeacher(){}

function participantInstitutionNumber(dataList, title) {

    let labels = [];
    let data = [];
    let backgroundColor = [];
    let borderColor = [];
    let datasets = [];
    let dataset = {};

    dataList.forEach( (element, index) => {

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

    getChart(labels, datasets, title);

}

function participantInstitutionNumberAux(dataList, title) {

    let labels = [];
    let data = [];
    let backgroundColor = [];
    let borderColor = [];
    let datasets = [];
    let dataset = {};

    dataList.forEach( (element, index) => {

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

    getChart(labels, datasets, title);

}

function firstTimeInstitutionPercentage(){}

function participantInstitutionPercentage(){}