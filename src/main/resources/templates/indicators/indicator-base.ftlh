<#import "../commons/header.ftlh" as hd />

<!DOCTYPE html>
<html>
    <head>
        <title>Archivos</title>
        <meta charset="UTF-8">
        <base href="/">
        <link href="css/header.css" rel="stylesheet"/>
        <link href="css/indicator-base.css" rel="stylesheet"/>
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
        </div>
        <div class=chartContainer>
            Grafico
        </div>
        <div class=filterContainer>
            <h3>Filtros</h3>
        </div>
    </div>
</html>