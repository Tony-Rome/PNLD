<#import "files-table.ftlh" as ft/>
<#import "pagination.ftlh" as pg/>
<#import "popup.ftlh" as pu/>
<#import "file-form.ftlh" as ff/>
<#import "header.ftlh" as hd />

<!DOCTYPE html>
<html>
    <head>
        <title>Archivos</title>
        <meta charset="UTF-8">
        <base href="/">
        <link href="css/header.css" rel="stylesheet"/>
        <link href="css/popup.css" rel="stylesheet"/>
        <link href="css/responsive.css" rel="stylesheet"/>
        <link href="css/files-table.css" rel="stylesheet"/>
        <link href="css/file-form.css" rel="stylesheet"/>
        <link href="css/pagination.css" rel="stylesheet"/>

    </head>
    <body>

        <#assign filesNumberToShow = 5 />

        <@hd.header title = 'Archivos'/>

        <@ft.filesTable filesNumberToShow = filesNumberToShow/>

        <#if (filesList?has_content) >
            <@pg.pagination filesNumberToShow = filesNumberToShow/>
        </#if>

        <@ff.fileForm/>
        <@pu.popup/>

        <script src="js/loadFiles.js"></script>
    </body>
</html>