import {allRegion, allGender, allYear, regionList, genderList, yearList, selectAllRegions, selectAllYears, selectAllGenders,
        getYearsSelected, getRegionsSelected, getGendersSelected} from '../filter.js';
import {selectChart} from './chart-setup.js';

const filterList = document.getElementsByClassName('filterOption');

Object.keys(filterList).forEach( (k,i) => {
    filterList[i].addEventListener('click', checkFilters);
});

function checkFilters(){
    if(this.name === 'year'){
        let years = getYearsSelected().length;
        allYear.checked = (yearList.length != years) ? false : true;
    }
    if(this.name === 'region'){
        let regions = getRegionsSelected().length;
        allRegion.checked = (regionList.length != regions) ? false : true;
    }
    if(this.name === 'gender'){
        let genders = getGendersSelected().length;
        allGender.checked = (genderList.length != genders) ? false : true;
    }
    selectChart();
}

function selectAllByFilter(){

    if(this.id === 'allYear') selectAllYears(this);
    if(this.id === 'allRegion') selectAllRegions(this);
    if(this.id === 'allGender') selectAllGenders(this);

    selectChart();
}

allYear.addEventListener('click', selectAllByFilter);
allRegion.addEventListener('click', selectAllByFilter);
allGender.addEventListener('click', selectAllByFilter);