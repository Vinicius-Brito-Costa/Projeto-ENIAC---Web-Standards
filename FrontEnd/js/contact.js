import formResponsePopUp from "./formResponsePopUp.js";
const contactAddUrl = "http://localhost:8080/contact"
export function cadastrarMensagem(event){
    event.preventDefault();
    let form = new FormData(event.target);
    let request = {
        name: form.get("nome"),
        email: form.get("email"),
        message: form.get("mensagem")
    }
    let options = {
        method: "POST",
        body: JSON.stringify(request),
        headers: {
            "Content-Type": "application/json"
        }
    }
    fetch(contactAddUrl, options).then(res => res.json())
    .then(res => {
        res.options = {
            topStart: "400px",
            topEnd: "445px"
        }
        return formResponsePopUp(res)
    })
    .then(res => {
        if(res == true){
            event.target.reset();
        }
    })
}