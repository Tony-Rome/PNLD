import {getSubDimensionSelectedElement} from './sub-dimension.js';

const filterBock = document.querySelectorAll('.filterBlock');
const filterTitle = document.querySelectorAll('.filterTitle');

export const yearList = document.getElementsByName('year');
export const regionList = document.getElementsByName('region');
export const genderList = document.getElementsByName('gender');

export const allYear = document.getElementById('allYear');
export const allRegion = document.getElementById('allRegion');
export const allGender = document.getElementById('allGender');

var genderListState;

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
    return gendersSelected;
};

export function getAllRegionsName(){
    var regionName = [];

    regionList.forEach( (e,i) => {
        regionName.push(e.id);
    });

    return regionName;
}

export function selectAllYears(element) {
    var yearList = document.getElementsByName('year');
    yearList.forEach( (e, i) => {
        e.checked = element.checked;
    });
};

export function selectAllRegions(element) {
    var regionList = document.getElementsByName('region');
    regionList.forEach( (e, i) => {
        e.checked = element.checked;
    });
};

export function selectAllGenders(element) {
    var genderList = document.getElementsByName('gender');
    genderList.forEach( (e,i) => {
        e.checked = element.checked;
    });
};

export function SwitchGenderFilter(multiOption){


    if(multiOption === true){

        allGender.parentElement.style.display = 'initial';
        for(var i = 0; i <= genderList.length - 1 ; i++){
            genderList[i].type = 'checkbox';


        }
        if(allGender.checked === true) selectAllGenders(allGender);
    }
    if(multiOption === false){

        const array = Array.from(genderList);
        const index = array.findIndex(element => element.checked === true);

        allGender.parentElement.style.display = 'none';
        allGender.checked = false;

        for(var i = 0; i <= genderList.length - 1; i++){
         genderList[i].type = 'radio';
         genderList[i].checked = false;
        }
        genderList[index].checked = true;
    }
}