let username = "";
let password = "";
let loginForm = document.getElementById("login");

loginForm.addEventListener('submit', function() {
    let ajax = new XMLHttpRequest();
    ajax.open("POST", "http://localhost:3000/static");
    ajax.onreadystatechange = function() {
        if(ajax.readyState == 4 && ajax.status == 200) {
            username = document.getElementById("username").innerText;
            password = document.getElementById("password").innerText;
            ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            ajaex.send("username=" + username + "&password="+password);
        }
    }
    

})

