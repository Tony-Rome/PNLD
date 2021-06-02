const yearList = document.getElementsByName('year');
const regionList = document.getElementsByName('region');
const genderList = document.getElementsByName('gender');
const allYear = document.getElementById('allYear');
const allRegion = document.getElementById('allRegion');
const allGender = document.getElementById('allGender');

getYearsSelected = () => {};

getRegionsSelected = () => {};

getGendersSelected = () => {};

function selectAllYears() {

    yearList.forEach( (e, i) => {
        e.checked = this.checked;
    })
};

function selectAllRegions() {
    regionList.forEach( (e, i) => {
        e.checked = this.checked;
    })
};

function selectAllGenders() {
    genderList.forEach( (e,i) => {
        e.checked = this.checked;
    })
};

yearList.forEach((e,i) => {
    e.addEventListener('click', getYearsSelected);
});

regionList.forEach((e,i) => {
    e.addEventListener('click', getRegionsSelected);
});

genderList.forEach((e,i) => {
    e.addEventListener('click', getGendersSelected);
});

allYear.addEventListener('change', selectAllYears);
allRegion.addEventListener('click', selectAllRegions);
allGender.addEventListener('click', selectAllGenders);