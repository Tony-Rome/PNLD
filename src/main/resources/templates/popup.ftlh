<#macro popup>
    <div class="popup" id="popup">
        <div class="popupContent">
            <div class="popupHead">
                Resumen archivo de carga
            </div>
            <div class="spinner displayNone" id="spinner"></div>
            <div class="displayNone popupDetail" id="popupBodyMsgOk">
                <div class="popupMsg">La carga de archivo se ha planificado. <br/><br/>Se procesará a las 00:00 UTC.</div>
                <input type="submit" id="btSubmitOk" value="Entendido">
            </div>
            <div class="displayNone popupDetail" id="popupBodyMsgError">
                <div class="popupMsg">Se ha producido un error al planificar carga del archivo.</div>
                <br/>
                Motivo: <span id="popupMsgResponse"></span>
                <input type="submit" id="btSubmitError" value="Entendido">
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