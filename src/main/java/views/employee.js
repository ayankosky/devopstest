


function getSingleEmployee() {
    let ajax = new XMLHttpRequest();
    ajax.open("GET", "http://localhost:3000/form/employee", true);

    ajax.onreadystatechange = function () {
        if (ajax.readyState == 4 && ajax.status == 200) {
            let data = JSON.parse(ajax.responseText)

            let name = data.firstName + " " + data.lastName;
            let table = document.getElementById("listRequests");
            console.log(name)
            for (let i = 0; i < data["reimbursements"].length; i++) {
                let row = table.insertRow(-1);
                row.insertCell(0).innerHTML = name;
                row.insertCell(1).innerText = data["reimbursements"][i]["reimbursementType"];
                row.insertCell(2).innerText = data["reimbursements"][i]["amount"];
                row.insertCell(3).innerText = data["reimbursements"][i]["approvalStatus"];

            }
        }
    }
    ajax.send();
}

window.onload = function () {
    getSingleEmployee();
}
