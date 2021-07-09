import {transformRegionName, defineYearsQueryParams, activateDefaultsFilters} from '../utils.js';
import { getYearsSelected, getRegionsSelected, getAllRegionsName } from '../filter.js';
import {getSubDimensionSelected} from '../sub-dimension.js';
import {getInstitutionSubDimensionData, getTeacherSubDimensionData} from './api.js';
import {participantInstitutionCounter, firstTimeInstitutionPercentage} from './institution-setup.js';
import {trainedTeacherCounter, teacherInPersonSessionPercentage,
        teacherPretestCompletedPercentage, teacherPostTestCompletedPercentage,
        teacherTrainingCompletedPercentage} from './teacher-setup.js';

const PARTICIPANT_INSTITUTION_NUMBER = 0;
const FIRST_TIME_INSTITUTION_PERCENTAGE = 1;
const PARTICIPANT_INSTITUTION_PERCENTAGE = 2;

const TRAINED_TEACHER_NUMBER = 0;
const IN_PERSON_SESSION_PERCENTAGE= 1;
const PRE_TEST_COMPLETED_PERCENTAGE = 2;
const POST_TEST_COMPLETED_PERCENTAGE = 3;
const TRAINING_COMPLETED_PERCENTAGE = 4;
const SCORE_DIFFERENCE_PRE_POST_TEST = 5;

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
        participantInstitutionCounter(yearsSelected, dataList, labels);

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

    if(chartOption === TRAINED_TEACHER_NUMBER)
        trainedTeacherCounter(yearsSelected, dataList, labels);

    if(chartOption === IN_PERSON_SESSION_PERCENTAGE)
        teacherInPersonSessionPercentage(yearsSelected, dataList, labels);

    if(chartOption === PRE_TEST_COMPLETED_PERCENTAGE)
        teacherPretestCompletedPercentage(yearsSelected, dataList, labels);

    if(chartOption === POST_TEST_COMPLETED_PERCENTAGE)
        teacherPostTestCompletedPercentage(yearsSelected, dataList, labels);

    if(chartOption === TRAINING_COMPLETED_PERCENTAGE)
        teacherTrainingCompletedPercentage(yearsSelected, dataList, labels);
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