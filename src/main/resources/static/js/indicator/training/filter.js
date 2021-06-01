getYearsSelected = () => {
    const yearsFilters = document.getElementsByName('year');
    var yearsSelected = [];
    yearsFilters.forEach( (e, i) => {
        yearsSelected.push(yearsFilters[i].checked);
    })

    console.log(yearsSelected);
};

getRegionsSelected = () => {
    const regionsFilter = document.getElementsByName('region');
    var regionSelected = [];
    regionsFilter.forEach( (e, i) => {
        regionSelected.push(regionsFilter[i].checked);
    })

    console.log(regionSelected);
};

getGendersSelected = () => {
    const gendersFilter = document.getElementsByName('gender');
    var gendersSelected = [];
    gendersFilter.forEach( (e, i) => {
        gendersSelected.push(gendersFilter[i].checked);
    })

    console.log(gendersSelected);
};