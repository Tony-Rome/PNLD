import {transformRegionName, getPaletteColor, defineYearsQueryParams} from '../utils.js';
import {getYearsSelected, getRegionsSelected, getGendersSelected, getAllRegionsName, yearList} from '../filter.js';
import {getSubDimensionSelected} from '../sub-dimension.js';
import {selectAllRegions, allRegion} from './filter.js';
import {getInstitutionSubDimensionData, getTeacherSubDimensionData} from './api.js';
import {getNumberBarChart, getPercentageBarChart} from './chart.js';

const PARTICIPANT_INSTITUTION_NUMBER = 0;
const FIRST_TIME_INSTITUTION_PERCENTAGE = 1;
const PARTICIPANT_INSTITUTION_PERCENTAGE = 2;

const TRAINED_TEACHER_NUMBER = 0;
const IN_PERSON_SESSION_PERCENTAGE= 1;
const PRE_TEST_COMPLETED_PERCENTAGE = 2;
const POST_TEST_COMPLETED_PERCENTAGE = 3;
const ONLINE_COURSE_COMPETED_PERCENTAGE = 4;
const TRAINING_COMPLETED_PERCENTAGE = 5;
const SCORE_DIFFERENCE_PRE_POST_TEST = 6;

export function selectChart(){

    if(this && this.type === "radio") activeDefaultOptions();
    console.log(this);
    let subDimensionSelected = getSubDimensionSelected();

    var chartOption = parseInt(subDimensionSelected['chart'])

    var yearsSelected = getYearsSelected();

    var queryParams = defineYearsQueryParams(yearsSelected);

    if(subDimensionSelected['id'] === 'institution')  selectCharByInstitution(chartOption, yearsSelected, queryParams);

    if(subDimensionSelected['id'] === 'teacher')  selectChartByTeacher(chartOption, yearsSelected, queryParams);
}

function activeDefaultOptions(){ //TODO: Podria ser general(?)
    yearList[0].checked = true;
    allRegion.checked = false;
    allRegion.click();
}

async function selectCharByInstitution(chartOption, yearsSelected, queryParams){

    var response = await getInstitutionSubDimensionData(queryParams);
    var dataRaw = response['trainingIndicatorData'];
    var data = transformRegionName(dataRaw);
    console.log(data);
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

async function selectChartByTeacher(chartOption, yearsSelected, queryParams){

    var response = await getTeacherSubDimensionData(queryParams);
     //TODO: Ver por qué se repite dos veces la función.

    if(chartOption === TRAINED_TEACHER_NUMBER){
        console.log(response);
        console.log('Chart option' + chartOption);
    }
    if(chartOption === IN_PERSON_SESSION_PERCENTAGE);
    if(chartOption === PRE_TEST_COMPLETED_PERCENTAGE);
    if(chartOption === POST_TEST_COMPLETED_PERCENTAGE);
    if(chartOption === ONLINE_COURSE_COMPETED_PERCENTAGE);
    if(chartOption === TRAINING_COMPLETED_PERCENTAGE);
    if(chartOption === SCORE_DIFFERENCE_PRE_POST_TEST);
}

function participantInstitutionNumber(dataList, yearRange, title) {

    var datasets = [];

    var dataListFully = dataListWithEmptyValues(dataList, yearRange);

    yearRange.forEach( (year, i) => {

        var paletteColor = getPaletteColor(i);
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
            'backgroundColor': paletteColor['backgroundColor'],
            'borderColor': paletteColor['borderColor']
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

function firstTimeInstitutionPercentage(dataList, yearRange, title){

    var datasets = [];
    const DECIMAL_NUMBER = 2;
    var dataListFully = dataListWithEmptyValues(dataList, yearRange);

    yearRange.forEach( (year, i) => {

        var paletteColor = getPaletteColor(i);
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
            'backgroundColor': paletteColor['backgroundColor'],
            'borderColor': paletteColor['borderColor']
        };

        datasets.push(dataset);
    });

    let labels = getRegionsSelected();

    getPercentageBarChart(labels, datasets, title, dataListFully);
}

function participantInstitutionPercentage(){}


//TODO: Funciones para subdimension docentes capacitaciones:

function trainedTeacherNumber(){
}