<#import "filesTable.ftlh" as ft/>
<#import "popup.ftlh" as pu/>
<#import "fileForm.ftlh" as ff/>

<!DOCTYPE html>
<html>
    <head>
        <title>Archivos</title>
        <meta charset="UTF-8">
        <link href="css/loadFiles.css" rel="stylesheet"/>
        <link href="css/popup.css" rel="stylesheet"/>
        <link href="css/responsive.css" rel="stylesheet"/>
        <link href="css/filesTable.css" rel="stylesheet"/>
        <link href="css/fileForm.css" rel="stylesheet"/>

    </head>
    <body>

        <div class="header">
            <img src="img/logo_mineduc.jpg" class="logoMineduc" alt="logo_mineduc">
            <div class="title">Archivos</div>
        </div>

        <@ft.filesTable/>

        <@ff.fileForm/>

        <@pu.popup/>

        <script src="js/loadFiles.js"></script>
    </body>
</html>