import {transformRegionName, getPaletteColor, defineYearsQueryParams} from '../utils.js';
import {allRegion, allGender, allYear, getYearsSelected,
        getRegionsSelected, getGendersSelected, getAllRegionsName,
         yearList, SwitchGenderFilter, selectAllRegions, selectAllYears, selectAllGenders} from '../filter.js';
import {getSubDimensionSelected} from '../sub-dimension.js';
import {getInstitutionSubDimensionData, getTeacherSubDimensionData} from './api.js';
import {participantInstitutionNumber, firstTimeInstitutionPercentage} from './institution.js';
import {trainedTeacherNumber} from './teacher.js';

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

    var chartOption = parseInt(subDimensionSelected['chart']) //TODO: SOlo este se queda

    if(subDimensionSelected['id'] === 'institution')  selectCharByInstitution(chartOption); //TODO: Sacar yearSelected de acá y ponerlo en función del indicador

    if(subDimensionSelected['id'] === 'teacher')  selectChartByTeacher(chartOption);
}

function activateDefaultsFilters(option){ //TODO: Podria ser general(?)
    allYear.checked = false;
    selectAllYears(allYear)
    yearList[0].checked = true;
    allRegion.checked = true;
    selectAllRegions(allRegion);
    if(option.id === 'teacher'){ //TODO: Se podría agrega opción de estudiante
        allGender.checked = true;
        selectAllGenders(allGender);
    };
}

async function selectCharByInstitution(chartOption){

    var yearsSelected = getYearsSelected();
    var queryParams = defineYearsQueryParams(yearsSelected);
    var response = await getInstitutionSubDimensionData(queryParams);

    var dataRaw = response['trainingIndicatorData'];
    var data = transformRegionName(dataRaw);
    var dataList = dataListWithEmptyValues(data, yearsSelected);

    var labels = getRegionsSelected();

    if(chartOption === PARTICIPANT_INSTITUTION_NUMBER){
        participantInstitutionNumber(yearsSelected, dataList, labels);

    }
    if(chartOption === FIRST_TIME_INSTITUTION_PERCENTAGE){
       firstTimeInstitutionPercentage(yearsSelected, dataList, labels);
    }
    if(chartOption === PARTICIPANT_INSTITUTION_PERCENTAGE){
        participantInstitutionPercentage();
    }
}

async function selectChartByTeacher(chartOption){

     //TODO: Ver por qué se repite dos veces la función.

     var yearsSelected = getYearsSelected();
     var queryParams = defineYearsQueryParams(yearsSelected);
     var response = await getTeacherSubDimensionData(queryParams);

     var dataRaw = response['trainingTeacherIndicatorDTOList']; //TODO: Se podría refactorizar la respuesta
     var data = transformRegionName(dataRaw);
     var dataList = trainedTeacherWithEmptyValues(data, yearsSelected);

     var labels = getRegionsSelected();
     var gendersSelected = getGendersSelected();

    if(chartOption === TRAINED_TEACHER_NUMBER)
        trainedTeacherNumber(yearsSelected, gendersSelected, dataList, labels);

    if(chartOption === IN_PERSON_SESSION_PERCENTAGE);
    if(chartOption === PRE_TEST_COMPLETED_PERCENTAGE);
    if(chartOption === POST_TEST_COMPLETED_PERCENTAGE);
    if(chartOption === ONLINE_COURSE_COMPETED_PERCENTAGE);
    if(chartOption === TRAINING_COMPLETED_PERCENTAGE);
    if(chartOption === SCORE_DIFFERENCE_PRE_POST_TEST);
}



function dataListWithEmptyValues(dataList, yearRange){ //TODO: Cambiar nombre a sólo indicador instituciones con valores vacios
//TODO: Se podría hacer general y dependiendo del indicador rellenar con cierto objeto

    let regionList = getAllRegionsName();
    let regionId = dataList.map(data => data.id);

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