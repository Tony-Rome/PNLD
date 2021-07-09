import {PADDING_AXIS_TITLE, FONT_SIZE_AXIS_TITLE} from '../chart-config.js';
import {defineTitle, getNewContext} from '../utils.js';

export function participantInstitutionCounterChart (labels, datasets, title) {
    var ctx = getNewContext();

    var myChart = new Chart(ctx, {
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
                            text: 'Cantidad por región',
                            align: 'center',
                            font: { size: FONT_SIZE_AXIS_TITLE },
                            padding: { bottom: PADDING_AXIS_TITLE }
                        }
                    },
                    y: {
                        title: {
                            display: true,
                            text: 'Regiones',
                            align: 'center',
                            font: { size: FONT_SIZE_AXIS_TITLE },
                            padding: { bottom: PADDING_AXIS_TITLE }
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
                        text: 'Número de establecimientos que participan en PNLD ' + defineTitle(title),
                    }
                }
            }
        });
    myChart.update();
}

export function firstTimeInstitutionPercentageChart (labels, datasets, title, dataList) {
  var ctx = getNewContext();

  var myChart = new Chart(ctx, {
      type: 'bar',
      data: { labels: labels, datasets: datasets },
      options: {
          scales: {
              x: {
                  position: 'top',
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
                      font: { size: FONT_SIZE_AXIS_TITLE },
                      padding: { bottom: PADDING_AXIS_TITLE },
                  }
              },
              y: {
                  title: {
                      display: true,
                      text: 'Regiones',
                      align: 'center',
                      font: { size: FONT_SIZE_AXIS_TITLE },
                      padding: { bottom : PADDING_AXIS_TITLE }
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
                  text: 'Porcentaje de establecimientos que participan por primera vez ' + defineTitle(title),
              }
          }
      }
  });

  myChart.update();
}
