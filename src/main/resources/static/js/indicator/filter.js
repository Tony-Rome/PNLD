const filterBock = document.querySelectorAll('.filterBlock');
const filterTitle = document.querySelectorAll('.filterTitle');

export const yearList = document.getElementsByName('year');
const regionList = document.getElementsByName('region');
const genderList = document.getElementsByName('gender');

filterTitle.forEach( (t, i) => {
    filterTitle[i].addEventListener('click', ()=>{
        filterBock.forEach( (b, i) =>{
            filterBock[i].classList.remove('filterActive')
        filterBock[i].classList.add('filterInactive')
        })
        filterBock[i].classList.add('filterActive')
        filterBock[i].classList.remove('filterInactive')
    })
});

export function getYearsSelected() {
    var yearsSelected = [];
    yearList.forEach( (e, i) => {
        if(e.checked === true) yearsSelected.push(parseInt(e.id));
    })
    return yearsSelected;
};

export function getRegionsSelected() {
    var regionSelected = [];
    regionList.forEach( (e, i) => {
        if(e.checked === true) regionSelected.push(e.id);
    })
    return regionSelected;
};

export function getGendersSelected() {
    var gendersSelected = [];
    genderList.forEach( (e, i) => {
        if(e.checked === true) gendersSelected.push(e.id);
    })

};

export function getAllRegionsName(){
    var regionName = [];

    regionList.forEach( (e,i) => {
        regionName.push(e.id);
    });

    return regionName;
}

export function SwitchGenderFilter(multiOption){ //TODO: Cambio checkbox <-> radio
    genderList.forEach( (e,i) => {
        e.type = (multiOption) ? 'checkbox' : 'radio';
    });
}



