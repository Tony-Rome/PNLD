<#import "filesTable.ftlh" as ft/>
<#import "pagination.ftlh" as pg/>
<#import "popup.ftlh" as pu/>
<#import "fileForm.ftlh" as ff/>
<#import "fileUploadHeader.ftlh" as hf />

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
        <link href="css/pagination.css" rel="stylesheet"/>

    </head>
    <body>

        <#assign filesNumberToShow = 5 />

        <@hf.header />
        <@ft.filesTable filesNumberToShow = filesNumberToShow/>

        <#if (filesList?has_content) >
            <@pg.pagination filesNumberToShow = filesNumberToShow/>
        </#if>

        <@ff.fileForm/>
        <@pu.popup/>

        <script src="js/loadFiles.js"></script>
    </body>
</html>