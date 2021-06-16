 const loadedFilesSquare = document.getElementById("loadedFilesSquare");
 const indicatorsSquare = document.getElementById("indicatorsSquare");

    loadedFilesSquare.addEventListener("click", ()=> {
        window.location.href="/scheduleFileLoadPost";

    });

    indicatorsSquare.addEventListener("click", ()=> {
            window.location.href="/indicators/training";

    });
