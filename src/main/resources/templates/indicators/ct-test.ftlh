<#import "indicator-base.ftlh" as ib/>
<#import "../commons/header.ftlh" as hd />
<!DOCTYPE html>
<html>

    <@ib.head/>

    <body>

    <@hd.header title = 'Indicadores' buttonHome = true/>

    <#assign dimensionIndex = 2/>

    <@ib.dimensions dimensionIndex = dimensionIndex/>


    <div class=indicatorContainer>

        <div class=subDimensionContainer>
            <h3>Subdimensiones</h3>

          <div class="subDimensionAccordion">
              <div class="subDimensionBlock subDimensionActive">

                  <h3 class="subDimensionTitle" id="institution">Establecimientos</h3>
                  <div class="subDimensionContent">

                     <#assign institutionList = ["N° de estudiantes que dan test","Distribución puntajes totales"] />
                    <#list institutionList as institution>
                        <li><input type="radio" class="subDimensionOption" value=${institution?index} name="institution" onclick="chartFunction()">${institution}</input></li>
                    </#list>

                  </div>
              </div>

              <div class="subDimensionBlock subDimensionInactive">

                  <h3 class="subDimensionTitle" id="teacher">Docentes</h3>
                  <div class="subDimensionContent">

                    <#assign teacherList = ["N° capacitados que dan test", "Distribución puntajes totales"] />
                    <#list teacherList as teacher>
                        <li><input type="radio" class="subDimensionOption" value=${teacher?index} name="teacher" onclick="chartFunction()">${teacher}</input></li>
                    </#list>

                  </div>
              </div>
          </div>

        </div>

        <@ib.chart/>

        <@ib.filter/>

    </div>

    <div class="exportData">Exportar Datos Indicador <img src="img/excel-logo.jpg" class="logoExcel" alt="export excel"></div>

    <@ib.javascript/>
    <script src="js/indicator/tpc/sub-dimension.js"></script>
    <script src="js/indicator/tpc/filter.js"></script>

</html>