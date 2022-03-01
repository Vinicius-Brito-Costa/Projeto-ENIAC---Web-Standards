import images from "./../config/images.js";
let imgs = []
const imgCount = 10
const px = "px";

function loadImages() {
    let min = 0
    let max = images.imgs.length - 1
    
    for (let i = 0; i < imgCount; i++){
        let img = document.createElement("img");
        let imgName = images.imgs[randomNumber(min, max)]
        img.src = "images/covers/" + imgName
        img.alt = imgName
        let container = document.getElementById("games-container")
        img.style.left = (i * 350) + px
        imgs = [...imgs, img]
        container.appendChild(img)
    }
}

function randomNumber(min, max){
    return Math.floor(Math.random() * (max - min)) + min
}

export function carousel(speed) {

    loadImages();

    let intervalo = 1000
    let container = document.getElementById("games-container")

    container.style.height = imgs[0].getBoundingClientRect().height + px;
    setInterval(() => {
        imgs.forEach(img => {
            if (img.style.left.replace(px, "") <= -350) {
                img.style.left = ((imgCount - 1) * 350) + px;
                container.insertBefore(img, imgs[imgCount - 1])
            }
            img.style.left = (img.style.left.replace(px, "") - 1) + px;
        })
    }, intervalo / speed)
}