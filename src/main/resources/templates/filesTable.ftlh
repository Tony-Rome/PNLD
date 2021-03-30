 <#function pags x>
    <#if (x%10) != 0>
        <#return ((x/10) + 1)?floor>
    <#else>
        <#return (x/10)>
    </#if>
 </#function>

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
                <th>Registros totales</th>
                <th>Registros duplicados</th>
              </tr>
              <#list files as file>
                  <tr>
                    <td>${file.responsable}</td>
                    <td>${file.nombreArchivo}</td>
                    <td>${file.tipoArchivo}</td>
                    <td>${file.fechaCarga}</td>
                    <td>${file.registrosTotales}</td>
                    <td>${file.registrosDuplicados}</td>
                  </tr>
              </#list>
            </table>
        </div>
    </div>


        <#assign interval = 1..pags(filesCount)>

        <div class="pagination">
            <a href="#">&laquo; Anterior</a>
            <#list interval as page>
                <a href="#" id=${page} onclick="pagination()">${page}</a>
            </#list>
            <a href="#">Siguiente &raquo;</a>
        </div>
</#macro>