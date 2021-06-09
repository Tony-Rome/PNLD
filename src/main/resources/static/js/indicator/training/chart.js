const ctx = document.getElementById('myChart').getContext('2d');
var myChart;

function defineTitle(data){

    let baseTitle = 'N° de colegios por región ';

    if(Object.keys(data).length === 2){
        return baseTitle + data['fromYear'] + " - " + data['toYear'];
    }else{
        return baseTitle + data['year'];
    }
}

export function getChart (labels, datasets, title, dataList) {

  if(myChart) { myChart.destroy(); }

    myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: labels,
                datasets: datasets
            },
            options: {
                scales: {
                    x: {
                        stacked: true,
                        title: {
                            display: true,
                            text: 'Regiones',
                            align: 'end',
                            font: {
                                size: 14,
                            },
                            padding: {
                                top: 12,
                            }
                        }
                    },
                    y: {
                        stacked: true,
                        title: {
                            display: true,
                            text: 'Cantidad',
                            align: 'end',
                            font: {
                                size: 14,
                            },
                            padding: {
                                bottom: 12,
                            }
                        }
                    }
                },
                indexAxis: 'x',
                plugins:{
                    title: {
                        display: true,
                        text: defineTitle(title),
                    },
                    legend: {
                        display: true
                    }
                },
                responsive: true,

            }
        });

    myChart.update();
}
