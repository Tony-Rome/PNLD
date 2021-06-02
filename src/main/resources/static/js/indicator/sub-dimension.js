const sdBlock = document.querySelectorAll('.subDimensionBlock');
const genderFilter = document.getElementById('genderFilter');
const sdTitle     = document.querySelectorAll('.subDimensionTitle');
const ACTIVE_CLASS = "subDimensionActive";
const INACTIVE_CLASS = "subDimensionInactive";
const INSTITUTION_VALUE = "institution";

if(sdTitle[0].id === INSTITUTION_VALUE){
    genderFilter.style.display = "none";
}

sdTitle.forEach( (e, i) => {
    sdTitle[i].addEventListener('click', ()=>{
        sdBlock.forEach( (e, i) =>{
            sdBlock[i].classList.remove(ACTIVE_CLASS)
            sdBlock[i].classList.add(INACTIVE_CLASS)
        })
        sdBlock[i].classList.add(ACTIVE_CLASS)
        sdBlock[i].classList.remove(INACTIVE_CLASS)

        if(sdBlock[i].classList.contains(ACTIVE_CLASS) && sdTitle[i].id === INSTITUTION_VALUE){
            genderFilter.style.display = "none";
        }else{
            genderFilter.style.display = "block";
        }
    })
});