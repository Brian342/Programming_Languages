/*How to accept user input
1. Easy way = window prompt
2. Proffessional way = html textbox
*/


// let username = window.prompt(`What's your username? `)

// console.log(username)

let username

document.getElementById("mySubmit").onclick = function(){
    username = document.getElementById("myText").value;
    document.getElementById("myH1").textContent = `Welcome ${username}`
}