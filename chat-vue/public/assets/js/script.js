const sideMenuWrapper = document.querySelector('.mobile-menu-wrp');
const sideMenu = document.querySelector('.mobile-menu');
var menuButton = document.getElementById("menu-btn");

menuButton.addEventListener('click', function(){
    sideMenuWrapper.style.display = 'block';
    sideMenuWrapper.style.backgroundColor = "rgba(0, 0, 0, 0.5)";
    setTimeout(function () {
        sideMenu.style.transform = 'translateX(440px)';
    }, 10);
})

sideMenuWrapper.addEventListener('click', function(){
    sideMenu.style.transform = 'translateX(0px)';
    sideMenuWrapper.style.backgroundColor = "rgba(0, 0, 0, 0)";
    setTimeout(function () {
        sideMenuWrapper.style.display = 'none';
    }, 500);
})

// window.addEventListener('click', () => {
//     let target = event.target;
//     if (target.className === 'menu-button') {
		
        
//     } else if (target.className === 'mobile-menu-wrp') {
// 		console.log('menu-button');
//         sideMenu.style.transform = 'translateX(0px)';
//         sideMenuWrapper.style.backgroundColor = "rgba(0, 0, 0, 0)";
//         setTimeout(function () {
//             sideMenuWrapper.style.display = 'none';
//         }, 500);
//     }
// });