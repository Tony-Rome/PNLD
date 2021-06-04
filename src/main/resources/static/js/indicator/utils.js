const regionNameList = document.getElementsByName('region');

export function transformRegionName(data){
    console.log("tranform: "+data);
    var new_data = data.map(e => {
        e['regionName'] = regionNameList[e['id'] - 1].id;
        return e;
    });

    return new_data;
}

export function randomColorFunction(){
    let r = Math.floor(Math.random() * 255);
    let g = Math.floor(Math.random() * 255);
    let b = Math.floor(Math.random() * 255);

    const COLOR_SOLID = 1;
    const COLOR_TRANSPARENT = 0.2;

    let randomColorDict = {};
    randomColorDict['backgroundColor'] = "rgba(" + r + "," + g + "," + b + "," + COLOR_TRANSPARENT + ")"
    randomColorDict['borderColor'] = "rgba(" + r + "," + g + "," + b + "," + COLOR_SOLID + ")"

    return randomColorDict;
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