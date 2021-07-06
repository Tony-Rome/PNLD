import {trainedTeacherCounterChart, teacherInPersonSessionPercentageChart,
        teacherPretestCompletedPercentageChart, teacherPostTestCompletedPercentageChart} from './chart.js';
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
                var approvedTrainingCounter = [0];
                if(e.trainingIndicatorDataList != undefined && e.trainingIndicatorDataList.length != 0){
                    let trainingIndicatorDataList = e.trainingIndicatorDataList.filter(data => data.year === filterYear);
                    if(trainingIndicatorDataList != undefined && trainingIndicatorDataList.length != 0){
                        approvedTrainingCounter = trainingIndicatorDataList[0].dataByGenderList.map(data => {
                                if(data.gender === filterGender) return data.approvedTrainingCounter;
                            }).filter(Boolean);
                    }
                }
                data.push(approvedTrainingCounter[0]);
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
                var inPersonSessionPercentage = [0];
                var dataByGender = [];
                if(e.trainingIndicatorDataList != undefined && e.trainingIndicatorDataList.length != 0){
                    let trainingIndicatorDataList = e.trainingIndicatorDataList.filter(data => data.year === filterYear);
                    if(trainingIndicatorDataList != undefined && trainingIndicatorDataList.length != 0){
                        inPersonSessionPercentage = trainingIndicatorDataList[0].dataByGenderList.map(data => {
                                if(data.gender === filterGender){
                                    let totalAssistance = data.notAssistanceCounter + data.assistanceCounter;
                                    let assistancePercentage = (data.assistanceCounter / totalAssistance) * 100;
                                    return assistancePercentage.toFixed(DECIMAL_NUMBER);
                                }
                            }).filter(Boolean);
                    }
                }

                data.push(inPersonSessionPercentage[0]);
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
    console.log(dataList);
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
                console.log(completedPercentage);
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
    teacherPostTestCompletedPercentageChart(labels, datasets, yearsSelected, dataLoop['data'], dataList);
}


//TODO: funcion general de porcentaje podria estar en otro arcvhio(?)
function getDataByParameter(list, filterParameter){
    if(list === undefined) return {};

    var data = list.find(data =>{
            if('year' in data) return data.year === filterParameter
            if('gender' in data) return data.gender === filterParameter;
        });

    return (data != undefined) ? data : {};
}

//TODO: funcion general de porcentaje podria estar en otro arcvhio(?)
function calculatePercentage(favourableCase, notFavourableCase){
    if(favourableCase === undefined || notFavourableCase === undefined) return 0;

    var total = favourableCase + notFavourableCase;
    if(total === 0) return total.toFixed(DECIMAL_NUMBER);

    return ((favourableCase / total) * 100).toFixed(DECIMAL_NUMBER);
}