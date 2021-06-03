<#macro head>
        <head>
            <title>Archivos</title>
            <meta charset="UTF-8">
            <base href="/">
            <link href="css/header.css" rel="stylesheet"/>
            <link href="css/indicator/indicator-base.css" rel="stylesheet"/>
            <link href="css/indicator/subdimension.css" rel="stylesheet"/>
            <link href="css/indicator/filter.css" rel="stylesheet"/>
        </head>
</#macro>

<#macro dimensions dimensionIndex>

    <#assign dimensionsList = ["Capacitaciones","Code.org","Test Pensamiento Computacional"]/>
    <#assign linkList = ["training","code","ct-test"]/>

    <div class=dimensionHeaderContainer>
        <div class=dimensionTitle>Dimensiones</div>

        <div class=dimensionContainer>
            <#list dimensionsList as d>
                <a href="/indicators/${linkList[d?index]}" class="dimension <#if d?index == dimensionIndex>active</#if>">${d}</a>
            </#list>
        </div>
    </div>
</#macro>

<#macro chart >
    <div class=chartContainer id=chartContainer>
        <canvas id="myChart" width="700" height="700"></canvas>
    </div>
</#macro>

<#macro filter>

    <div class=filterContainer>

        <h3>Filtros</h3>

        <div class="filterAccordion">

            <#assign currentYear = .now?string.yyyy?number />

            <#assign yearRange = 4 />
            <#assign lastYear = currentYear - yearRange />

            <div class="filterBlock filterActive">
              <h3 class="filterTitle">Año</h3>
              <div class="filterContent scrollable">
                <li><input type="checkbox" id="allYear">Todos los años</input></li>
                <#list currentYear..lastYear as yearAsNumber>
                    <#assign yearAsStr = yearAsNumber?string.computer/>
                    <li><input type="checkbox" name="year" value=${yearAsStr}>${yearAsStr}</input></li>
                </#list>
              </div>
            </div>

            <#assign regionList = ["Tarapacá", "Antofagasta", "Atacama", "Coquimbo", "Valparaíso", "O'Higgins",
                "Maule", "BioBío", "Araucanía", "Los Lagos", "Aysén", "Magallanes", "Metropolitana", "Los Ríos",
                 "Arica y Parinacota", "Ñuble", "Otra"] />

            <div class="filterBlock filterInactive">
              <h3 class="filterTitle">Región</h3>
              <div class="filterContent scrollable">
                <li><input type="checkbox" id="allRegion">Todas las regiones</input></li>
                <#list regionList as region>
                    <li><input type="checkbox" id="${region}" name="region" value=${region?index + 1}>${region}</input></li>
                </#list>
              </div>
            </div>

            <#assign genderList = ["Femenino", "Masculino", "No especificado", "Otro"]/>

            <div class="filterBlock filterInactive" id="genderFilter">
                <h3 class="filterTitle">Género</h3>
                <div class="filterContent scrollable">
                    <li><input type="checkbox" id="allGender">Todos los géneros</input></li>
                    <#list genderList as gender>
                      <li><input type="checkbox" name="gender" value=${gender?index}>${gender}</input></li>
                    </#list>
                </div>
            </div>
        </div>
    </div>
</#macro>

<#macro javascript>
    <script src="js/indicator/sub-dimension.js"></script>
    <script src="js/indicator/filter.js"></script>
    <script src="js/indicator/chart-main.js"></script>
</#macro>

