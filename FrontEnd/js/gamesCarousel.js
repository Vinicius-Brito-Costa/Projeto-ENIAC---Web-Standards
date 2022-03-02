import images from "./../config/images.js";
let imgs = [];
const witdh = 350
const height = 507
const px = "px";

function loadImages(quantity) {
    let min = 0
    let max = images.imgs.length - 1
    
    for (let i = 0; i < quantity; i++){
        let img = document.createElement("img");
        let imgName = images.imgs[randomNumber(min, max)]
        img.src = "images/covers/" + imgName
        img.alt = imgName
        let container = document.getElementById("games-container")
        img.style.width = witdh + px
        img.style.height = height + px
        img.style.left = (i * witdh) + px
        imgs = [...imgs, img]
        container.appendChild(img)
    }
}

function randomNumber(min, max){
    return Math.floor(Math.random() * (max - min)) + min
}

/**
 * Load images, set then as a child of an element named 'games-container', 
 * the carousel will loop over a random set of images and move then from right to left, 
 * when a image leaves completly the screen its position is set right after the last image and the 
 * image element is set as the last child-element of the parent container
 * @param  {[number]} speed speed in which the carousel move the images from right to left. Higher is faster.
 * @param {[number]} imageQuantity quantity of images loaded to make the carousel. Default is 10.
 * @return {[type]} void
 */
export function carousel(speed, imageQuantity = 10) {

    loadImages(imageQuantity);

    let intervalo = 1000
    let container = document.getElementById("games-container")

    container.style.height = height + px;
    setInterval(() => {
        imgs.forEach(img => {
            if (img.style.left.replace(px, "") <= -witdh) {
                img.style.left = ((imageQuantity - 1) * witdh) + px;
                container.insertBefore(img, imgs[imageQuantity - 1])
            }
            img.style.left = (img.style.left.replace(px, "") - 1) + px;
        })
    }, intervalo / speed)
}