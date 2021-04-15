<#macro filesTable filesNumberToShow>

    <div class="filesTableContainer">
        <div class="filesTableHeader">
            <div>Archivos cargados</div>
        </div>

        <div>
            <table class="filesTable">
              <tr>
                <th>Responsable</th>
                <th>Nombre archivo</th>
                <th>Tipo</th>
                <th>Fecha subida</th>
                <th>Estado</th>
                <th>Registros totales</th>
                <th>Registros duplicados</th>
              </tr>

              <#if (filesList?has_content) >

                  <#assign maxID = [((currentPage * filesNumberToShow) - 1), (filesList?size - 1)]?min />
                  <#assign minID = (currentPage - 1) * filesNumberToShow />

                  <#list minID..maxID as i>
                          <tr>
                            <td>${filesList[i].loadedBy}</td>
                            <td>${filesList[i].name}</td>
                            <td>${filesList[i].type}</td>
                            <td>${filesList[i].uploadedDateTime}</td>
                            <td>${filesList[i].state}</td>
                            <td>${filesList[i].totalRecords}</td>
                            <td>${filesList[i].duplicateRecords}</td>
                          </tr>
                  </#list>

              </#if>
            </table>
        </div>

        <h2 class="emptyTableMsg">No hay archivos subidos para mostrar actualmente</h2>
    </div>


</#macro>
