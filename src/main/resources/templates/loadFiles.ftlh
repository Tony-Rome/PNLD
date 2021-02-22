<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
    <title>Archivos</title>
    <meta charset="UTF-8">
    <link href="css/loadFiles.css" rel="stylesheet"/>
</head>
<body>
    <div class="banner">
        <img src="img/logo_mineduc.jpg" class="logo_mineduc" alt="logo_mineduc">
        <div class="title">Archivos</div>

    </div>

    <div class="files_table_container">
        <div class="files_table_header">
            <div class="files_table_title" >Archivos cargados</div>
            <div class="filters_container">
                Filtros
            </div>
        </div>

        <div class="table_container">
            <table>
              <tr>
                <th>Responsable</th>
                <th>Nombre archivo</th>
                <th>Origen</th>
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

    <div class="form_container">
      <form action="/scheduleLoadFilePost" method="post" enctype="multipart/form-data">
        <div class="row">
          <div class="col-25">
            <label>Nombre archivo</label>
          </div>
          <div class="col-75">
            <input type="text" name="name" placeholder="Nombre del archivo...">
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
          <div class="col-75">
            <div class="drag_drop">
                <div>Arrastre y suelte archivo</div>
                <br>
                <input type="file" id="uploadFile" name="uploadFile" >
            </div>
          </div>
        </div>
        <div class="row">
          <input type="submit" value="Iniciar carga">
        </div>
      </form>
    </div>



</body>
</html>