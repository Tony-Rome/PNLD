const sDBlock = document.querySelectorAll('.subDimensionBlock');
const sDTitle     = document.querySelectorAll('.subDimensionTitle');

sDTitle.forEach( (t, i) => {
    sDTitle[i].addEventListener('click', ()=>{
        sDBlock.forEach( (b, i) =>{
            sDBlock[i].classList.remove('subDimensionActive')
            sDBlock[i].classList.add('subDimensionInactive')
        })
        sDBlock[i].classList.add('subDimensionActive')
        sDBlock[i].classList.remove('subDimensionInactive')
    })
});