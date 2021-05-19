const filterBock = document.querySelectorAll('.filterBlock');
const filterTitle = document.querySelectorAll('.filterTitle');

filterTitle.forEach( (t, i) => {
    filterTitle[i].addEventListener('click', ()=>{
        filterBock.forEach( (b, i) =>{
            filterBock[i].classList.remove('filterActive')
            filterBock[i].classList.add('filterInactive')
        })
        filterBock[i].classList.add('filterActive')
        filterBock[i].classList.remove('filterInactive')
    })
});