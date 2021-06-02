const ctx = document.getElementById('myChart').getContext('2d');
var myChart;

export function getChart (labels, datasets) {

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
                        text: 'N° de colegios por región',
                    },
                    legend: {
                        display: false
                    }
                },

            }
        });

    myChart.update();
}
