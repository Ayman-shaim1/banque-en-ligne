<%@ page import="java.util.List" %>
<%@ page import="dao.Compte.Compte" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Compte> lstcomptes = (List<Compte>) request.getAttribute("lstcomptes");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />

    <title>Banque en-ligne (retrait)</title>
</head>
<body class="bg-light">
<%@ include file="../header.jsp" %>

<div class="container">
        <div class="d-flex justify-content-center">
            <div class="col-md-4 col-sm-12">
                <label>choisire un compte</label>
                <select class="form-control"  onchange="getComptes(event)">
                    <option value="">--- veuillez choisire un compte ---</option>
                    <% for (Compte compte :lstcomptes) {%>
                          <option value="<%= compte.getNumCompte() %>">compte numero : <%= compte.getNumCompte() %></option>
                    <%}%>
                </select>
            </div>
        </div>
        <div class="d-flex justify-content-center mt-2" >
            <div class="col-md-4 col-sm-12">
                 <div class="alert alert-info d-none" id="alert">
                     <div class="d-flex justify-content-center">
                         <h3><i class="fa-solid fa-wallet"></i></h3>
                     </div>
                     <span class="d-block">
                      numero de compte : <b id="numCompte">1</b>
                     </span>
                     <span class="d-block">
                         solde de compte : <b id="solde">1000</b>
                     </span>
                 </div>
            </div>
        </div>
        <div class="d-flex justify-content-center mt-5">
            <div class="col-md-4 col-sm-12">
                <form id="frmRetrait">
                    <label>Montant du retrait</label>
                    <input  disabled id="txtmt" class="form-control" type="number" placeholder="veuillez saisire le montant du retrait"/>
                    <button class="btn btn-primary w-100 mt-2" disabled id="btnrt">
                        retirer
                    </button>
                </form>
            </div>
        </div>
    </div>
<div aria-live="polite" aria-atomic="true" style="position: absolute;bottom: 0;margin:10px;left: 40%">
    <div id="toast-retrait-empty" class="toast align-items-center text-white bg-danger border-0" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                veuilliez saisire le montant a retirer
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
                retrait effectue avec success !
            </div>
            <button type="button" class="btn btn-sm text-white " data-bs-dismiss="toast" aria-label="Close">
                <i class="fa-solid fa-xmark"></i>
            </button>
        </div>
    </div>

</div>


<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

<script>
    const alert = document.getElementById("alert");
    const numcompteEl = alert.querySelector("#numCompte");
    const soldeEl = alert.querySelector("#solde");
    const txtmt = document.getElementById("txtmt");
    const btnrt = document.getElementById("btnrt");
    const frmRetrait = document.getElementById('frmRetrait');
    let currentNumCompte = 0;

    function getComptes(event){
        let numCompte = event ? event.target.value : null;
        if(numCompte){
            let url = '/banque_war_exploded/compteByNum?numCompte=' + numCompte;

            fetch(url)
                .then(response => {
                    if (!response.ok)
                        throw new Error('Network response was not ok');
                    return response.json();
                })
                .then(data => {
                    alert.classList.remove("d-none");
                    numcompteEl.innerText = data.numCompte;
                    currentNumCompte = Number(data.numCompte);
                    soldeEl.innerText = data.solde;

                    txtmt.removeAttribute("disabled");
                    btnrt.removeAttribute("disabled");
                    txtmt.value = "";

                })
                .catch(error => {
                    console.error("Error calling servlet:", error);
                });
        }
        else {
            alert.classList.add("d-none");
            numcompteEl.innerText = 0;
            soldeEl.innerText =0;
            txtmt.setAttribute("disabled",true);
            btnrt.setAttribute("disabled",true);
            txtmt.value = "";
        }



    }

    frmRetrait.addEventListener("submit", (e) => {
        e.preventDefault();

        if (txtmt.value !== "") {
            const url = '/banque_war_exploded/retrait?numCompte=' + currentNumCompte+"&solde="+txtmt.value;
            const xhr = new XMLHttpRequest();

            xhr.onreadystatechange = function() {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        const toastLiveExample = document.getElementById('toast-rt-efct');
                        const toast = new bootstrap.Toast(toastLiveExample);
                        soldeEl.innerText = Number(soldeEl.innerText) - (Number(txtmt.value) + 5);
                        toast.show();
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
</body>
</html>
