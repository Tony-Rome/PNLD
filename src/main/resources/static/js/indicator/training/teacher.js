import {trainedTeacherCounterChart, teacherInPersonSessionPercentageChart,
        teacherPretestCompletedPercentageChart, teacherPostTestCompletedPercentageChart,
        teacherTrainingCompletedPercentageChart} from './chart.js';
import {getPaletteColor, teacherDecisionLoop} from '../utils.js';

const DECIMAL_NUMBER = 2;

export function trainedTeacherCounter(yearsSelected, dataList, labels ){
    var datasets = [];
    var dataLoop = teacherDecisionLoop(yearsSelected);

    dataLoop['list'].forEach( (element, i) => {
        var paletteColor = getPaletteColor(i);
        var data = [];
        var filterGender = (dataLoop['filter']) ? element.toLowerCase() : dataLoop['data'];
        var filterYear = (dataLoop['filter']) ? dataLoop['data'] : element;

        dataList.forEach( (e,index) => {
            if(labels.includes(e.regionName)){
                var trainingIndicatorDataList = getDataByParameter(e.trainingIndicatorDataList, filterYear);
                var dataByGender = getDataByParameter(trainingIndicatorDataList.dataByGenderList, filterGender);
                var approvedTrainingCounter = dataByGender.approvedTrainingCounter || 0;
                data.push(approvedTrainingCounter);
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
    trainedTeacherCounterChart(labels, datasets, yearsSelected, dataLoop['data']);
}

export function teacherInPersonSessionPercentage(yearsSelected, dataList, labels){
    var datasets = [];
    var dataLoop = teacherDecisionLoop(yearsSelected);

    dataLoop['list'].forEach( (element, i) => {
        var paletteColor = getPaletteColor(i);
        var data = [];
        var filterGender = (dataLoop['filter']) ? element.toLowerCase() : dataLoop['data'];
        var filterYear = (dataLoop['filter']) ? dataLoop['data'] : element;

        dataList.forEach( (e,index) => {
            if(labels.includes(e.regionName)){
                var trainingIndicatorDataList = getDataByParameter(e.trainingIndicatorDataList, filterYear);
                var dataByGender = getDataByParameter(trainingIndicatorDataList.dataByGenderList, filterGender);
                var inPersonSessionPercentage = calculatePercentage(dataByGender.notAssistanceCounter, dataByGender.assistanceCounter);
                data.push(inPersonSessionPercentage);
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

    teacherInPersonSessionPercentageChart(labels, datasets, yearsSelected, dataLoop['data'], dataList);
}

export function teacherPretestCompletedPercentage(yearsSelected, dataList, labels){
    var datasets = [];
    var dataLoop = teacherDecisionLoop(yearsSelected);

    dataLoop['list'].forEach( (element, i) => {
        var paletteColor = getPaletteColor(i);
        var data = [];
        var filterGender = (dataLoop['filter']) ? element.toLowerCase() : dataLoop['data'];
        var filterYear = (dataLoop['filter']) ? dataLoop['data'] : element;

        dataList.forEach( (e,index) => {
            if(labels.includes(e.regionName)){
                var trainingIndicatorDataList = getDataByParameter(e.trainingIndicatorDataList, filterYear);
                var dataByGender = getDataByParameter(trainingIndicatorDataList.dataByGenderList, filterGender);
                var completedPercentage = calculatePercentage(dataByGender.preTestCompletedCounter, dataByGender.preTestNotCompletedCounter);
                data.push(completedPercentage);
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
    teacherPretestCompletedPercentageChart(labels, datasets, yearsSelected, dataLoop['data'], dataList);
}

export function teacherPostTestCompletedPercentage(yearsSelected, dataList, labels){
    var datasets = [];
    var dataLoop = teacherDecisionLoop(yearsSelected);

    dataLoop['list'].forEach( (element, i) => {
        var paletteColor = getPaletteColor(i);
        var data = [];
        var filterGender = (dataLoop['filter']) ? element.toLowerCase() : dataLoop['data'];
        var filterYear = (dataLoop['filter']) ? dataLoop['data'] : element;

        dataList.forEach( (e,index) => {
            if(labels.includes(e.regionName)){
                var trainingIndicatorDataList = getDataByParameter(e.trainingIndicatorDataList, filterYear);
                var dataByGender = getDataByParameter(trainingIndicatorDataList.dataByGenderList, filterGender);
                var completedPercentage = calculatePercentage(dataByGender.postTestCompletedCounter, dataByGender.postTestNotCompletedCounter);
                data.push(completedPercentage);
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
    teacherPostTestCompletedPercentageChart(labels, datasets, dataLoop['title'], dataLoop['data'], dataList);
}

export function teacherTrainingCompletedPercentage(yearsSelected, dataList, labels){
    var datasets = [];
    var dataLoop = teacherDecisionLoop(yearsSelected);

    dataLoop['list'].forEach( (element, i) => {
        var paletteColor = getPaletteColor(i);
        var data = [];
        var filterGender = (dataLoop['filter']) ? element.toLowerCase() : dataLoop['data'];
        var filterYear = (dataLoop['filter']) ? dataLoop['data'] : element;

        dataList.forEach( (e,index) => {
            if(labels.includes(e.regionName)){
                var trainingIndicatorDataList = getDataByParameter(e.trainingIndicatorDataList, filterYear);
                var dataByGender = getDataByParameter(trainingIndicatorDataList.dataByGenderList, filterGender);
                var completedPercentage = calculatePercentage(dataByGender.postTestCompletedCounter, dataByGender.postTestNotCompletedCounter);
                data.push(completedPercentage);
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
    teacherTrainingCompletedPercentageChart(labels, datasets, dataLoop['title'], dataLoop['data'], dataList);
}

function getDataByParameter(list, filterParameter){
    if(list === undefined) return {};

    var data = list.find(data =>{
            if('year' in data) return data.year === filterParameter
            if('gender' in data) return data.gender === filterParameter;
        });
    return data || {};
}

function calculatePercentage(favourableCase, notFavourableCase){
    var notValue = 0;
    if(favourableCase === undefined || notFavourableCase === undefined) return notValue.toFixed(DECIMAL_NUMBER);

    var total = favourableCase + notFavourableCase;
    if(total === 0) return total.toFixed(DECIMAL_NUMBER);

    return ((favourableCase / total) * 100).toFixed(DECIMAL_NUMBER);
}