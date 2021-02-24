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
      <form id="drag_drop_form" action="/scheduleLoadFilePost" method="post" enctype="multipart/form-data">

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
              <option value="diag">diagnostico</option>
              <option value="pre-cap">pre-capacitacion</option>
              <option value="post-cap">post-capacitacion</option>
              <option value="pc-1">PC 1 a 4 año</option>
              <option value="pc-2">PC 5 a 8 año</option>
              <option value="pc-3">PC docentes</option>
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
            <div id="dropZone">
                <span>Suelta un archivo o haz click para cargar uno</span>
                <input type="file" name="uploadFile" >
            </div>
          </div>

        </div>

        <div class="row">
          <input type="submit" id="btSubmit" value="Iniciar carga">
        </div>



      </form>

    </div>

    <!--<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>-->
    <script>

        (function (document, window, index) {

            var fileName = document.getElementById("fileName");
            var fileType = document.getElementById("selectedType");
            var uploadFile = document.getElementById("uploadFile");
            var droppedFile = document.getElementById("dragDrop");
            var buttonSubmit = document.getElementById("btSubmit");

            var dropZone = document.getElementById("dropZone");
            var dropZoneInput = document.getElementById("dropZoneInput");

            dropZone.addEventListener("click", e => {
                dropZoneInput.click();
            });

            var form = document.getElementById("drag_drop_form");

            console.log(fileName);
            console.log(fileType);
            console.log(uploadFile);
            console.log(droppedFile);
            console.log(form);
            console.log(buttonSubmit);

            fileName.oninput = function(){
                if(fileName.value === ""){
                    alert("No puede estar vacío campo nombre");
                }
                console.log(fileName.value);


            }

            fileType.onchange = function(){
                console.log(fileType.value);
            }



        }(document, window, 0));


    </script>
</body>
</html>