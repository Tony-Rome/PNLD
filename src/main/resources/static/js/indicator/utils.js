const regionNameList = document.getElementsByName('region');

export function transformRegionName(data){
    var new_data = data.map(e => {
        e['regionName'] = regionNameList[e['id'] - 1].id;
        return e;
    });

    return new_data;
}