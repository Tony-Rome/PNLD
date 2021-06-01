const yearList = document.getElementsByName('year');
const regionList = document.getElementsByName('region');
const genderList = document.getElementsByName('gender');

getYearsSelected = () => {};

getRegionsSelected = () => {};

getGendersSelected = () => {};

yearList.forEach((e,i) => {
    e.addEventListener('click', getYearsSelected);
});

regionList.forEach((e,i) => {
    e.addEventListener('click', getRegionsSelected);
});

genderList.forEach((e,i) => {
    e.addEventListener('click', getGendersSelected);
});