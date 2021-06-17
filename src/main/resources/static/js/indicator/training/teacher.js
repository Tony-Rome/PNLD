import {trainedTeacherNumberChart} from './chart.js';
import {getPaletteColor} from '../utils.js';
import {allRegion, allGender, allYear, getYearsSelected,
        getRegionsSelected, getGendersSelected, getAllRegionsName,
         yearList, SwitchGenderFilter, selectAllRegions, selectAllYears, selectAllGenders} from '../filter.js';
export function trainedTeacherNumber(yearsSelected, gendersSelected, dataList, labels ){

    var datasets = [];

    console.log(dataList); //TODO: Eliminar
    console.log("Annos seleciconados");
    console.log(yearsSelected);
    var dataLoop = { 'list': [] };
    if(yearsSelected.length != 0 && gendersSelected.length != 0)
        dataLoop = teacherDecisionLoop(yearsSelected, gendersSelected);
    console.log(dataLoop);
        //TODO: Verificar si filtros funcionan.
        //TODO: Refactorizar código.
     dataLoop['list'].forEach( (element, i) => {
        var paletteColor = getPaletteColor(i);
        var data = [];
        var filterGender = (dataLoop['filter']) ? element.toLowerCase() : dataLoop['data'];
        var filterYear = (dataLoop['filter']) ? dataLoop['data'] : element;

        dataList.forEach( (e,index) => {

            if(labels.includes(e.regionName)){
                let teacherData = e.trainingTeacherIndicatorDataByTeacherDTOList
                        .filter(data => {
                            if(data.year === filterYear && data.gender === filterGender && data.trainingState === true)
                                    return data.trainingState;
                            })
                data.push(teacherData.length);
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
    trainedTeacherNumberChart(labels, datasets, yearsSelected);
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