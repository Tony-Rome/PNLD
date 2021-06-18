import {transformRegionName, defineYearsQueryParams, activateDefaultsFilters} from '../utils.js';
import { getYearsSelected, getRegionsSelected, getGendersSelected, getAllRegionsName } from '../filter.js';
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

export function selectChart(){

    if(this && this.type === 'radio' && this.name === 'training') activateDefaultsFilters(this);

    let subDimensionSelected = getSubDimensionSelected();

    var chartOption = parseInt(subDimensionSelected['chart'])

    if(subDimensionSelected['id'] === 'institution')  selectCharByInstitution(chartOption);

    if(subDimensionSelected['id'] === 'teacher')  selectChartByTeacher(chartOption);
}

async function selectCharByInstitution(chartOption){

    var yearsSelected = getYearsSelected();
    var queryParams = defineYearsQueryParams(yearsSelected);
    var response = await getInstitutionSubDimensionData(queryParams);

    var dataRaw = response['trainingIndicatorDTOList'];
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

     var yearsSelected = getYearsSelected();
     var queryParams = defineYearsQueryParams(yearsSelected);
     var response = await getTeacherSubDimensionData(queryParams);

     var dataRaw = response['trainingIndicatorDTOList'];
     var data = transformRegionName(dataRaw);
     var dataList = dataListWithEmptyValues(data, yearsSelected);

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

function dataListWithEmptyValues(dataList, yearRange){

    let regionList = getAllRegionsName();
    let regionId = dataList.map(data => data.id);

    regionList.forEach( (region,i) => {
        if(!regionId.includes(i+1)){
            let newValue = {
                'id': i+1,
                'regionName': region,
                'trainingIndicatorDataList': [],
            }
            dataList.splice(i,0,newValue);
        }
    });

    return dataList;
}