<#macro popup>
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
</#macro>