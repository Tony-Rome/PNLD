<#import "indicator-base.ftlh" as ib/>
<#import "../commons/header.ftlh" as hd />
<!DOCTYPE html>
<html>

    <@ib.head/>

    <body>

    <@hd.header title = 'Indicadores' />

    <#assign dimensionIndex = 1/>

    <@ib.dimensions dimensionIndex = dimensionIndex/>


    <div class=indicatorContainer>

        <div class=subDimensionContainer>
            <h3>Subdimensiones</h3>

          <div class="subDimensionAccordion">
              <div class="subDimensionBlock subDimensionActive">

                  <h3 class="subDimensionTitle" id="teacher">Docentes</h3>
                  <div class="subDimensionContent">

                      <#assign teacherList = ["N° de inscritos","% activos", "Distribución n° secciones","Distribución n° estudiante"] />
                      <#list teacherList as teacher>
                        <li><input type="radio" class="subDimensionOption" value=${teacher?index} name="teacher" onclick="chartFunction()">${teacher}</input></li>
                      </#list>

                  </div>
              </div>
              <div class="subDimensionBlock subDimensionInactive">

                  <h3 class="subDimensionTitle" id="student">Estudiantes</h3>
                  <div class="subDimensionContent">

                        <#assign studentList = ["N° de inscritos", "% activos", "Distribución nivel de avance", "Distribución nivel de uso"] />
                        <#list studentList as student>
                            <li><input type="radio" class="subDimensionOption" value=${student?index} name="student" onclick="chartFunction()">${student}</input></li>
                        </#list>

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

    <@ib.javascript/>
    <script src="js/indicator/code/subDimension.js"></script>

</html>