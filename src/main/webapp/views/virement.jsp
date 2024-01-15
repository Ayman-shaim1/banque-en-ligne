<%@ page import="dao.Compte.Compte" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Compte> lstcomptes = (List<Compte>) request.getAttribute("lstcomptes");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />


    <title>Banque en-ligne (virement)</title>
</head>
<body class="bg-light">
<%@ include file="../header.jsp" %>
<div class="container">

    <div class="d-flex justify-content-center">
        <div class="col-md-4">
            <label>choisire un compte expéditeur</label>
            <select class="form-control"  onchange="getComptesExp(event)" id="drp-cpt-exp">
                <option value="">--- veuillez choisire un compte ---</option>
                <% for (Compte compte :lstcomptes) {%>
                <option value="<%= compte.getNumCompte() %>">compte numero : <%= compte.getNumCompte() %></option>
                <%}%>
            </select>
        </div>
    </div>
    <div class="d-flex justify-content-center mt-2" >
        <div class="col-md-4">
            <div class="alert alert-info d-none" id="alert-cpt-exp">
                <div class="d-flex justify-content-center">
                    <h3><i class="fa-solid fa-wallet"></i></h3>
                </div>
                <span class="d-block">
                      numero de compte : <b id="numCompteExp">1</b>
                     </span>
                <span class="d-block">
                         solde de compte : <b id="soldeExp">1000</b>
                     </span>
            </div>
        </div>
    </div>

    <div class="d-flex justify-content-center mt-3">
        <div class="col-md-4">
            <label>choisire un compte destination</label>
            <select class="form-control" disabled id="drp-cpt-dest"  onchange="getComptesDest(event)">
                <option value="">--- veuillez choisire un compte ---</option>
                <% for (Compte compte :lstcomptes) {%>
                <option value="<%= compte.getNumCompte() %>">compte numero : <%= compte.getNumCompte() %></option>
                <%}%>
            </select>
        </div>
    </div>
    <div class="d-flex justify-content-center mt-2" >
        <div class="col-md-4">
            <div class="alert alert-info d-none" id="alert-cpt-dest">
                <div class="d-flex justify-content-center">
                    <h3><i class="fa-solid fa-wallet"></i></h3>
                </div>
                <span class="d-block">
                      numero de compte : <b id="numCompteDest">1</b>
                     </span>
                <span class="d-block">
                         solde de compte : <b id="soldeDest">1000</b>
                </span>
            </div>
        </div>
    </div>
    <div class="d-flex justify-content-center mt-2">
        <div class="col-md-4">
            <form id="frmVerser">
                <label>Montant a verser</label>
                <input  disabled id="txtmt" class="form-control" type="number" placeholder="veuillez saisire le montant a verser"/>
                <button class="btn btn-primary w-100 mt-2" disabled id="btnvr">
                    verser
                </button>
            </form>
        </div>
    </div>
</div>


<div aria-live="polite" aria-atomic="true" style="position: absolute;bottom: 0;margin:10px;left: 40%">
    <div id="toast-retrait-empty" class="toast align-items-center text-white bg-danger border-0" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                veuilliez saisire le montant de versemet
            </div>
            <button type="button" class="btn btn-sm text-white " data-bs-dismiss="toast" aria-label="Close">
                <i class="fa-solid fa-xmark"></i>
            </button>
        </div>
    </div>
    <div id="toast-solde-err" class="toast align-items-center text-white bg-danger border-0" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                le montant que vous avez saisi doit être inférieur au solde de ce compte.
            </div>
            <button type="button" class="btn btn-sm text-white " data-bs-dismiss="toast" aria-label="Close">
                <i class="fa-solid fa-xmark"></i>
            </button>
        </div>
    </div>
    <div id="toast-rt-efct" class="toast align-items-center text-white bg-success border-0" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                virement effectue avec success !
            </div>
            <button type="button" class="btn btn-sm text-white " data-bs-dismiss="toast" aria-label="Close">
                <i class="fa-solid fa-xmark"></i>
            </button>
        </div>
    </div>
</div>


