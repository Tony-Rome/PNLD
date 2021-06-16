<#macro pagination filesNumberToShow>

    <#assign filesNumber = filesList?size />
    <#assign numberPages = (filesNumber / filesNumberToShow)?ceiling />

    <#assign previousPage = [(currentPage - 1), 1]?max />
    <#assign nextPage = [(currentPage + 1),numberPages]?min />

    <#assign pageRange = 3 />
    <#assign minInterval = [(currentPage - pageRange), 1]?max />
    <#assign maxInterval = [(currentPage + pageRange), numberPages]?min />
    <#assign interval = minInterval..maxInterval>

    <#assign pagePath = "/scheduleFileLoadPost/page">

    <div class="pagination">

        <a href=${pagePath}/${previousPage}>&laquo; Anterior</a>

        <#if (minInterval > 1) >
            <a href=${pagePath}/${minInterval}>..</a>
        </#if>

        <#list interval as page>
            <#if page == currentPage>
                <a class="currentPage"  href=${pagePath}/${page}>${page}</a>
            <#else>
                <a href=${pagePath}/${page}>${page}</a>
            </#if>
        </#list>

        <#if (maxInterval < numberPages) >
            <a href=${pagePath}/${maxInterval}>..</a>
         </#if>

        <a href=${pagePath}/${nextPage}>Siguiente &raquo;</a>

    </div>

</#macro>