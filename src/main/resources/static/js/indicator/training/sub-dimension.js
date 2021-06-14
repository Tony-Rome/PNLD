import {selectChart} from './chart-setup.js';

const institutionList = document.getElementsByName('institution');
const teacherList = document.getElementsByName('teacher');

institutionList.forEach((e,i) => {
    e.addEventListener('click', selectChart);
});

teacherList.forEach( (e,i) => {
    e.addEventListener('click', selectChart);
});