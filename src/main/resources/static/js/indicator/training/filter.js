import {allRegion, allGender, allYear, selectAllRegions, selectAllYears, selectAllGenders} from '../filter.js';
import {selectChart} from './chart-setup.js';

const filterList = document.getElementsByClassName('filterOption');

Object.keys(filterList).forEach( (k,i) => {
    filterList[i].addEventListener('click', selectChart);
});

function selectAllYearsByTraining() { //TODO: tal vez no son necesarios para exportar
    selectAllYears(this);
    selectChart();
};

function selectAllRegionsByTraining() {
    selectAllRegions(this);
    selectChart();
};

function selectAllGendersByTraining() {
    selectAllGenders(this);
    selectChart();
};

allYear.addEventListener('click', selectAllYearsByTraining);
allRegion.addEventListener('click', selectAllRegionsByTraining);
allGender.addEventListener('click', selectAllGendersByTraining);