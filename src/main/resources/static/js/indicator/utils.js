import {allRegion, allGender, allYear, selectAllRegions, selectAllYears,
        selectAllGenders, yearList, getYearsSelected, getGendersSelected, SwitchGenderFilter} from './filter.js';

const regionNameList = document.getElementsByName('region');

export function transformRegionName(data){
    var new_data = data.map(e => {
        e['regionName'] = regionNameList[e['id'] - 1].id;
        return e;
    });

    return new_data;
}

export function getPaletteColor(index){

    const COLOR_SOLID = 1;
    const COLOR_TRANSPARENT = 0.7;

    const BLUE = 'rgba(0, 63, 92,';
    const PURPLE = 'rgba(88, 80, 141,';
    const PINK = 'rgba(188,80,144,';
    const RED = 'rgba(255,99,97,';
    const YELLOW = 'rgba(255,166,0,';

    let bgBlue =  BLUE + COLOR_TRANSPARENT + ')';
    let bgPurple = PURPLE + COLOR_TRANSPARENT + ')';
    let bgPink = PINK + COLOR_TRANSPARENT + ')';
    let bgRed = RED + COLOR_TRANSPARENT + ')';
    let bgYellow = YELLOW + COLOR_TRANSPARENT + ')';

    const backgroundColors = [bgBlue, bgPurple, bgPink, bgRed, bgYellow];

    let borderBlue =  BLUE + COLOR_SOLID + ')';
    let borderPurple = PURPLE + COLOR_SOLID + ')';
    let borderPink = PINK + COLOR_SOLID + ')';
    let borderRed = RED + COLOR_SOLID + ')';
    let borderYellow = YELLOW + COLOR_SOLID + ')';

    const borderColors = [borderBlue, borderPurple, borderPink, borderRed, borderYellow];


    let paletteColor = {
        'backgroundColor': backgroundColors[index],
        'borderColor': borderColors[index]
    };

    return paletteColor;
};

export function defineYearsQueryParams(yearsSelected){
    var selected = {};

    if(yearsSelected.length === 0) {
        selected['year'] = new Date().getFullYear();
        return selected;
    }

    if(yearsSelected.length === 1) {
        selected['year'] = yearsSelected[0];
        return selected;
    }

    if(yearsSelected.length >= 2){
        selected['fromYear'] = Math.min(...yearsSelected);
        selected['toYear'] = Math.max(...yearsSelected);
        return selected;
    }

}

export function activateDefaultsFilters(option){
    allYear.checked = false;
    selectAllYears(allYear)
    yearList[0].checked = true;
    allRegion.checked = true;
    selectAllRegions(allRegion);
    if(option.id === 'teacher' || option.id === 'student'){
        allGender.checked = true;
        selectAllGenders(allGender);
    };
}

function getDataByLoop(option, yearsSelected, gendersSelected){
    if(option === true){
        return { 'list': gendersSelected, 'data': yearsSelected[0], 'filter': true };
    }
    if(option === false){
        return {'list': yearsSelected, 'data': gendersSelected[0].toLowerCase(), 'filter': false};
    }
}

export function teacherDecisionLoop(yearsSelected){

    var dataLoop = { 'list': [] , 'data' : null};

    if(yearsSelected.length!= 0){
        var option = (yearsSelected.length === 1) ? true : false;
        SwitchGenderFilter(option);

        var gendersSelected = getGendersSelected();
        if(gendersSelected != 0) dataLoop = getDataByLoop(option, yearsSelected, gendersSelected);
    }
    return dataLoop;
}