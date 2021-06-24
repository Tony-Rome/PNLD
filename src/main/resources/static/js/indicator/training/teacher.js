import {trainedTeacherNumberChart} from './chart.js';
import {getPaletteColor} from '../utils.js';
import { SwitchGenderFilter } from '../filter.js';

export function trainedTeacherNumber(yearsSelected, gendersSelected, dataList, labels ){

    var datasets = [];
    var dataLoop = { 'list': [] , 'data' : null};

    if(yearsSelected.length != 0 && gendersSelected.length != 0)
        dataLoop = teacherDecisionLoop(yearsSelected, gendersSelected);

     dataLoop['list'].forEach( (element, i) => {
        var paletteColor = getPaletteColor(i);
        var data = [];
        var filterGender = (dataLoop['filter']) ? element.toLowerCase() : dataLoop['data'];
        var filterYear = (dataLoop['filter']) ? dataLoop['data'] : element;

        dataList.forEach( (e,index) => {
            if(labels.includes(e.regionName)){
                let trainingIndicatorDataList = e.trainingIndicatorDataList.filter(data => data.year === filterYear);
                let approvedTrainingCounter = trainingIndicatorDataList[0].dataByGenderList.map(data => {
                    if(data.gender === filterGender) return data.approvedTrainingCounter;
                }).filter(Boolean);
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
    trainedTeacherNumberChart(labels, datasets, yearsSelected, dataLoop['data']);
}

function teacherDecisionLoop(yearsSelected, gendersSelected){

    if(yearsSelected.length === 1){
        SwitchGenderFilter(true);
        return { 'list': gendersSelected, 'data': yearsSelected[0], 'filter': true };
    }
    if(yearsSelected.length > 1){
        SwitchGenderFilter(false);
        return {'list': yearsSelected, 'data': gendersSelected[0].toLowerCase(), 'filter': false};
    }
}