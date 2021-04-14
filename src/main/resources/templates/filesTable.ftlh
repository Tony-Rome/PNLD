<#if (((currentPage * 10) - 1) > (filesList?size - 1) ) >
    <#assign maxID = filesList?size -1 />
<#else>
    <#assign maxID = (currentPage * 10) - 1 />
</#if>
<#assign minID = (currentPage - 1) * 10 />

<#macro filesTable>
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
              <#list minID..maxID as i>
                      <tr>
                        <td>${filesList[i].loadedBy}</td>
                        <td>${filesList[i].name}</td>
                        <td>${filesList[i].type}</td>
                        <td>${filesList[i].loadedOnDateTime}</td>
                        <td>${filesList[i].state}</td>
                        <td>${filesList[i].totalRecords}</td>
                        <td>${filesList[i].duplicateRecords}</td>
                      </tr>
              </#list>
            </table>
        </div>
    </div>


</#macro>
