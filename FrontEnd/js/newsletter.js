import formResponsePopUp from "./formResponsePopUp.js";

const emailAddUrl = "http://localhost:8080/email"
export default async function cadastrarEmail(event){
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
    await fetch(emailAddUrl, options).then(res => res.json()
        .then(res => {
        res.options = {
            topStart: "0px",
            topEnd: "35px"
        }
        await formResponsePopUp(res);
    }));
}