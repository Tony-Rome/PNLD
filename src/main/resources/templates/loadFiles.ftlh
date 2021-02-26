<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
    <title>Archivos</title>
    <meta charset="UTF-8">
</head>
<body>
<h2>Nuevo Archivo</h2>

<#if csvFile??>
    Archivo Ingresado<br>
    Nombre: ${csvFile.name}<br>
    Tipo: ${csvFile.selectedType}<br>
<#else>
    <form action="/scheduleLoadFilePost" method="post" enctype="multipart/form-data">
        Nombre:<br>
        <input type="text" name="name"/>
        <br><br>
        Tipo:<br>
        <select name="selectedType" id="selectedType">
            <option value="teacher-roster">code org - teacher roster</option>
            <option value="teacher-opt-in">code org - teacher opt</option>
            <option value="student-level">code org - student level</option>
            <option value="signin-per-course">code org - sign in per course</option>
            <option value="sign-ins">code org - sign ins</option>
            <option value="diagnostico">diagnostico</option>
            <option value="pre-capacita">pre-capacitacion</option>
            <option value="post-capacita">post-capacitacion</option>
            <option value="test-pc-1">test PC (1 - 4 año)</option>
            <option value="test-pc-2">test PC (5 - 8 año)</option>
            <option value="test-pc-3">Test PC docentes</option>
            <option value="salida">salida</option>
            <option value="satis">satisfaccion</option>
        </select>
        <br><br>
        Buscar Archivo:<br>
        <input type="file" id="uploadFile" name="uploadFile" >
        <br><br>
        <input type="submit" value="Iniciar Carga">
    </form>
</#if>
</body>
</html>