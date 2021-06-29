import {selectChart} from './chart-setup.js';

const institutionList = document.querySelectorAll('input#institution');
const teacherList = document.querySelectorAll('input#teacher');

institutionList.forEach((e,i) => {
    e.addEventListener('click', selectChart);
});

teacherList.forEach( (e,i) => {
    e.addEventListener('click', selectChart);
});