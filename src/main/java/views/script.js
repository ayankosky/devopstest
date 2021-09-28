

let username = "";
let password = "";
let loginForm = document.getElementById("login");
let submitBtn = document.getElementById("submit");
let names = document.getElementById("names");
let employeeList = [];
let table = document.getElementById("listRequests");
let averageCost = document.getElementById("average");
let mostRequests = document.getElementById("most");
let totalPending = document.getElementById("pending");


window.onload = function () {
    getAllRequests();
}


function mostRequestsFinder() {

    let name = "";
    let count = 0;
    for (let emp of employeeList) {
        if (emp["reimbursements"].length > count) {
            name = emp.firstName + " " + emp.lastName;
            count = emp["reimbursements"].length;
        }
    }
    mostRequests.innerText = name;
}

function averageCostFinder() {
    let total = 0;
    let count = 0;
    for (let emp of employeeList) {
        
            for (let i = 0; i < emp["reimbursements"].length; i++) {
                if (emp["reimbursements"][i]["approvalStatus"] == "approved" || emp["reimbursements"][i]["approvalStatus"] == "Approved"){
                    total += emp["reimbursements"][i]["amount"];
                count += count + 1;
                console.log(total);
                console.log(count);
            }
        }

    }
    let result = count / total;
    result = result.toFixed(2);
    averageCost.innerText = "$" + result;
}

function totalPendingFinder() {
    let total = 0;

    for (let emp of employeeList) {
        for (let i = 0; i < emp.reimbursements.length; i++) {
            if (emp["reimbursements"][i]["approvalStatus"] == "pending" || emp["reimbursements"][i]["approvalStatus"] == "Pending") {
                total += 1;
            }
        }
    }
    totalPending.innerText = total;
}

function getStats() {
    totalPendingFinder();
    averageCostFinder();
    mostRequestsFinder()
}



function getAllRequests() {

    let ajax = new XMLHttpRequest();
    ajax.open("GET", "http://localhost:3000/form/manager", true);

    ajax.onreadystatechange = function () {
        if (ajax.readyState == 4 && ajax.status == 200) {
            let data = JSON.parse(ajax.responseText)
            employeeList = data;
            let option = document.createElement("option");
            option.innerHTML = "All Employees";
            names.appendChild(option);
            for (let d of data) {
                let i = 0;
                let name = d.firstName + " " + d.lastName;
                let option2 = document.createElement("option");
                option2.innerHTML = name;
                option2.setAttribute("id", name);
                names.appendChild(option2);


                for (let j = 0; j < d.reimbursements.length; j++) {
                    let row = table.insertRow(-1);
                    row.insertCell(0).innerHTML = name;
                    row.insertCell(1).innerText = d["reimbursements"][j]["reimbursementType"];
                    row.insertCell(2).innerText = d["reimbursements"][j]["amount"];


                    let check = d["reimbursements"][j]["approvalStatus"];


                    if (check == "Pending" || check == "pending") {

                        let link = document.createElement("select");
                        link.class = ("status");


                        link.id = (d["reimbursements"][j]["reimbursementId"]);
                        let pending = document.createElement("option")
                        pending.innerHTML = "Pending";
                        link.appendChild(pending);
                        let approved = document.createElement("option")
                        approved.innerHTML = "Approved";
                        link.appendChild(approved);
                        let denied = document.createElement("option")
                        denied.innerHTML = "Denied";
                        link.appendChild(denied);
                        row.insertCell(3).append(link);

                    } else {
                        row.insertCell(3).innerText = d["reimbursements"][j]["approvalStatus"];
                    }


                }
                i++;

            }
        }
    }
    ajax.send();
    setTimeout(function () { getStats(); }, 4000);
}

// reimbursements.addEventListener("click", function() {
//     let params = new URLSearchParams();
//     params.append("id", this.getAttribute("id"));
//     location.href = "/form/updateForm.html?" + params.toString();
// })


document.addEventListener("change", function (e) {

    let x = e.target;
    console.log(x.value);
    if (x.value == "Approved" || x.value == "Denied") {

        let ajax = new XMLHttpRequest();
        ajax.open("PATCH", "http://localhost:3000/form/manager/updateForm");
        ajax.onreadystatechange = function () {
            if (ajax.readyState == 4 && ajax.status == 200) {
            }
        };

        let data = {
            "reimbursementId": x.id,
            "approvalStatus": x.value
        };
        ajax.send(JSON.stringify(data));
        setTimeout(function () { location.reload(true); }, 2000);

    }
})


names.addEventListener("change", function () {
    while (table.firstChild) {
        table.removeChild(table.firstChild);
    }
    let filter = names.value
    for (let emp of employeeList) {
        let i = 0;
        let name = emp.firstName + " " + emp.lastName;
        if (filter == name || filter == "All Employees") {
            for (let j = 0; j < emp.reimbursements.length; j++) {
                let row = table.insertRow(-1);
                row.insertCell(0).innerHTML = name;
                row.insertCell(1).innerText = emp["reimbursements"][j]["reimbursementType"];
                row.insertCell(2).innerText = emp["reimbursements"][j]["amount"];

                let check = emp["reimbursements"][j]["approvalStatus"];


                if (check == "Pending" || check == "pending") {

                    let link = document.createElement("select");


                    link.id = (emp["reimbursements"][j]["reimbursementId"]);
                    let pending = document.createElement("option")
                    pending.innerHTML = "Pending";
                    link.appendChild(pending);
                    let approved = document.createElement("option")
                    approved.innerHTML = "Approved";
                    link.appendChild(approved);
                    let denied = document.createElement("option")
                    denied.innerHTML = "Denied";
                    link.appendChild(denied);
                    row.insertCell(3).append(link);

                } else {
                    row.insertCell(3).innerText = emp["reimbursements"][j]["approvalStatus"];
                }

            }
            i++;

        }

    }
})










