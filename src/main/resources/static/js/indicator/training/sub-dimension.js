import {transformRegionName} from '../utils.js';
import {selectChart} from './utils.js';
import {getChart} from './chart.js';
import {getInstitutionSubDimensionData} from './api.js';

const institutionList = document.getElementsByName('institution');

institutionList.forEach((e,i) => {
    e.addEventListener('click', selectChart); //TODO: Se puede generalizar con todos los subdimensiones
});