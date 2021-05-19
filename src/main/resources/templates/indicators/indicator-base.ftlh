<#import "../commons/header.ftlh" as hd />

<!DOCTYPE html>
<html>
    <head>
        <title>Archivos</title>
        <meta charset="UTF-8">
        <base href="/">
        <link href="css/header.css" rel="stylesheet"/>
        <link href="css/indicator/indicator-base.css" rel="stylesheet"/>
        <link href="css/indicator/subdimension.css" rel="stylesheet"/>
        <link href="css/indicator/filter.css" rel="stylesheet"/>
    </head>
    <body>

    <@hd.header title = 'Indicadores' />

    <div class=dimensionHeaderContainer>
        <div class=dimensionTitle>Dimensiones</div>
        <div class=dimensionContainer>
            <div class=dimension>Capacitaciones</div>
            <div class=dimension>Uso Code.org</div>
            <div class=dimension>Test Pensamiento COmputacional</div>
        </div>
    </div>


    <div class=indicatorContainer>

        <div class=subDimensionContainer>
            <h3>Subdimensiones</h3>

          <div class="subDimensionAccordion">
              <div class="subDimensionBlock subDimensionActive">
                  <h3 class="subDimensionTitle">Establecimientos</h3>
                  <div class="subDimensionContent">
                      <li><input type="radio" class="subDimensionOption" value="1" name="institution">Cantidad por región</input></li>
                      <li><input type="radio" class="subDimensionOption" value="2" name="institution">Participantes en PNLD</input></li>
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


        <div class=chartContainer>
            Grafico
        </div>


        <div class=filterContainer>
            <h3>Filtros</h3>

            <div class="filterAccordion">
                  <div class="filterBlock filterActive">
                      <h3 class="filterTitle">Año</h3>
                      <div class="filterContent">
                          <li><input type="checkbox">2021</input></li>
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
</html>