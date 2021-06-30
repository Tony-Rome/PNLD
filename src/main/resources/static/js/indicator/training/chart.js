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
                      text: 'Porcentaje de docentes que asisten a jornada presencial',
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
                        return (percentage != 0) ? label + " " + keyword + ": " + "Porcentaje " + percentage + "%" : null;
                    },
                    afterLabel: function(data){
                      let formattedValue = parseInt(data.formattedValue);
                      let label = data.label;
                      let datasetLabel = data.dataset.label;
                      let regionData = dataList.find(element => element.regionName === label);
                      let filterByYear = (typeof(keyword) === 'string') ? datasetLabel : keyword;
                      console.log("filterbyyear: ");
                      console.log(filterByYear);
                      let dataByYear = regionData.trainingIndicatorDataList.find(element => element.year === filterByYear);
                      if(dataByYear === undefined) return null;
                      let filterByGender = (typeof(keyword) === 'string') ? keyword : datasetLabel;
                      console.log("filterbygender: ");
                      console.log(filterByGender);
                      console.log("keyword: ");
                      console.log(keyword);
                      console.log("datasetlabel: ");
                      console.log(datasetLabel);
                      console.log("databyyear: ");
                      console.log(dataByYear);
                      console.log("formatedvalue: ");
                      console.log(formattedValue);
                      let dataByGender = dataByYear.dataByGenderList.find(element => element.gender === filterByGender.toLowerCase());
                      if(dataByGender === undefined) return null;
                      console.log("databygender: ");
                      console.log(dataByGender);
                      let totalAssistance = dataByGender.assistanceCounter + dataByGender.notAssistanceCounter;

                      return (formattedValue != 0) ? "Detalle: " + "Total "+ totalAssistance + " - " + "Valor actual " + dataByGender.assistanceCounter : null;
                    },
                  },
              },
              title: {
                  display: true,
                  text: '% de docentes que asisten a jornada presencial ' + defineTitle(title),
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
