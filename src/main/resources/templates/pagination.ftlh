<#function calculateNumberPages filesCount numberFiles>
    <#if (filesCount % numberFiles) != 0>
        <#return ((filesCount / numberFiles) + 1)?floor>
    <#else>
        <#return (filesCount / numberFiles)>
    </#if>
 </#function>



<#macro pagination numberFiles>

    <#assign filesCount = filesList?size />
    <#assign numberPages = calculateNumberPages(filesCount, numberFiles) />
    <#assign interval = 1..numberPages>
    <#assign pagePath = "/scheduleFileLoadPost/page">
    <#assign previousPage = currentPage -1 />
    <#assign nextPage = currentPage + 1 />

    <div class="pagination">

        <a href=${pagePath}/${previousPage}>&laquo; Anterior</a>

        <#list interval as page>
            <#if page == currentPage>
                <a class="currentPage"  href=${pagePath}/${page}>${page}</a>
            <#else>
                <a href=${pagePath}/${page}>${page}</a>
            </#if>
        </#list>

        <a href=${pagePath}/${nextPage}>Siguiente &raquo;</a>

    </div>

</#macro>