<#import "indicator-base.ftlh" as ib/>
<#import "../commons/header.ftlh" as hd />
<!DOCTYPE html>
<html>

    <@ib.head/>

    <body>

    <@hd.header title = 'Indicadores' />

    <#assign dimensionIndex = 2/>

    <@ib.dimensions dimensionIndex = dimensionIndex/>


    <div class=indicatorContainer>

        <div class=subDimensionContainer>
            <h3>Subdimensiones</h3>

          <div class="subDimensionAccordion">
              <div class="subDimensionBlock subDimensionActive">

                    <assign subDimensions = ["Cantidad por región","Participantes en PNLD", "% que participan por 1ra vez","% por región"]>/
                  <h3 class="subDimensionTitle">Establecimientos</h3>
                  <div class="subDimensionContent">
                      <li><input type="radio" class="subDimensionOption" value="1" name="institution" onclick="chartFunction()">Cantidad por región</input></li>
                      <li><input type="radio" class="subDimensionOption" value="2" name="institution" onclick="chartFunction()">Participantes en PNLD</input></li>
                      <li><input type="radio" class="subDimensionOption" value="3" name="institution">% que participan por 1ra vez</input></li>
                      <li><input type="radio" class="subDimensionOption" value="4" name="institution">% por región</input></li>
                  </div>
              </div>
              <div class="subDimensionBlock subDimensionInactive">
                  <h3 class="subDimensionTitle">Estudiantes</h3>
                  <div class="subDimensionContent">
                      <li><a href="#" class="subDimensionOption">Opcion 1</a></li>
                      <li><a href="#" class="subDimensionOption">Participantes en PNLD</a></li>
                      <li><a href="#" class="subDimensionOption">% que participan por 1ra vez</a></li>
                      <li><a href="#" class="subDimensionOption">% por región</a></li>
                  </div>
              </div>
              <div class="subDimensionBlock subDimensionInactive">
                  <h3 class="subDimensionTitle">Estudiantes</h3>
                  <div class="subDimensionContent">
                  </div>
              </div>
          </div>

        </div>


        <div class=chartContainer id=chartContainer>
            <canvas id="myChart" width="400" height="400"></canvas>
        </div>


        <div class=filterContainer>
            <h3>Filtros</h3>

            <div class="filterAccordion">
                  <div class="filterBlock filterActive">
                      <h3 class="filterTitle">Año</h3>
                      <div class="filterContent">
                          <li><input type="checkbox" onclick="addRemoveFilter()">2021</input></li>
                          <li><input type="checkbox">2020</input></li>
                          <li><input type="checkbox">2019</input></li>
                          <li><input type="checkbox">2018</input></li>
                      </div>
                  </div>
                  <div class="filterBlock filterInactive">
                      <h3 class="filterTitle">Región</h3>
                      <div class="filterContent">
                          <li><input type="checkbox">Tarapaca</input></li>
                          <li><input type="checkbox">BioBio</input></li>
                          <li><input type="checkbox">Araucania</input></li>
                          <li><input type="checkbox">Los Lagos</input></li>
                          <li><input type="checkbox">Tarapaca</input></li>
                        <li><input type="checkbox">BioBio</input></li>
                        <li><input type="checkbox">Araucania</input></li>
                        <li><input type="checkbox">Los Lagos</input></li>
                      </div>
                  </div>
              </div>
        </div>
    </div>

    <div class="exportData">Exportar Datos Indicador <img src="img/excel-logo.jpg" class="logoExcel" alt="export excel"></div>

    <script src="js/indicator/subDimension.js"></script>
    <script src="js/indicator/filter.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script>
    var ctx = document.getElementById('myChart').getContext('2d');
    addRemoveFilter = () => {};
    chartFunction = () => {
        var myChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
                    datasets: [{
                        label: '# of Votes',
                        data: [12, 19, 3, 5, 2, 3],
                        backgroundColor: [
                            'rgba(255, 99, 132, 0.2)',
                            'rgba(54, 162, 235, 0.2)',
                            'rgba(255, 206, 86, 0.2)',
                            'rgba(75, 192, 192, 0.2)',
                            'rgba(153, 102, 255, 0.2)',
                            'rgba(255, 159, 64, 0.2)'
                        ],
                        borderColor: [
                            'rgba(255, 99, 132, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(75, 192, 192, 1)',
                            'rgba(153, 102, 255, 1)',
                            'rgba(255, 159, 64, 1)'
                        ],
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
    };

    </script>
</html>