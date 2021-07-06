import {trainedTeacherCounterChart, teacherInPersonSessionPercentageChart,
        teacherPretestCompletedPercentageChart} from './chart.js';
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
                try{
                    var trainingIndicatorDataList = e.trainingIndicatorDataList.find(data => data.year === filterYear);
                    var dataByGender = trainingIndicatorDataList.dataByGenderList.find(data => data.gender === filterGender);
                    var totalCompleted = dataByGender.preTestCompletedCounter + dataByGender.preTestNotCompletedCounter;
                    var completedPercentage = ((dataByGender.preTestCompletedCounter / totalCompleted) * 100).toFixed(DECIMAL_NUMBER);
                    data.push(completedPercentage);
                }catch (error){
                    console.error(error);
                    data.push(0);
                }
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