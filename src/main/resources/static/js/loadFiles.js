const form = document.getElementById("fileForm");
const btSubmitForm = document.getElementById("btSubmitForm");
const fileName = document.getElementById("fileName");
const fileType = document.getElementById("selectedType");
const dropFile = document.getElementById("dropZone");
const uploadFile = document.getElementById("uploadFile");
const uploadedFileName = document.getElementById("uploadedFileName");
const popup = document.getElementById("popup");
const popupYes = document.getElementById("btSubmitYes");
const popupNo = document.getElementById("btSubmitNo");
const popupName = document.getElementById("popupName");
const popupType = document.getElementById("popupType");
const popupFile = document.getElementById("popupFile");
const popupBodyFile = document.getElementById("popupBodyFile");
const popupBodyMsgOk = document.getElementById("popupBodyMsgOk");
const popupBodyMsgError = document.getElementById("popupBodyMsgError");
const popupMsgResponse = document.getElementById("popupMsgResponse");
const btSubmitOk = document.getElementById("btSubmitOk");
const btSubmitError = document.getElementById("btSubmitError");
const spinner = document.getElementById("spinner");
const STATUS_FILE_UPLOAD_OK = 200;
const SHOW_SPINNER = 0;
const SHOW_MSG_OK = 1;
const SHOW_MSG_ERROR = 2;
const REMOVE_MSG_OK = 3;
const REMOVE_MSG_ERROR = 4;
const dt = new DataTransfer();
const INIT_URL = "/scheduleFileLoadPost";

let formDataValid = {
            name: false,
            uploadFile: false,
};

fileName.addEventListener("input", ()=> {
    (fileName.value === "") ? formDataValid.name=false : formDataValid.name=true ;
    submitController();
});

dropFile.addEventListener("dragover", (e) => {
    e.preventDefault();
    dropFile.style.borderColor = "black";
    dropFile.style.borderStyle = "solid";

});

["dragleave","dragend"].forEach(event => {
    dropFile.addEventListener(event, e => {
        dropFile.style.borderColor = "#ccc";
           dropFile.style.borderStyle = "dashed";
    });
});

dropFile.addEventListener("drop", (e) => {
    e.preventDefault();
    if(e.dataTransfer.files.length === 1){
        uploadFile.files = e.dataTransfer.files;
        name = uploadFile.files[0].name;
        activeFileName(name);
    }
    else deactivateFileName();

    submitController();
});

dropFile.addEventListener("click", ()=> {
    uploadFile.click();
});

uploadFile.addEventListener("change", (e)=>{
    if(uploadFile.files.length === 1){
        let name = uploadFile.files[0].name;
        activeFileName(name);
    }
    else deactivateFileName();

    submitController();
});

form.addEventListener("submit", (e)=>{
    e.preventDefault();
});

btSubmitForm.addEventListener("click", ()=> {
  popup.style.display = "flex";
  popupName.innerHTML = fileName.value;
  popupType.innerHTML = fileType.selectedOptions[0].innerText;
  popupFile.innerHTML = uploadFile.files[0].name;
});

popupYes.addEventListener("click", ()=> {
    let formResponse;
    let formData = new FormData(form);

    var xmlhttp = new XMLHttpRequest();
    var url = INIT_URL;

    xmlhttp.open("POST", url);
    xmlhttp.send(formData);

    switchPopup(SHOW_SPINNER);

    xmlhttp.onload = ()=>{
        if(xmlhttp.status === STATUS_FILE_UPLOAD_OK){
            switchPopup(SHOW_MSG_OK);
        }
        else{
            popupMsgResponse.innerHTML = xmlhttp.response;
            switchPopup(SHOW_MSG_ERROR);
        }
    };

});

popupNo.addEventListener("click", ()=> {
    popup.style.display = "none";
    deactivateFileName();
    form.reset();
    formDataValid.name = false;
    submitController();
});

btSubmitOk.addEventListener("click", ()=> {
    form.reset();
    formDataValid.name = false;
    deactivateFileName();
    popup.style.display = "none";
    switchPopup(REMOVE_MSG_OK);
    submitController();
    window.location.replace(INIT_URL);
});

btSubmitError.addEventListener("click",()=> {
    popup.style.display = "none";
    switchPopup(REMOVE_MSG_ERROR);
    deactivateFileName();
    uploadFile.files = dt.files;
    submitController();
});

submitController = () => {
            if(formDataValid.name && formDataValid.uploadFile){
                btSubmitForm.classList.remove('disabled');
                btSubmitForm.classList.add('enabled');
                btSubmitForm.toggleAttribute('disabled', false);
            }
            else{
                btSubmitForm.classList.remove('enabled');
                btSubmitForm.classList.add('disabled');
                btSubmitForm.toggleAttribute('disabled', true);
            }
        };

activeFileName = (name) => {
            dropFile.classList.add("displayNone");
            uploadedFileName.classList.remove("displayNone");
            uploadedFileName.innerHTML = name;
            formDataValid.uploadFile = true;
        };

deactivateFileName = () => {
            dropFile.classList.remove("displayNone");
            uploadedFileName.classList.add("displayNone");
            formDataValid.uploadFile = false;
            dropFile.style.borderColor = "#ccc";
            dropFile.style.borderStyle = "dashed";
        };

switchPopup = (msg) => {
    switch(msg){
        case SHOW_SPINNER:
            spinner.classList.remove("displayNone");
            popupBodyFile.classList.add("displayNone");
            break;
        case SHOW_MSG_OK:
            spinner.classList.add("displayNone");
            popupBodyFile.classList.add("displayNone");
            popupBodyMsgOk.classList.remove("displayNone");
            break;
        case SHOW_MSG_ERROR:
            spinner.classList.add("displayNone");
            popupBodyFile.classList.add("displayNone");
            popupBodyMsgError.classList.remove("displayNone");
            break;
        case REMOVE_MSG_OK:
            popupBodyFile.classList.remove("displayNone");
            popupBodyMsgOk.classList.add("displayNone");
            break;
        case REMOVE_MSG_ERROR:
             popupBodyFile.classList.remove("displayNone");
             popupBodyMsgError.classList.add("displayNone");
    }
};