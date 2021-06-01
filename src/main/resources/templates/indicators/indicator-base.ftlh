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
    <#assign linkList = ["training","code","TPC"]/>

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
        <canvas id="myChart" width="400" height="400"></canvas>
    </div>
</#macro>

<#macro yearFilter>
    <#assign currentYear = .now?string.yyyy?number />

    <#assign yearRange = 4 />
    <#assign lastYear = currentYear - yearRange />

    <div class="filterBlock filterActive">
      <h3 class="filterTitle">Año</h3>
      <div class="filterContent scrollable">
        <#list currentYear..lastYear as yearAsNumber>
            <#assign yearAsStr = yearAsNumber?string.computer/>
            <li><input type="checkbox" name="year" value=${yearAsStr} onclick="getYearsSelected()">${yearAsStr}</input></li>
        </#list>
      </div>
    </div>
</#macro>

<#macro regionFilter>

    <#assign regionList = ["Arica y Parinacota", "Tarapaca", "Antofagasta", "Atacama",
        "Coquimbo", " Valparaíso", " Metropolitana de Santiago", "O'higgins",
         "Maule", "Ñuble", "Biobio", "Aracanía", "Los ríos", "Los Lagos", "Aysén", "Magallanes", "Otra"] />

    <div class="filterBlock filterInactive">
      <h3 class="filterTitle">Región</h3>
      <div class="filterContent scrollable">
        <#list regionList as region>
            <li><input type="checkbox" name="region" value=${region?index} onclick="getRegionsSelected()">${region}</input></li>
        </#list>
      </div>
    </div>
</#macro>

<#macro genderFilter>

    <#assign genderList = ["Femenino", "Masculino", "No especificado", "Otro"]/>

    <div class="filterBlock filterInactive" id="genderFilter">
        <h3 class="filterTitle">Género</h3>
        <div class="filterContent">
            <#list genderList as gender>
              <li><input type="checkbox" name="gender" value=${gender?index} onclick="getGendersSelected()">${gender}</input></li>
            </#list>
        </div>
    </div>
</#macro>

<#macro javascript>
    <script src="js/indicator/subDimension.js"></script>
    <script src="js/indicator/filter.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</#macro>

