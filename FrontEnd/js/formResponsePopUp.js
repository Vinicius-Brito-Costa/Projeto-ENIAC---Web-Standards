export default async function formResponsePopUp(res){
    let element = document.getElementById("form-response");
    element.style.opacity = "100%";
    element.getElementsByTagName("span")[0].innerHTML = res.message;
    element.style.top = res.options.topStart;
    if(res.code == 0){
        element.style.backgroundColor = "rgb(0, 128, 0)";
    }
    else{
        element.style.backgroundColor = "rgb(252, 40, 0)";
    }
    setTimeout(() => {
        element.style.opacity = "0%";
        element.style.top = res.options.topEnd;
    }, 3000);
    return res.code == 0;
}