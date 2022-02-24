import formResponsePopUp from "./formResponsePopUp.js";

const emailAddUrl = "http://localhost:8080/email"
export default function cadastrarEmail(event){
    event.preventDefault();
    let form = new FormData(event.target);
    let request = {
        email: form.get("email")
    }
    let options = {
        method: "POST",
        body: JSON.stringify(request),
        headers: {
            "Content-Type": "application/json"
        }
    }
    fetch(emailAddUrl, options).then(res => res.json()
    .then(res => {
        formResponsePopUp(res);
    }));
}