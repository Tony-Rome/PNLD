import {trainedTeacherCounterChart} from './chart.js';
import {getPaletteColor, teacherDecisionLoop} from '../utils.js';

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