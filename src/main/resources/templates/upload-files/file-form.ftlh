<#macro fileForm>
    <form name="fileForm" id="fileForm" action="/scheduleFileLoadPost" method="post" enctype="multipart/form-data">

        <table class="tableFileForm">
            <tr>
                <td><label>Nombre archivo</label></td>
                <td><input type="text" id="fileName" name="name" placeholder="Nombre del archivo"> </td>
            </tr>
            <tr>
                <td><label>Origen</label></td>
                <td>
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
                          <option value="satis">satisfaccion</option>
                          <option value="general">resumen general</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label>Nuevo archivo</label></td>
                <td>
                    <div class="displayNone" id="uploadedFileName"></div>
                    <div  id="dropZone">
                        <span id="span">Suelta un archivo o haz click para cargar uno aquí</span>
                        <input class="displayNone" id="uploadFile" type="file" name="uploadFile" >
                    </div>
                </td>
            </tr>

        </table>
        <input class="disabled" type="submit" id="btSubmitForm" value="Iniciar carga" disabled>

    </form>
</#macro>