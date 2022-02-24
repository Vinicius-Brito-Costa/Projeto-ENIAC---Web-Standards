export default function formResponsePopUp(res){
    let element = document.getElementById("form-response");
    element.style.opacity = "100%";
    element.getElementsByTagName("span")[0].innerHTML = res.message;
    element.style.top = "0px";
    if(res.code == 0){
        element.style.backgroundColor = "rgb(0, 128, 0)";
    }
    else{
        element.style.backgroundColor = "rgb(252, 40, 0)";
    }
    setTimeout(() => {
        element.style.opacity = "0%";
        element.style.top = "35px";
    }, 3000);
}