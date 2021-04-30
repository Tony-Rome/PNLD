<#import "../commons/header.ftlh" as hd />

<!DOCTYPE html>
<html>
    <head>
        <title>Archivos</title>
        <meta charset="UTF-8">
        <link href="css/header.css" rel="stylesheet"/>
        <link href="css/home.css" rel="stylesheet"/>
    </head>
    <body>

    <@hd.header title = 'Plataforma PNLD' />

    <div class="container">

       <div class="squareContainer">
           <div class="square" id="loadedFilesSquare">
            <img src="img/upload-file.jpg" class="imageHome" alt="upload file">
           </div>

           <h2>CARGAR ARCHIVOS</h2>
           <span>Se pueden cargar archivos csv y txt</span>

       </div>

       <div class="SquareContainer" >
           <div class="square" id="indicatorsSquare">
            <img src="img/indicator.jpg" class="imageHome" alt="indicator">
           </div>

           <h2>VER INDICADORES</h2>
           <span>Según los indicadores definidos</span>

        </div>
    </div>



    <script src="js/home.js"></script>
    </body>
</html>