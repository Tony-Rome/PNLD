import {participantInstitutionNumberChart, firstTimeInstitutionPercentageChart} from './chart.js';
import {getPaletteColor} from '../utils.js';


export function participantInstitutionNumber(yearsSelected, dataList, labels) {

    var datasets = [];

    yearsSelected.forEach( (year, i) => {

        var paletteColor = getPaletteColor(i);
        var data = [];

        dataList.forEach( (e,index) => {

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
    participantInstitutionNumberChart(labels, datasets, yearsSelected);

}

export function firstTimeInstitutionPercentage(yearsSelected, dataList, labels){

    var datasets = [];
    const DECIMAL_NUMBER = 2;

    yearsSelected.forEach( (year, i) => {

        var paletteColor = getPaletteColor(i);
        var data = [];

        dataList.forEach( (e,index) => {

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

    firstTimeInstitutionPercentageChart(labels, datasets, yearsSelected, dataList);
}