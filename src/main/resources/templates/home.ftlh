<#import "header.ftlh" as hd />

<!DOCTYPE html>
<html>
    <head>
        <title>Archivos</title>
        <meta charset="UTF-8">
        <base href="/">
        <link href="css/header.css" rel="stylesheet"/>
        <link href="css/home.css" rel="stylesheet"/>
    </head>
    <body>

    <@hd.header title = 'Plataforma PNLD' />

    <div class="squareContainer">

       <div class="square" id="loadedFilesSquare">
        Cuadrado UNO
        <a href="/scheduleFileLoadPost" >  </a>
       </div>

       <div class="square" id="indicatorsSquare">
        Segundo cuadrado
       </div>

    </div>
    <script src="js/home.js"></script>
    </body>
</html>