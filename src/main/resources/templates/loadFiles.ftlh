<#import "filesTable.ftlh" as ft/>
<#import "popup.ftlh" as pu/>
<!DOCTYPE html>
<html>
    <head>
        <title>Archivos</title>
        <meta charset="UTF-8">
        <link href="css/loadFiles.css" rel="stylesheet"/>
        <link href="css/popup.css" rel="stylesheet"/>
        <link href="css/responsive.css" rel="stylesheet"/>

    </head>
    <body>

        <div class="header">
            <img src="img/logo_mineduc.jpg" class="logoMineduc" alt="logo_mineduc">
            <div class="title">Archivos</div>

        </div>

        <@ft.filesTable/>

        <div class="formContainer">
          <form name="fileForm" id="fileForm" action="/scheduleFileLoadPost" method="post" enctype="multipart/form-data">

                <div class="row">

                  <div class="col-25">
                    <label>Nombre archivo</label>
                  </div>

                  <div class="col-75">
                    <input type="text" id="fileName" name="name" placeholder="Nombre del archivo">
                  </div>

                </div>

                <div class="row">

                  <div class="col-25">
                    <label>Origen</label>
                  </div>

                  <div class="col-75">
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
                  </div>

                </div>

                <div class="row">

                  <div class="col-25">
                    <label>Nuevo archivo</label>
                  </div>

                  <div class="col-75" >
                    <div class="displayNone" id="uploadedFileName"></div>
                    <div  id="dropZone">
                        <span id="span">Suelta un archivo o haz click para cargar uno aquí</span>
                        <input class="displayNone" id="uploadFile" type="file" name="uploadFile" >
                    </div>
                  </div>

                </div>

                <div class="row">
                  <input class="disabled" type="submit" id="btSubmitForm" value="Iniciar carga" disabled>
                </div>

          </form>

        </div>

        <@pu.popup/>

        <script src="js/loadFiles.js"></script>
    </body>
</html>