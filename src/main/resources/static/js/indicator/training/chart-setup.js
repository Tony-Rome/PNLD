import {transformRegionName, getPaletteColor, defineYearsQueryParams} from '../utils.js';
import {allRegion, allGender, allYear, getYearsSelected,
        getRegionsSelected, getGendersSelected, getAllRegionsName,
         yearList, SwitchGenderFilter, selectAllRegions, selectAllYears, selectAllGenders} from '../filter.js';
import {getSubDimensionSelected} from '../sub-dimension.js';
import {getInstitutionSubDimensionData, getTeacherSubDimensionData} from './api.js';
import {getNumberBarChart, getPercentageBarChart, getTrainedTeacherNumberChart} from './chart.js';

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

//TODO: Ordenar funciones, agrupar por indicador.

export function selectChart(){

    if(this && this.type === 'radio' && this.name === 'training') activateDefaultsFilters(this);

    let subDimensionSelected = getSubDimensionSelected();

    var chartOption = parseInt(subDimensionSelected['chart'])

    var yearsSelected = getYearsSelected(); //TODO: Esto se puede quitar y dejar dentro de las funciones charts

    var queryParams = defineYearsQueryParams(yearsSelected); //TODO: Sacar queryparams de acá

    if(subDimensionSelected['id'] === 'institution')  selectCharByInstitution(chartOption, yearsSelected, queryParams); //TODO: Sacar yearSelected de acá y ponerlo en función del indicador

    if(subDimensionSelected['id'] === 'teacher')  selectChartByTeacher(chartOption);
}

function activateDefaultsFilters(option){ //TODO: Podria ser general(?)
    allYear.checked = true;
    allYear.click();
    yearList[0].checked = true;
    allRegion.checked = false;
    allRegion.click();
    if(option.id === 'teacher'){ //TODO: Se podría agrega opción de estudiante
        allGender.checked = false;
        allGender.click();
    };
}

