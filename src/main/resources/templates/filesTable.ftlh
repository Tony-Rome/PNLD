<#function calculateNumberPages numberPages>
    <#if (numberPages % limitPagination) != 0>
        <#return ((numberPages / limitPagination) + 1)?floor>
    <#else>
        <#return (numberPages / limitPagination)>
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
                    <td>${file.loadedBy}</td>
                    <td>${file.name}</td>
                    <td>${file.type}</td>
                    <td>${file.loadedOnDateTime}</td>
                    <td>${file.totalRecords}</td>
                    <td>${file.duplicateRecords}</td>
                  </tr>
              </#list>
            </table>
        </div>
    </div>

        <#if filesCount <= limitPagination>
            <br>
            <br>
        <#else>

            <#assign pages = calculateNumberPages(filesCount)>
            #{pages}
            <#assign maxRange = 3>

            <#if ((currentPage - maxRange) <= 1)>
                <#assign minInterval = 1>
            <#else>
                <#assign minInterval = currentPage - 3>
            </#if>
            <#if ((currentPage + maxRange) >= pages) >
                <#assign maxInterval = pages>
            <#else>
                <#assign maxInterval = currentPage + maxRange>
            </#if>

            <#assign interval = minInterval..maxInterval>

            <div class="pagination">

                <#assign pagePath = "/scheduleFileLoadPost/page">

                <#assign previousPage = currentPage - 1>
                <#if (previousPage < 1) >
                    <a>&laquo; Anterior</a>
                <#else>
                    <a href=${pagePath}/${previousPage}>&laquo; Anterior</a>
                </#if>

                <#list interval as page>
                    <#if page == currentPage>
                        <a class="currentPage" href=${pagePath}/${page} id=${page}>${page}</a>
                    <#else>
                        <a href=${pagePath}/${page} id=${page}>${page}</a>
                    </#if>
                </#list>

                <#assign nextPage = currentPage + 1>
                <#if (nextPage > pages) >
                    <a>Siguiente &raquo;</a>
                <#else>
                    <a href=${pagePath}/${nextPage}>Siguiente &raquo;</a>
                </#if>


            </div>
        </#if>
</#macro>