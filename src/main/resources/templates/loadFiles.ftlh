<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
    <title>Archivos</title>
    <meta charset="UTF-8">
    <link href="css/loadFiles.css" rel="stylesheet"/>

</head>
<body>

    <div class="header">
        <img src="img/logo_mineduc.jpg" class="logoMineduc" alt="logo_mineduc">
        <div class="title">Archivos</div>

    </div>

    <div class="filesTableContainer">
        <div class="filesTableHeader">
            <div>Archivos cargados</div>
            <div>Filtros</div>
        </div>

        <div>
            <table>
              <tr>
                <th>Responsable</th>
                <th>Nombre archivo</th>
                <th>Tipo</th>
                <th>Fecha subida</th>
                <th>Registros totales</th>
                <th>Registros duplicados</th>
              </tr>
              <tr>
                <td>Admin1</td>
                <td>Teachers_Opt_in_data</td>
                <td>Code.org</td>
                <td>18/01/2021</td>
                <td>900</td>
                <td>100</td>
              </tr>
              <tr>
                <td>Admin2</td>
                <td>PC_1</td>
                <td>test PC</td>
                <td>20/02/2021</td>
                <td>30</td>
                <td>2</td>
              </tr>
            </table>
        </div>
    </div>

    <div class="pagination">
      <a href="#">&laquo; Anterior</a>
      <a href="#">1</a>
      <a class="active" href="#">2</a>
      <a href="#">3</a>
      <a href="#">4</a>
      <a href="#">5</a>
      <a href="#">6</a>
      <a href="#">Siguiente &raquo;</a>
    </div>

    <div class="formContainer">
      <form name="fileForm" id="fileForm" action="/scheduleLoadFilePost" method="post" enctype="multipart/form-data">

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

    <div class="popup" id="popup">
        <div class="popupContent">
            <div class="popupHead">
                Resumen archivo de carga
            </div>
            <div class="displayNone" id="popupBodyMsg">
                <div class="popupMsg">El archivo se ha subido a la plataforma y se comenzará con su procesamiento. Una vez finalizada la carga se informará a su correo.</div>
                <input type="submit" id="btSubmitOk" value="Entendido">
            </div>
            <div id="popupBodyFile">
                <div class="popupDetail">
                    <div class="popupRow">Nombre: <div class="popupRowDetail" id="popupName"> fileName </div></div>
                    <div class="popupRow">Tipo: <div class="popupRowDetail" id="popupType"> typeFile </div></div>
                    <div id="popupFile"> File </div>
                </div>
                <div class="buttonsContainer">
                    <input class="btSubmitYes" type="submit" id="btSubmitYes" value="Cargar">
                    <input class="btSubmitNo" type="submit" id="btSubmitNo" value="Cancelar">
                </div>
            </div>
        </div>
    </div>

    <script src="js/loadFiles.js"></script>
</body>
</html>