<#import "filesTable.ftlh" as ft/>
<#import "pagination.ftlh" as pg/>
<#import "popup.ftlh" as pu/>
<#import "fileForm.ftlh" as ff/>

<!DOCTYPE html>
<html>
    <head>
        <title>Archivos</title>
        <meta charset="UTF-8">
        <base href="/">
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
            <a class="logout" href="/logout">Cerrar sesión</a>
        </div>

        <#assign numberFiles = 10 />

        <@ft.filesTable/>
        <@pg.pagination numberFiles = numberFiles/>
        <@ff.fileForm/>
        <@pu.popup/>

        <script src="js/loadFiles.js"></script>
    </body>
</html>