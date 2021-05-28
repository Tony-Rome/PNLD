const sDBlock = document.querySelectorAll('.subDimensionBlock');
const sDTitle     = document.querySelectorAll('.subDimensionTitle');
const ctx = document.getElementById('myChart').getContext('2d');
const SUBDIMENSION_BASE_URL = '/v1/capacitaciones/establecimientos/';

var myChart;

sDTitle.forEach( (t, i) => {
    sDTitle[i].addEventListener('click', ()=>{
        sDBlock.forEach( (b, i) =>{
            sDBlock[i].classList.remove('subDimensionActive')
            sDBlock[i].classList.add('subDimensionInactive')
        })
        sDBlock[i].classList.add('subDimensionActive')
        sDBlock[i].classList.remove('subDimensionInactive')
    })
});

function getInfoRegion() {

    let fromYearParam = 2021;
    let toYearParam = 2021;
    const url = SUBDIMENSION_BASE_URL + '?fromYear=' + fromYearParam + '&toYear=' + toYearParam;
    const response = fetch(url, {method: 'GET'})
        .then( (resp) => resp.json() )
        .then( (data) => {
            return data;
        });
    return response;
};


randomColorFunction = () => {
    let r = Math.floor(Math.random() * 255);
    let g = Math.floor(Math.random() * 255);
    let b = Math.floor(Math.random() * 255);

    const COLOR_SOLID = 1;
    const COLOR_TRANSPARENT = 0.2;

    let randomColorDict = {};
    randomColorDict['backgroundColor'] = "rgba(" + r + "," + g + "," + b + "," + COLOR_TRANSPARENT + ")"
    randomColorDict['borderColor'] = "rgba(" + r + "," + g + "," + b + "," + COLOR_SOLID + ")"

    return randomColorDict
};


chartFunction = async () => {

        let response = await getInfoRegion();
        let infoRegionsResponse = response['trainingInfoRegions'];

        let labels = [];
        let label = []; // DEntro de datasets
        let backgroundColor = []; // DEntro de datasets
        let borderColor = []; // DEntro de datasets

        let data = [];
        let datasets = [];
        let dataset = {};

        infoRegionsResponse.forEach( (element, index) => {

            let regionName = element['name'];
            let schoolNumber = element['numberInstitutionsInPNLD'];
            let randomColorDict = randomColorFunction();


            labels.push(regionName);

            data.push(schoolNumber);
            backgroundColor.push(randomColorDict['backgroundColor'])
            borderColor.push(randomColorDict['borderColor'])




        });

        dataset['data'] = data;
        dataset['backgroundColor'] = backgroundColor;
        dataset['borderColor'] = borderColor;

        datasets.push(dataset);

        if(myChart) { myChart.destroy(); }

        myChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: labels,
                    datasets: datasets
                },
                options: {
                    scales: {
                        y: {
                            title: {
                                display: true,
                                text: 'Regiones',
                                font: {
                                    size: 14,
                                },

                            }
                        },
                        x: {
                            title: {
                                display: true,
                                text: 'Cantida',
                                font: {
                                    size: 14,
                                },
                                padding: {
                                    top: 12,
                                }
                            }
                        }
                    },
                    indexAxis: 'y',
                    plugins:{
                        legend: {
                            display: false
                        }
                    },
                    title: {
                        display: true,
                        text: 'N° de colegios por región'
                    }
                }
            });

        myChart.update();
    };