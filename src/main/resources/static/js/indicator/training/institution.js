import {participantInstitutionCounterChart, firstTimeInstitutionPercentageChart} from './chart.js';
import {getPaletteColor} from '../utils.js';


export function participantInstitutionCounter(yearsSelected, dataList, labels) {

    var datasets = [];

    yearsSelected.forEach( (year, i) => {

        var paletteColor = getPaletteColor(i);
        var data = [];

        dataList.forEach( (e,index) => {

            if(labels.includes(e.regionName)){
                var institutionCounterData = [0];
                if(e.trainingIndicatorDataList != undefined && e.trainingIndicatorDataList.length != 0){
                    institutionCounterData = e.trainingIndicatorDataList
                        .filter(data => data.year === year)
                        .map( data => data.institutionCounterPNLD);
                }
                data.push(institutionCounterData[0]);

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
    participantInstitutionCounterChart(labels, datasets, yearsSelected);

}

export function firstTimeInstitutionPercentage(yearsSelected, dataList, labels){

    var datasets = [];
    const DECIMAL_NUMBER = 2;

    yearsSelected.forEach( (year, i) => {

        var paletteColor = getPaletteColor(i);
        var data = [];

        dataList.forEach( (e,index) => {

            if(labels.includes(e.regionName)){
                var percentageFirstTimeInstitution = [0];
                if(e.trainingIndicatorDataList != undefined && e.trainingIndicatorDataList.length != 0){
                    percentageFirstTimeInstitution = e.trainingIndicatorDataList
                        .filter(data => data.year === year)
                        .map( data => {
                            let totalInstitution = data.institutionCounterPNLD;
                            let firstTimeCounter = data.firstTimeInstitutionCounter;
                            let percentage = (firstTimeCounter/totalInstitution)*100;
                            return percentage.toFixed(DECIMAL_NUMBER);
                            });
                }
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

    firstTimeInstitutionPercentageChart(labels, datasets, yearsSelected, dataList);
}