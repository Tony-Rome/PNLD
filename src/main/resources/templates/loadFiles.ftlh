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
                <div class="displayNone" id="uploadedFileName"></div>
                <div  id="dropZone">
                    <span id="span">Suelta un archivo o haz click para cargar uno</span>
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

    <!--<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>-->
    <script>

        const form = document.getElementById("fileForm");
        const btSubmitForm = document.getElementById("btSubmitForm");
        const fileName = document.getElementById("fileName");
        const fileType = document.getElementById("selectedType");
        const dropFile = document.getElementById("dropZone");
        const uploadFile = document.getElementById("uploadFile");
        const uploadedFileName = document.getElementById("uploadedFileName");
        const popup = document.getElementById("popup");
        const popupYes = document.getElementById("btSubmitYes");
        const popupNo = document.getElementById("btSubmitNo");
        const popupName = document.getElementById("popupName");
        const popupType = document.getElementById("popupType");
        const popupFile = document.getElementById("popupFile");
        const popupBodyFile = document.getElementById("popupBodyFile");
        const popupBodyMsg = document.getElementById("popupBodyMsg");
        const btSubmitOk = document.getElementById("btSubmitOk");


        let formDataValid = {
                    name: false,
                    uploadFile: false,
        };

        fileName.addEventListener("input", ()=> {
            (fileName.value === "") ? formDataValid.name=false : formDataValid.name=true ;
            submitController();
        });

        dropFile.addEventListener("dragover", (e) => {
            e.preventDefault();
        });

        dropFile.addEventListener("drop", (e) => {
            e.preventDefault();

            if(e.dataTransfer.files.length === 1){
                uploadFile.files = e.dataTransfer.files;
                name = uploadFile.files[0].name;
                activeFileName(name);
            }
            else deactivateFileName();

            submitController();
        });

        dropFile.addEventListener("click", ()=> {
            uploadFile.click();
        });

        uploadFile.addEventListener("change", ()=>{
            if(uploadFile.files.length === 1){
                let name = uploadFile.files[0].name;
                activeFileName(name);
            }
            else deactivateFileName();

            submitController();
        });

        activeFileName = (name) => {
            dropFile.classList.add("displayNone");
            uploadedFileName.classList.remove("displayNone");
            uploadedFileName.innerHTML = name;
            formDataValid.uploadFile = true;
        };

        deactivateFileName = () => {
            dropFile.classList.remove("displayNone");
            uploadedFileName.classList.add("displayNone");
            formDataValid.uploadFile = false;
        };

        submitController = () => {
            if(formDataValid.name && formDataValid.uploadFile){
                btSubmitForm.classList.remove('disabled');
                btSubmitForm.classList.add('enabled');
                btSubmitForm.toggleAttribute('disabled', false);

            }
            else{
                btSubmitForm.classList.remove('enabled');
                btSubmitForm.classList.add('disabled');
                btSubmitForm.toggleAttribute('disabled', true);
            }
        };

        form.addEventListener("submit", (e)=>{
            e.preventDefault();
        });

        btSubmitForm.addEventListener("click", ()=> {
          popup.style.display = "flex";
          popupName.innerHTML = fileName.value;
          popupType.innerHTML = fileType.selectedOptions[0].innerText;
          popupFile.innerHTML = uploadFile.files[0].name;
        });

        popupYes.addEventListener("click", ()=> {
            let formData = new FormData(form);
            var xmlhttp = new XMLHttpRequest();
            var url = "/scheduleLoadFilePost";
            xmlhttp.open("POST", url);
            xmlhttp.send(formData);
            xmlhttp.onload = () => console.log(xmlhttp.status);



            formDataValid.name = false;



            popupBodyFile.classList.add("displayNone");
            popupBodyMsg.classList.remove("displayNone");


        });

        popupNo.addEventListener("click", ()=> {

            popup.style.display = "none";
            form.reset();
            deactivateFileName();
            formDataValid.name = false;
            submitController();
        });

        btSubmitOk.addEventListener("click", ()=> {
            form.reset();
            deactivateFileName();
            popup.style.display = "none";
            popupBodyFile.classList.remove("displayNone");
            popupBodyMsg.classList.add("displayNone");
            submitController();

        });

    </script>
</body>
</html>