<#macro header title buttonHome>
  <div class="header">
      <img src="img/logo-mineduc.jpg" class="logoMineduc" alt="logo_mineduc">
      <#if buttonHome == true>
        <a class="buttonHome init" href="/home">Inicio</a>
      </#if>
      <div class="title">${title}</div>
      <a class="buttonHome logout" href="/logout">Cerrar sesión</a>
  </div>
</#macro>