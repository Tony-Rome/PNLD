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

export function getNumberBarChart (labels, datasets, title) {

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

export function getPercentageBarChart (labels, datasets, title, dataList) {
    console.log(dataList);
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
                      let dataNumber = regionData.trainingInstitutionDataByYearDTOList.map(e => {
                          if (e.year === year) {
                            let dataNumber = {
                                'total': e.institutionNumberPNLD,
                                'firstTime': e.firstTimeInstitutionNumber,
                            };
                            return dataNumber;
                          };
                      }).filter(e => typeof e != 'undefined');
                      let total = (typeof dataNumber[0] != 'undefined') ? dataNumber[0].total : 0;
                      let firstTimeNumber = (typeof dataNumber[0] != 'undefined') ? dataNumber[0].firstTime : 0;
                      return "Detalle: " + "Total "+ total + " - valor actual " + firstTimeNumber;
                    },
                  },
              },
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
