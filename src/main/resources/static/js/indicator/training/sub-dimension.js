import {selectChart} from './chart-setup.js';

const institutionList = document.getElementsByName('institution');

institutionList.forEach((e,i) => {
    e.addEventListener('click', selectChart);
});