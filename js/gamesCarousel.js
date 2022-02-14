import images from "./../config/images.js";
let imgs = []

function loadImages() {
    let min = 0
    let max = images.imgs.length - 1
    
    for (let i = 0; i < 10; i++){
        let img = document.createElement("img");
        img.src = "images/covers/" + images.imgs[randomNumber(min, max)]
        let container = document.getElementById("games-container")
        img.style.left = (i * 350) + "px"
        imgs = [...imgs, img]
        container.appendChild(img)
    }
}

function randomNumber(min, max){
    return Math.floor(Math.random() * (max - min)) + min
}

function isOutOfScreen(element){
    let rect = element.getBoundingClientRect();
    return rect.left <= -rect.width
}
export function carousel(speed) {

    loadImages();
    
    let intervalo = 0
    let container = document.getElementById("games-container")
    container.style.height = imgs[0].getBoundingClientRect().height + "px"
    setInterval(() => {
        if(intervalo == 0 ){
            intervalo = 60000 * speed
        }
        imgs.forEach(img => {
            if(isOutOfScreen(img)){
                let current = img.getBoundingClientRect()
                img.style.transition = ""
                img.style.left = (current.width * (imgs.length - 2)) + "px"
                container.insertBefore(img, imgs[imgs.length -1])
            }
            let rect = img.getBoundingClientRect()
            let transX = rect.left - (rect.width * (imgs.length - 2))
            img.style.transition = "all " + (intervalo / 1000) + "s linear"
            img.style.left = transX + "px"
        });
    }, intervalo);
}