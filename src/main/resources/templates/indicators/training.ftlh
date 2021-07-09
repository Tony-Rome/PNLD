<#import "indicator-base.ftlh" as ib/>
<#import "../commons/header.ftlh" as hd />
<!DOCTYPE html>
<html>

    <@ib.head/>

    <body>

    <@hd.header title = 'Indicadores' buttonHome = true/>

    <#assign dimensionIndex = 0/>

    <@ib.dimensions dimensionIndex = dimensionIndex/>


    <div class=indicatorContainer>

        <div class=subDimensionContainer>
            <h3>Subdimensiones</h3>

          <div class="subDimensionAccordion">

              <div class="subDimensionBlock subDimensionActive">

                  <h3 class="subDimensionTitle" id="institution">Establecimientos</h3>
                  <div class="subDimensionContent">

                    <#assign institutionList = ["N° participantes en PNLD", "% que participan por 1ra vez", "% de participantes"]/>
                    <#list institutionList as institution>
                        <li><input type="radio" class="subDimensionOption" value=${institution?index} name="training" id="institution">${institution}</input></li>
                    </#list>

                  </div>
              </div>

              <div class="subDimensionBlock subDimensionInactive">

                  <h3 class="subDimensionTitle" id="teacher">Docentes</h3>
                  <div class="subDimensionContent scrollable">

                    <#assign teacherList = ["N° de capacitados", "% jornada presencial", "% pre-test completado", "% post-test completado", "% capacitación completada", "Diferencia puntajes pre y post-test"]/>

                    <#list teacherList as teacher>
                        <li><input type="radio" class="subDimensionOption" value=${teacher?index} name="training" id="teacher">${teacher}</input></li>
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
    <script type="module" src="js/indicator/training/sub-dimension.js"></script>
    <script type="module" src="js/indicator/training/filter.js"></script>

</html>