<script>
    const alertExp = document.getElementById("alert-cpt-exp");
    const numcompteExpEl = alertExp.querySelector("#numCompteExp");
    const soldeExpEl = alertExp.querySelector("#soldeExp");
    const drpCptExp = document.getElementById("drp-cpt-exp");

    const alertDest = document.getElementById("alert-cpt-dest");
    const numcompteDestEl = alertDest.querySelector("#numCompteDest");
    const soldeDestEl = alertDest.querySelector("#soldeDest");
    const drpCptDest = document.getElementById("drp-cpt-dest");

    const txtmt = document.getElementById("txtmt");
    const btnvr = document.getElementById("btnvr");

    const frmVerser = document.getElementById('frmVerser');

    let currentNumCompteExp = null;
    let currentNumCompteDest = null;


    let optionsDest = [];
    drpCptDest.querySelectorAll('option').forEach((option) => {
        if(option.value !== "")
            optionsDest.push(option.value);
    });


    function fillDropDown(option){
        let nOptionsDest = optionsDest.filter(op => op != option);
        let htmlOptions = "<option value=''>--- veuillez choisire un compte ---</option>";
        for(const op of nOptionsDest)
            htmlOptions += "<option value="+op+">compte numero : "+op+"</option>";
        drpCptDest.innerHTML = htmlOptions;
        drpCptDest.removeAttribute("disabled");
        alertDest.classList.add("d-none");
    }


    function getComptesExp(event) {
        let numCompte = event ? event.target.value : null;
        if (numCompte) {
            let url = '/banque_war_exploded/compteByNum?numCompte=' + numCompte;
            fetch(url)
                .then(response => {
                    if (!response.ok)
                        throw new Error('Network response was not ok');
                    return response.json();
                })
                .then(data => {
                    alertExp.classList.remove("d-none");
                    numcompteExpEl.innerText = data.numCompte;
                    soldeExpEl.innerText = data.solde;
                    currentNumCompteExp = numCompte;
                    fillDropDown(numCompte);
                })
                .catch(error => {
                    console.error("Error calling servlet:", error);
                });
        } else {
            alertExp.classList.add("d-none");
            alertDest.classList.add("d-none");
            numcompteExpEl.innerText = 0;
            soldeExpEl.innerText = 0;

            numcompteDestEl.innerText = 0;
            soldeDestEl.innerText = 0;
            drpCptDest.setAttribute("disabled",true);
            drpCptDest.selectedIndex = 0;

            txtmt.setAttribute("disabled",true);
            btnvr.setAttribute("disabled",true);

            txtmt.value = "";

        }
    }

    function getComptesDest(event) {
        let numCompte = event ? event.target.value : null;
        if (numCompte) {

            let url = '/banque_war_exploded/compteByNum?numCompte=' + numCompte;

            fetch(url)
                .then(response => {
                    if (!response.ok)
                        throw new Error('Network response was not ok');
                    return response.json();
                })
                .then(data => {
                    alertDest.classList.remove("d-none");
                    numcompteDestEl.innerText = data.numCompte;
                    soldeDestEl.innerText = data.solde;
                    txtmt.removeAttribute('disabled');
                    btnvr.removeAttribute('disabled');

                    currentNumCompteDest = numCompte;
                })
                .catch(error => {
                    console.error("Error calling servlet:", error);
                });
        } else {
            alertDest.classList.add("d-none");
            numcompteDestEl.innerText = 0;
            soldeDestEl.innerText = 0;
            txtmt.setAttribute("disabled",true);
            btnvr.setAttribute("disabled",true);

            txtmt.value = "";
        }
    }

    frmVerser.addEventListener("submit", (e) => {
        e.preventDefault();

        if (txtmt.value !== "") {
            const url = '/banque_war_exploded/virement?numCompteExp=' + currentNumCompteExp+ "&numCompteDest=" + currentNumCompteDest+"&solde="+txtmt.value;
            const xhr = new XMLHttpRequest();

            xhr.onreadystatechange = function() {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        const toastLiveExample = document.getElementById('toast-rt-efct');
                        const toast = new bootstrap.Toast(toastLiveExample);

                        const soldeDest = Number(txtmt.value);
                        const soldeDestElValue = Number(soldeDestEl.innerText);
                        const soldeExpElValue = Number(soldeExpEl.innerText);

                        toast.show();



                        soldeDestEl.innerText = soldeDestElValue + soldeDest ;
                        soldeExpEl.innerText =soldeExpElValue - (soldeDest + (soldeDest * 5 /100 ))

                        txtmt.value = "";

                    } else {
                        console.error('XHR error:', xhr.statusText);
                        console.log(xhr.responseText);
                        const toastLiveExample = document.getElementById('toast-solde-err');
                        const toast = new bootstrap.Toast(toastLiveExample);
                        toast.show();

                    }
                }
            };

            xhr.open('POST', url, true);
            xhr.setRequestHeader('Content-Type', 'application/json');
            xhr.send(JSON.stringify({ /* Your data here */ }));
        } else {
            const toastLiveExample = document.getElementById('toast-retrait-empty');
            const toast = new bootstrap.Toast(toastLiveExample);
            toast.show();
        }
    });


</script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>
</html>
