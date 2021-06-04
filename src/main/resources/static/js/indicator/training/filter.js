import {selectChart} from './utils.js';

const filterList = document.getElementsByClassName('filterOption');

const allYear = document.getElementById('allYear');
const allRegion = document.getElementById('allRegion');
const allGender = document.getElementById('allGender');

function selectAllYears() {
    var yearList = document.getElementsByName('year');
    yearList.forEach( (e, i) => {
        e.checked = this.checked;
    });
    selectChart();
};

function selectAllRegions() {
    var regionList = document.getElementsByName('region');
    regionList.forEach( (e, i) => {
        e.checked = this.checked;
    });
    selectChart();
};

function selectAllGenders() {
    var genderList = document.getElementsByName('gender');
    genderList.forEach( (e,i) => {
        e.checked = this.checked;
    });
    selectChart();
};

Object.keys(filterList).forEach( (k,i) => {
    filterList[i].addEventListener('click', selectChart);
});

allYear.addEventListener('click', selectAllYears); //TODO: Se ppuede hacer general con classname
allRegion.addEventListener('click', selectAllRegions); //TODO: Se ppuede hacer general con classname
allGender.addEventListener('click', selectAllGenders); //TODO: Se ppuede hacer general con classname