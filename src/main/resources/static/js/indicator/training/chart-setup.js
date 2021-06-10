import {transformRegionName, randomColorFunction, defineYearsQueryParams} from '../utils.js';
import {getYearsSelected, getRegionsSelected, getGendersSelected, getAllRegionsName, yearList} from '../filter.js';
import {getSubDimensionSelected} from '../sub-dimension.js';
import {selectAllRegions, allRegion} from './filter.js';
import {getInstitutionSubDimensionData} from './api.js';
import {getNumberBarChart, getPercentageBarChart} from './chart.js';

const PARTICIPANT_INSTITUTION_NUMBER = 0;
const FIRST_TIME_INSTITUTION_PERCENTAGE = 1;
const PARTICIPANT_INSTITUTION_PERCENTAGE = 2;

//TODO: Agregar constantes subdimensiones con docentes

export function selectChart(){

    if(this && this.type === "radio") activeDefaultOptions();

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

function activeDefaultOptions(){
    yearList[0].checked = true;
    allRegion.checked = false;
    allRegion.click();
}

async function selectCharByInstitution(chartOption, yearsSelected, queryParams){

    var response = await getInstitutionSubDimensionData(queryParams);
    var dataRaw = response['trainingIndicatorData'];
    var data = transformRegionName(dataRaw);

    if(chartOption === PARTICIPANT_INSTITUTION_NUMBER){
        participantInstitutionNumber(data, yearsSelected, queryParams);

    }
    if(chartOption === FIRST_TIME_INSTITUTION_PERCENTAGE){
       firstTimeInstitutionPercentage(data, yearsSelected, queryParams);
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

    var dataListFully = dataListWithEmptyValues(dataList, yearRange);

    yearRange.forEach( (year, i) => {

        var randomColorDict = randomColorFunction();
        var data = [];

        dataListFully.forEach( (e,index) => {

            let institutionNumberData = e.trainingInstitutionDataByYearDTOList
                .filter(data => data.year === year)
                .map( data => data.institutionNumberPNLD);

            data.push(institutionNumberData[0]);


        });

        var dataFilter = regionFilter(dataListFully);

        let dataset = {
            'label': year,
            'data': data,
            'backgroundColor': randomColorDict['backgroundColor'],
            'borderColor': randomColorDict['borderColor']
        };

        datasets.push(dataset);
    });
    let labels = getRegionsSelected();
    getNumberBarChart(labels, datasets, title);

}

function dataListWithEmptyValues(dataList, yearRange){

    let regionList = getAllRegionsName();
    let regionId = dataList.map(data => data.id);


    regionList.forEach( (region,i) => {
        if(!regionId.includes(i+1)){
            var dataDescriptionList = [];
            yearRange.forEach( (year, index) => {
                let dataDescription = {
                    'institutionNumberPNLD': 0,
                    'percentageFirstTimeInPNLD': 0.0,
                    'percentageInstitutions': 0.0,
                    'year': year
                }
                dataDescriptionList.push(dataDescription);
            })
            let newValue = {
                'id': i+1,
                'regionName': region,
                'trainingInstitutionDataByYearDTOList': dataDescriptionList,
            }
            dataList.splice(i,0,newValue);
        }
    });

    return dataList;
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

function firstTimeInstitutionPercentage(dataList, yearRange, title){

    var datasets = [];
    const DECIMAL_NUMBER = 2;
    var dataListFully = dataListWithEmptyValues(dataList, yearRange);

    yearRange.forEach( (year, i) => {

        var randomColorDict = randomColorFunction();
        var data = [];

        dataListFully.forEach( (e,index) => {

            let percentageFirstTimeInstitution = e.trainingInstitutionDataByYearDTOList
                .filter(data => data.year === year)
                .map( data => {
                    let totalInstitution = data.institutionNumberPNLD;
                    let firstTimeNumber = data.firstTimeInstitutionNumber;
                    let percentage = (firstTimeNumber/totalInstitution)*100;
                    return percentage.toFixed(DECIMAL_NUMBER);
                    });

            data.push(percentageFirstTimeInstitution[0]);


        });

        var dataFilter = regionFilter(dataListFully);

        let dataset = {
            'label': year,
            'data': data,
            'backgroundColor': randomColorDict['backgroundColor'],
            'borderColor': randomColorDict['borderColor']
        };

        datasets.push(dataset);
    });

    let labels = getRegionsSelected();

    getPercentageBarChart(labels, datasets, title, dataListFully);
}

function participantInstitutionPercentage(){}