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

export function getPercentageBarChart (labels, datasets, title, dataList) {

  if(myChart) { myChart.destroy(); }

  myChart = new Chart(ctx, {
      type: 'bar',
      data: { labels: labels, datasets: datasets },
      options: {
          scales: {
              x: {
                  title: {
                      display: true,
                      text: 'N° de instituciones inscritas por primera vez',
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
                      text: 'Regiones',
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
          indexAxis: 'y',
          plugins:{
              tooltip:{
                  callbacks:{
                    title: function(data){
                      return data[0].label;
                    },
                    label: function(data){
                      console.log(dataList);
                      console.log(data);
                      let index = data.parsed.y;
                      let regionData = dataList[index];
                      let year = parseInt(data.dataset.label);
                      console.log("AÑO ABAJo");
                      console.log(year);
                      let total = regionData.trainingInstitutionDataByYearDTOList.map(e => {
                          if (e.year === year) return e.institutionNumberPNLD;
                      });
                      console.log("TOTAL ABAJO");
                      console.log(total);
                      let percentage = data.formattedValue + "%";

                      return year + " - " + "Total: "+ total + "valor actual: " + percentage + "(" + percentage +")";
                    },
                    afterLabel: function(tooltipItem, data){
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
