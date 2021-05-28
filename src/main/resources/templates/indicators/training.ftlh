<#import "indicator-base.ftlh" as ib/>
<#import "../commons/header.ftlh" as hd />
<!DOCTYPE html>
<html>

    <@ib.head/>

    <body>

    <@hd.header title = 'Indicadores' />

    <#assign dimensionIndex = 0/>

    <@ib.dimensions dimensionIndex = dimensionIndex/>


    <div class=indicatorContainer>

        <div class=subDimensionContainer>
            <h3>Subdimensiones</h3>

          <div class="subDimensionAccordion">
              <div class="subDimensionBlock subDimensionActive">

                  <h3 class="subDimensionTitle">Establecimientos</h3>
                  <div class="subDimensionContent">
                      <li><input type="radio" class="subDimensionOption" value="2" name="institution" onclick="chartFunction()">N° participantes en PNLD</input></li>
                      <li><input type="radio" class="subDimensionOption" value="3" name="institution">% que participan por 1ra vez</input></li>
                      <li><input type="radio" class="subDimensionOption" value="4" name="institution">% de participantes</input></li>
                  </div>
              </div>
              <div class="subDimensionBlock subDimensionInactive">
                  <h3 class="subDimensionTitle">Docentes</h3>
                  <div class="subDimensionContent scrollable">
                      <li><input type="radio" class="subDimensionOption">N° de capacitados</input></li>
                      <li><input type="radio" class="subDimensionOption">% de jornada presencial</input></li>
                      <li><input type="radio" class="subDimensionOption">% que completan pre-test</input></li>
                      <li><input type="radio" class="subDimensionOption">% que completan post-test</input></li>
                      <li><input type="radio" class="subDimensionOption">% que completan curso en línea</input></li>
                      <li><input type="radio" class="subDimensionOption">% que aprueban capacitación</input></li>
                      <li><input type="radio" class="subDimensionOption">Diferencia puntajes pre y post-test</input></li>
                  </div>
              </div>
          </div>

        </div>


        <@ib.chart/>


        <div class=filterContainer>
            <h3>Filtros</h3>

            <div class="filterAccordion">

                  <@ib.yearFilter/>

                  <@ib.regionFilter/>

                  <@ib.genderFilter/>

              </div>
        </div>
    </div>

    <div class="exportData">Exportar Datos Indicador <img src="img/excel-logo.jpg" class="logoExcel" alt="export excel"></div>

    <script src="js/indicator/subDimension.js"></script>
    <script src="js/indicator/filter.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</html>