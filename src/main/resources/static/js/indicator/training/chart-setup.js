import {transformRegionName, randomColorFunction, defineYearsQueryParams} from '../utils.js';
import {getYearsSelected, getRegionsSelected, getGendersSelected, getAllRegionsName} from '../filter.js';
import {getSubDimensionSelected} from '../sub-dimension.js';
import {getInstitutionSubDimensionData} from './api.js';
import {getChart} from './chart.js';

const PARTICIPANT_INSTITUTION_NUMBER = 0;
const FIRST_TIME_INSTITUTION_PERCENTAGE = 1;
const PARTICIPANT_INSTITUTION_PERCENTAGE = 2;

//TODO: Agregar constantes subdimensiones con docentes

export function selectChart(){

    let subDimensionSelected = getSubDimensionSelected();

    var chartOption = parseInt(subDimensionSelected['chart'])

    var yearsSelected = getYearsSelected();
    var queryParams = defineYearsQueryParams(yearsSelected);

    if(subDimensionSelected['name'] === 'institution')  selectCharByInstitution(chartOption, yearsSelected, queryParams);

    if(subDimensionSelected['name'] === 'teacher'){
        //TODO: filtro según valor de chart
        selectChartByTeacher();
    }
}

async function selectCharByInstitution(chartOption, yearsSelected, queryParams){

    var response = await getInstitutionSubDimensionData(queryParams);
    var dataRaw = response['trainingIndicatorData'];
    var data = transformRegionName(dataRaw);

    if(chartOption === PARTICIPANT_INSTITUTION_NUMBER){
        participantInstitutionNumber(data, yearsSelected, queryParams);

    }
    if(chartOption === FIRST_TIME_INSTITUTION_PERCENTAGE){
       firstTimeInstitutionPercentage();
    }
    if(chartOption === PARTICIPANT_INSTITUTION_PERCENTAGE){
        participantInstitutionPercentage();
    }
}

function selectChartByTeacher(){
    //TODO: Agregar filtross segun valor de chartOption
}

function participantInstitutionNumber(dataList, yearRange, title) {

    var datasets = [];

    yearRange.forEach( (year, i) => {

        var randomColorDict = randomColorFunction();

        var dataByYear = dataList.filter(data => {
            if(data.year === year) return data;
        });

        var dataFully = dataListWithEmptyValues(dataByYear);

        var dataFilter = regionFilter(dataFully);

        var data = dataFilter.map(d => d.institutionNumberPNLD);

        let dataset = {
            'label': year,
            'data': data,
            'backgroundColor': randomColorDict['backgroundColor'],
            'borderColor': randomColorDict['borderColor']
        };

        datasets.push(dataset);
    });

    let labels = getRegionsSelected();
    getChart(labels, datasets, title);

}

function dataListWithEmptyValues(data){
    let regionList = getAllRegionsName();
    let institutionNumber = data.map(d => d.institutionNumberPNLD);
    let regionId = data.map(d => d.id);

    regionList.forEach( (e,i) => {
        if(!regionId.includes(i+1)){
            let newValue = {
                'regionName': e,
                'institutionNumberPNLD': 0
            }
            data.splice(i,0,newValue);
        }
    });

    return data;
}

function regionFilter(data){
    let regionList = getRegionsSelected();

    if(regionList.length === 0) return [];

    let dataFilter = []

    data.filter(d => {
        if(regionList.includes(d.regionName)) dataFilter.push(d);
    });

    return dataFilter;
}


function genderFilter(data){};

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