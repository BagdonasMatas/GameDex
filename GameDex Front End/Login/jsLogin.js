
let loginBtn = document.getElementById("myLogForm");
loginBtn.addEventListener("submit",loginUser);

async function loginUser(e){
    e.preventDefault();
    let logEmail = document.getElementById("email").value;
    let logPass = document.getElementById("password").value;

    await fetch("http://localhost:8090/api/auth/login", {
        method: "POST",
        headers:{
            "Content-type":"application/json"
        },
        body:JSON.stringify({email:logEmail,password:logPass})
    })
    .then(res=>res.json())
    .then(user =>{
        console.log(user);
        localStorage.setItem("userToken", user.token);
        console.log(localStorage.getItem("userToken"));
        localStorage.setItem("userId", user.id);
        console.log("test");
    })
    //To be implemented fetch for getting the username and id to put into local storage via the user's email.
    await getUsername();
    window.location.href = "/GameDex/GameDex Main/indexMain.html";
}
function getUsername(){
    fetch("http://localhost:8090/user/" + Number(localStorage.getItem("userId")), {
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
}