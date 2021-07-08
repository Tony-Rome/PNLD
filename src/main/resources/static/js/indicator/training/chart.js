const ctx = document.getElementById('myChart').getContext('2d');
var myChart;

Chart.defaults.color = 'black';
Chart.defaults.font.family = 'Arial';

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
                            text: 'Cantidad por región',
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
                            return label + ": " + "cantidad " + value;
                        },

                      },
                    },
                    title: {
                        display: true,
                        text: 'Número de establecimientos que participan en PNLD ' + defineTitle(title),
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
                      text: 'Porcentaje participación por primera vez',
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
                      let label = data.label;
                      let regionData = dataList.find(element => element.regionName === label);
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
                      return "Detalle: " + "Total "+ total + " - valor actual " + firstTimeCounter;
                    },
                  },
              },
              title: {
                  display: true,
                  text: 'Porcentaje de establecimientos que participan por primera vez ' + defineTitle(title),
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
//TOdO: Afecta a todos los gráficos

    myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: labels,
                datasets: datasets
            },
            options: {
                scales: {
                    x: {
                        position: 'top',
                        title: {
                            display: true,
                            text: 'Cantidad capacitados',
                            align: 'center',
                            font: {
                                size: 15,
                            },
                            padding: {
                                top: 50,
                                bottom: 50,
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
                            return label + " " + keyword + ": " + "cantidad " + value;
                        },

                      },
                    },
                    title: {
                        font:{
                            size: 20,
                            family: 'Arial',
                        },
                        display: true,
                        text: 'Número de docentes capacitados ' + defineTitle(title),
                        padding: 50,
                    },
                    legend: {
                        display: true,
                    }
                },
                responsive: true,

            }
        });

    myChart.update();
}

export function teacherInPersonSessionPercentageChart(labels, datasets, title, keyword, dataList){

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
                      text: 'Porcentaje asistencia jornada presencial',
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
                        let label = data.dataset.label;
                        return label + " " + keyword + ": " + "Porcentaje " + percentage + "%" ;
                    },
                    afterLabel: function(data){
                      let formattedValue = parseInt(data.formattedValue);
                      let label = data.label;
                      let datasetLabel = data.dataset.label;
                      let regionData = dataList.find(element => element.regionName === label);
                      let filterByYear = (typeof(keyword) === 'string') ? datasetLabel : keyword;
                      let filterByGender = (typeof(keyword) === 'string') ? keyword : datasetLabel;

                      let dataByYear = regionData.trainingIndicatorDataList.find(element => element.year === filterByYear);
                      if(dataByYear === undefined) return "Detalle: " + "Total "+ 0 + " - " + "Valor actual " + formattedValue;;

                      let dataByGender = dataByYear.dataByGenderList.find(element => element.gender === filterByGender.toLowerCase());
                      if(dataByGender === undefined) return "Detalle: " + "Total "+ 0 + " - " + "Valor actual " + formattedValue;;

                      let totalAssistance = dataByGender.assistanceCounter + dataByGender.notAssistanceCounter;
                      return "Detalle: " + "Total "+ totalAssistance + " - " + "Valor actual " + dataByGender.assistanceCounter;
                    },
                  },
              },
              title: {
                  display: true,
                  text: 'Porcentaje de docentes que asisten a jornada presencial ' + defineTitle(title),
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

export function teacherPretestCompletedPercentageChart(labels, datasets, title, keyword, dataList){
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
                      text: 'Porcentaje pre-test completado',
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
                        let label = data.dataset.label;
                        return label + " " + keyword + ": " + "Porcentaje " + percentage + "%" ;
                    },
                    afterLabel: function(data){
                      let formattedValue = parseInt(data.formattedValue);
                      let label = data.label;
                      let datasetLabel = data.dataset.label;
                      let regionData = dataList.find(element => element.regionName === label);
                      let filterByYear = (typeof(keyword) === 'string') ? datasetLabel : keyword;
                      let filterByGender = (typeof(keyword) === 'string') ? keyword : datasetLabel;

                      let dataByYear = regionData.trainingIndicatorDataList.find(element => element.year === filterByYear);
                      if(dataByYear === undefined) return "Detalle: " + "Total "+ 0 + " - " + "Valor actual " + formattedValue;;

                      let dataByGender = dataByYear.dataByGenderList.find(element => element.gender === filterByGender.toLowerCase());
                      if(dataByGender === undefined) return "Detalle: " + "Total "+ 0 + " - " + "Valor actual " + formattedValue;;

                      let totalCompleted = dataByGender.preTestCompletedCounter + dataByGender.preTestNotCompletedCounter;
                      return "Detalle: " + "Total "+ totalCompleted + " - " + "Valor actual " + dataByGender.preTestCompletedCounter;
                    },
                  },
              },
              title: {
                  display: true,
                  text: 'Porcentaje de docentes que completan pre-test ' + defineTitle(title),
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


//TODO: Este se modifica
export function teacherPostTestCompletedPercentageChart(labels, datasets, title, keyword, dataList){
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
                      text: 'Porcentaje post-test completado',
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
                        let label = data.dataset.label;
                        return label + " " + keyword + ": " + "Porcentaje " + percentage + "%" ;
                    },
                    afterLabel: function(data){
                      let formattedValue = parseInt(data.formattedValue);
                      let label = data.label;
                      let datasetLabel = data.dataset.label;
                      let regionData = dataList.find(element => element.regionName === label);
                      let filterByYear = (typeof(keyword) === 'string') ? datasetLabel : keyword;
                      let filterByGender = (typeof(keyword) === 'string') ? keyword : datasetLabel;

                      let dataByYear = regionData.trainingIndicatorDataList.find(element => element.year === filterByYear);
                      if(dataByYear === undefined) return "Detalle: " + "Total "+ 0 + " - " + "Valor actual " + formattedValue;;

                      let dataByGender = dataByYear.dataByGenderList.find(element => element.gender === filterByGender.toLowerCase());
                      if(dataByGender === undefined) return "Detalle: " + "Total "+ 0 + " - " + "Valor actual " + formattedValue;;

                      let totalCompleted = dataByGender.postTestCompletedCounter + dataByGender.postTestNotCompletedCounter;
                      return "Detalle: " + "Total "+ totalCompleted + " - " + "Valor actual " + dataByGender.postTestCompletedCounter;
                    },
                  },
              },
              title: {
                  display: true,
                  text: 'Porcentaje de docentes que completan post-test ' + defineTitle(title),
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
