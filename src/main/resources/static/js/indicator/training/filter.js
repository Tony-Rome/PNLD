import {allRegion, allGender, allYear, selectAllRegions, selectAllYears, selectAllGenders} from '../filter.js';
import {selectChart} from './chart-setup.js';

const filterList = document.getElementsByClassName('filterOption');

Object.keys(filterList).forEach( (k,i) => {
    filterList[i].addEventListener('click', selectChart);
});

function selectAllByFilter(){

    if(this.id === 'allYear') selectAllYears(this);
    if(this.id === 'allRegion') selectAllRegions(this);
    if(this.id === 'allGender') selectAllGenders(this);

    selectChart();
}

allYear.addEventListener('click', selectAllByFilter);
allRegion.addEventListener('click', selectAllByFilter);
allGender.addEventListener('click', selectAllByFilter);