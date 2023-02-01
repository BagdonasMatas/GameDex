let registerBtn = document.getElementById("myRegForm");
registerBtn.addEventListener("submit",registerUser);
let arr=[];

async function registerUser(e){
    e.preventDefault();
    let regEmail = document.getElementById("email").value;

    let regPass = document.getElementById("password").value;
    let regRepeatPass = document.getElementById("repeatPassword").value;
    let regUsername = document.getElementById("username").value;
    let regId;
    if(regUsername.length >= 6 && regUsername.length <= 20){
        if(regPass.length >= 6 && regPass.length <= 15){
            if(regPass === regRepeatPass){
    localStorage.setItem("userUsername",regUsername);
    await fetch("http://localhost:8090/api/auth/signup", {
        method: "POST",
        headers:{
            "Content-type":"application/json"
        },
        body:JSON.stringify({email:regEmail, password:regPass, repeatPassword:regRepeatPass, username:regUsername})
    })
    .then(res=> res.json())
    .then(data=> console.log(data))

    await fetch("http://localhost:8090/api/auth/login", {
        method: "POST",
        headers:{
            "Content-type":"application/json"
        },
        body:JSON.stringify({email:regEmail,password:regPass})
    })
    .then(res=>res.json())
    .then(user =>{
        localStorage.setItem("userToken", user.token);
        localStorage.setItem("userId", user.id);
    })

    await fetch("http://localhost:8090/user/" + Number(localStorage.getItem("userId")), {
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("userToken"),
        },
        method: 'GET'
        })
        .then(res => res.json())
        .then(user =>{
            localStorage.setItem("userUsername", user.username);
            console.log(user.username);
        });

    window.location.href = "/GameDex Front End/GameDex Main/indexMain.html"
            }
            else{
                alert("Your password and repeated password do not match.")
            }
        }
        else{
            alert("Your password must be between 6 and 15 characters long.")
        }
    }
    else{
        alert("Your username must be between 6 and 20 characters long.")
    }
}