import {selectChart} from './chart-setup.js';

const filterList = document.getElementsByClassName('filterOption');

const allYear = document.getElementById('allYear');
export const allRegion = document.getElementById('allRegion');
const allGender = document.getElementById('allGender');

function selectAllYears() {
    var yearList = document.getElementsByName('year');
    yearList.forEach( (e, i) => {
        e.checked = this.checked;
    });
    selectChart();
};

export function selectAllRegions() {
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

allYear.addEventListener('click', selectAllYears);
allRegion.addEventListener('click', selectAllRegions);
allGender.addEventListener('click', selectAllGenders);