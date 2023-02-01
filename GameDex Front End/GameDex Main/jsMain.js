window.onload = document.getElementById("userWelcome").textContent = "Welcome " + localStorage.getItem("userUsername") + "!";

let changesBtn = document.getElementById("changeButton");
changesBtn.addEventListener("click",saveChanges);

let gameArr = [];
let userGameArr = [];
let existingEntryId;
let alreadyAnEntry = false;

getGameList()
async function getGameList(){
    await fetch("http://localhost:8090/game/", {
        headers: {Authorization: 'Bearer ' + localStorage.getItem("userToken")},
        method: 'GET'
    })
        .then(res => res.json())
        .then(data => data.forEach(element => {
                gameArr.push([element.name, element.genre, element.averageRating, element.id]);
    }))
    gameArr.forEach(element => console.log(element));
    console.log(gameArr);

    for(let i = 0 ; i < gameArr.length ; i++){
        console.log("inputingGame");
    document.getElementById("gameList").innerHTML += "<div id=\"game" + i +"\"><hr><p>NAME: " + gameArr[i][0] + "</p><p>GENRE: " + gameArr[i][1] + "</p><p>AVERAGE RATING: " + gameArr[i][2] +"</p> <p>YOUR RATING: </p> <select name= \"gameSelect" + i + "\" id=\"gameSelect" + i +"\"> <option value=\"1\">1</option> <option value=\"2\">2</option> <option value=\"3\">3</option> <option value=\"4\">4</option> <option value=\"5\">5</option> <option value=\"6\">6</option> <option value=\"7\">7</option> <option value=\"8\">8</option> <option value=\"9\">9</option> <option value=\"10\">10</option> <option value=\"not played\" selected = \"selected\">Not played</option>";
    }

    await fetch("http://localhost:8090/usergame/UserGames/" + localStorage.getItem("userId"), {
        headers: {Authorization: 'Bearer ' + localStorage.getItem("userToken")},
        method: 'GET'
    })
        .then(res => res.json())
        .then(data => data.forEach(element => {
            userGameArr.push([element.gameId, element.rating, element.id]);
            console.log(userGameArr);
    }))

    for(let i = 0 ; i < gameArr.length ; i++){
        for(let j = 0 ; j < userGameArr.length ; j++){
            console.log(gameArr[i][3]);
            if(Number(gameArr[i][3]) == Number(userGameArr[j][0])){
                document.getElementById("gameSelect" + i).value= userGameArr[j][1];
            }
        }
    }
}

async function saveChanges(){

    for(let i = 0 ; i < gameArr.length ; i++){
        if(document.getElementById("gameSelect" + i).value != "not played"){
            for(let j = 0 ; j < userGameArr.length ; j++){
            if(Number(gameArr[i][3]) == Number(userGameArr[j][0])){
                alreadyAnEntry = true;
                existingEntryId = userGameArr[j][2];
            }
            }
            if(alreadyAnEntry){
            await fetch("http://localhost:8090/usergame/" + existingEntryId, {
            method: "PUT",
            headers:{
            Authorization: 'Bearer ' + localStorage.getItem("userToken"),
            "Content-type":"application/json"
            },
                body:JSON.stringify({id:existingEntryId, rating:document.getElementById("gameSelect" + i).value, gameId:gameArr[i][3], userId:localStorage.getItem("userId")})
            })
            .then(res=> res.json())
            .then(data=> console.log(data));
            }
            else{
                await fetch("http://localhost:8090/usergame/", {
            method: "POST",
            headers:{
            Authorization: 'Bearer ' + localStorage.getItem("userToken"),
            "Content-type":"application/json"
            },
                body:JSON.stringify({rating:document.getElementById("gameSelect" + i).value, gameId:gameArr[i][3], userId:localStorage.getItem("userId")})
            })
            .then(res=> res.json())
            .then(data=> console.log(data));
            }
            alreadyAnEntry = false;
        }
    }
    window.location.reload();
}