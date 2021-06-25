const ctx = document.getElementById('myChart').getContext('2d');
var myChart;

function defineTitle(data){
    return (data.length >= 2) ?
        data[data.length - 1] + " - " + data[0] : data[0];
}

export function participantInstitutionCounterChart (labels, datasets, title) {
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
                            text: 'Cantidad',
                            align: 'center',
                            font: {
                                size: 15,
                            },
                            padding: {
                                top: 12,
                            }
                        }
                    },
                    y: {
                        title: {
                            display: true,
                            text: 'Regiones',
                            align: 'center',
                            font: {
                                size: 15,
                            },
                            padding: {
                                bottom: 12,
                            }
                        }
                    }
                },
                indexAxis: 'y',
                plugins:{
                    tooltip:{
                      callbacks:{
                        title: function(data){
                          return data[0].label;
                        },
                        label: function(data){
                            let value = data.formattedValue;
                            let label = data.dataset.label;
                            return (value != 0) ? label + ": " + "cantidad " + value : null;
                        },

                      },
                    },
                    title: {
                        display: true,
                        text: 'N° de establecimientos por región ' + defineTitle(title),
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

export function firstTimeInstitutionPercentageChart (labels, datasets, title, dataList) {

  if(myChart) { myChart.destroy(); }

  myChart = new Chart(ctx, {
      type: 'bar',
      data: { labels: labels, datasets: datasets },
      options: {
          scales: {
              x: {
                  min: 0,
                  max: 100,
                  ticks:{
                    callback: function(value, index, values){
                        return value+"%";
                    }
                  },
                  title: {
                      display: true,
                      text: '% de instituciones que participan por primera vez',
                      align: 'center',
                      font: {
                          size: 15,
                      },
                      padding: {
                          top: 12,
                      }
                  }
              },
              y: {
                  title: {
                      display: true,
                      text: 'Regiones',
                      align: 'center',
                      font: {
                          size: 15,
                      }
                  }
              }
          },
          indexAxis: 'y',
          plugins:{
              tooltip:{
                  callbacks:{
                    title: function(data){
                      return data[0].label;
                    },
                    label: function(data){
                        let percentage = data.formattedValue;
                        let year = data.dataset.label;
                        return year + ": " + "Porcentaje " + percentage + "%";
                    },
                    afterLabel: function(data){
                      let index = data.parsed.y;
                      let regionData = dataList[index];
                      let year = parseInt(data.dataset.label);
                      let dataCounter = regionData.trainingIndicatorDataList.map(e => {
                          if (e.year === year) {
                            let dataCounter = {
                                'total': e.institutionCounterPNLD,
                                'firstTime': e.firstTimeInstitutionCounter,
                            };
                            return dataCounter;
                          };
                      }).filter(e => typeof e != 'undefined');
                      let total = (typeof dataCounter[0] != 'undefined') ? dataCounter[0].total : 0;
                      let firstTimeCounter = (typeof dataCounter[0] != 'undefined') ? dataCounter[0].firstTime : 0;
                      return (total != 0) ? "Detalle: " + "Total "+ total + " - valor actual " + firstTimeCounter : null;
                    },
                  },
              },
              title: {
                  display: true,
                  text: '% de establecimientos que participan por primera vez ' + defineTitle(title),
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

export function trainedTeacherCounterChart(labels, datasets, title, keyword){

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
                            text: 'Cantidad',
                            align: 'center',
                            font: {
                                size: 15,
                            },
                            padding: {
                                top: 12,
                            }
                        }
                    },
                    y: {
                        title: {
                            display: true,
                            text: 'Regiones',
                            align: 'center',
                            font: {
                                size: 15,
                            },
                            padding: {
                                bottom: 12,
                            }
                        }
                    }
                },
                indexAxis: 'y',
                plugins:{
                  tooltip:{
                      callbacks:{
                        title: function(data){
                          return data[0].label;
                        },
                        label: function(data){
                            let value = data.formattedValue;
                            let label = data.dataset.label;
                            return (value != 0) ? label + " " + keyword + ": " + "cantidad " + value : null;
                        },

                      },
                    },
                    title: {
                        display: true,
                        text: 'N° de docentes capacitados ' + defineTitle(title),
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
