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
const btSubmitOk = document.getElementById("btSubmitOk");
const btSubmitError = document.getElementById("btSubmitError");
const spinner = document.getElementById("spinner");


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

        uploadFile.addEventListener("change", ()=>{
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
            var url = "/scheduleFileLoadPost";

            xmlhttp.open("POST", url);
            xmlhttp.send(formData);

            switchPopup(0);

            xmlhttp.onload = ()=>{

                if(xmlhttp.status === 200){
                    switchPopup(1);
                }
                else{
                    switchPopup(2);
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
            switchPopup(3);
            submitController();

        });

        btSubmitError.addEventListener("click",()=> {
            popup.style.display = "none";
            switchPopup(4);
            deactivateFileName();
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

        switchPopup = (status) => {

            switch(status){
                case 0:
                    spinner.classList.remove("displayNone");
                    popupBodyFile.classList.add("displayNone");
                    break;
                case 1:
                    spinner.classList.add("displayNone");
                    popupBodyFile.classList.add("displayNone");
                    popupBodyMsgOk.classList.remove("displayNone");
                    break;
                case 2:
                    spinner.classList.add("displayNone");
                    popupBodyFile.classList.add("displayNone");
                    popupBodyMsgError.classList.remove("displayNone");
                    break;
                case 3:
                    popupBodyFile.classList.remove("displayNone");
                    popupBodyMsgOk.classList.add("displayNone");
                    break;
                case 4:
                     popupBodyFile.classList.remove("displayNone");
                     popupBodyMsgError.classList.add("displayNone");
            }
        };