async function selectCharByInstitution(chartOption, yearsSelected, queryParams){

    var response = await getInstitutionSubDimensionData(queryParams); //TODO: sacar de aquí, colocar en función chart
    var dataRaw = response['trainingIndicatorData']; //TODO: sacar de aquí, colocar en función chart
    var data = transformRegionName(dataRaw); //TODO: sacar de aquí, colocar en función chart

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

function selectChartByTeacher(chartOption){

     //TODO: Ver por qué se repite dos veces la función.
    if(chartOption === TRAINED_TEACHER_NUMBER){
        trainedTeacherNumber();
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

    var dataListFully = dataListWithEmptyValues(dataList, yearRange); //TODO: Hacer erste paso antes de la func

    var labels = getRegionsSelected(); //TODO: Hacer erste paso antes de la func

    yearRange.forEach( (year, i) => {

        var paletteColor = getPaletteColor(i);
        var data = [];

        dataListFully.forEach( (e,index) => {

            if(labels.includes(e.regionName)){
                let institutionNumberData = e.trainingInstitutionDataByYearDTOList
                        .filter(data => data.year === year)
                        .map( data => data.institutionNumberPNLD);

                data.push(institutionNumberData[0]);

            }

        });

        let dataset = {
            'label': year,
            'data': data,
            'backgroundColor': paletteColor['backgroundColor'],
            'borderColor': paletteColor['borderColor']
        };

        datasets.push(dataset);
    });
    getNumberBarChart(labels, datasets, title);

}

function dataListWithEmptyValues(dataList, yearRange){ //TODO: Cambiar nombre a sólo indicador instituciones con valores vacios
//TODO: Se podría hacer general y dependiendo del indicador rellenar con cierto objeto

    let regionList = getAllRegionsName();
    let regionId = dataList.map(data => data.id);
    dtoListKey = Object.keys(dataList[0])[2];

    regionList.forEach( (region,i) => {
        if(!regionId.includes(i+1)){
            let newValue = {
                'id': i+1,
                'regionName': region,
                'trainingInstitutionDataByYearDTOList': [],
            }
            dataList.splice(i,0,newValue);
        }
    });

    return dataList;
}

function trainedTeacherWithEmptyValues(dataList, yearRange){ //TODO: Se podria solo rellenar con el campo id, nombre_region y con descripcion vacía.

    let regionList = getAllRegionsName();
    let regionId = dataList.map(data => data.id);
    let dtoListKey = Object.keys(dataList[0])[2];

    regionList.forEach( (region,i) => {
        if(!regionId.includes(i+1)){
            let newValue = {
                'id': i+1,
                'regionName': region,
                'trainingTeacherIndicatorDataByTeacherDTOList': [],
            }
            dataList.splice(i,0,newValue);
        }
    });

    return dataList;
}

function firstTimeInstitutionPercentage(dataList, yearRange, title){

    var datasets = [];
    const DECIMAL_NUMBER = 2;
    var dataListFully = dataListWithEmptyValues(dataList, yearRange);
    var labels = getRegionsSelected();

    yearRange.forEach( (year, i) => {

        var paletteColor = getPaletteColor(i);
        var data = [];

        dataListFully.forEach( (e,index) => {

            if(labels.includes(e.regionName)){
                let percentageFirstTimeInstitution = e.trainingInstitutionDataByYearDTOList
                    .filter(data => data.year === year)
                    .map( data => {
                        let totalInstitution = data.institutionNumberPNLD;
                        let firstTimeNumber = data.firstTimeInstitutionNumber;
                        let percentage = (firstTimeNumber/totalInstitution)*100;
                        return percentage.toFixed(DECIMAL_NUMBER);
                        });
                data.push(percentageFirstTimeInstitution[0]);
            }

        });

        let dataset = {
            'label': year,
            'data': data,
            'backgroundColor': paletteColor['backgroundColor'],
            'borderColor': paletteColor['borderColor']
        };

        datasets.push(dataset);
    });

    getPercentageBarChart(labels, datasets, title, dataListFully);
}

function participantInstitutionPercentage(){}

function teacherDecisionLoop(){
    var gendersSelected = getGendersSelected(); //TODO: Se recorre por género.
    var yearsSelected = getYearsSelected();

    if(yearsSelected.length === 1){
        SwitchGenderFilter(true);
        return { 'list': gendersSelected, 'data': yearsSelected[0], 'filter': true };
    }
    if(yearsSelected.length > 1){
        SwitchGenderFilter(false);
        return {'list': yearsSelected, 'data': gendersSelected[0].toLowerCase(), 'filter': false};
    }
}

//TODO: Funciones para subdimension docentes capacitaciones:

async function trainedTeacherNumber(){ //TODO: Dejar como ejeplo function para las anteriores para refactorizar

    var yearsSelected = getYearsSelected();

    var queryParams = defineYearsQueryParams(yearsSelected);
    var response = await getTeacherSubDimensionData(queryParams);
    var dataRaw = response['trainingTeacherIndicatorDTOList'];
    var data = transformRegionName(dataRaw);
    //TODO: Obten los años seleccionados, si length > 1 entonces cambia a radio los géneros y matén true el género con menor index.
    //TODO: Recorre por yearRange.forEach(...), caso contrario (length === 1) recorrer por gender.forEach(...) seleccionados.
    //TODO: Tal vez necesite dos funciones más, para recorrer por año y para recorrer por género.
    var datasets = [];
    var labels = getRegionsSelected();
    var dataList = trainedTeacherWithEmptyValues(data, yearsSelected);
    console.log(dataList);
    var gendersSelected = getGendersSelected(); //TODO: Se recorre por género.

    var dataLoop = teacherDecisionLoop();
        //TODO: Verificar si filtros funcionan.
        //TODO: Refactorizar código.
     dataLoop['list'].forEach( (element, i) => {
        console.log(element);
        console.log(dataLoop['data']);
        var paletteColor = getPaletteColor(i);
        var data = [];
        var filterElement = (dataLoop['filter']) ? element.toLowerCase() : element;
        dataList.forEach( (e,index) => {

            if(labels.includes(e.regionName)){
                let teacherData = e.trainingTeacherIndicatorDataByTeacherDTOList
                        .filter(data => data.year === (dataLoop['filter']) ? dataLoop['data'] : filterElement && data.gender === (dataLoop['filter']) ? data['data'] : filterElement && data.trainingState === true)
                        .map( data => data.trainingState);
                    data.push(teacherData.length);
            }
        });

        let dataset = {
            'label': element,
            'data': data,
            'backgroundColor': paletteColor['backgroundColor'],
            'borderColor': paletteColor['borderColor']
        };

        datasets.push(dataset);
    });
    getTrainedTeacherNumberChart(labels, datasets, yearsSelected);
}

