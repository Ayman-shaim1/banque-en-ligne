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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />

    <title>Banque en-ligne (virement)</title>
</head>
<body class="bg-light">
<%@ include file="../header.jsp" %>
<div class="container">
    <div class="d-flex justify-content-center">
        <div class="col-md-4">
            <div class="form-check form-switch">
                <input class="form-check-input" type="checkbox" role="switch" id="chk-cpt">

                <label class="form-check-label" for="chk-cpt">
                    votre compte ou autre compte
                </label>
            </div>
        </div>
    </div>
    <div class="d-flex justify-content-center">
        <div class="col-md-4">
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
        <div class="col-md-4">
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
</div>
<script>
    const alert = document.getElementById("alert");
    const numcompteEl = alert.querySelector("#numCompte");
    const soldeEl = alert.querySelector("#solde");
    const txtmt = document.getElementById("txtmt");
    const btnrt = document.getElementById("btnrt");
    const frmRetrait = document.getElementById('frmRetrait');
    let currentNumCompte = 0;

    function getComptes(event) {
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
                    alert.classList.remove("d-none");
                    numcompteEl.innerText = data.numCompte;
                    currentNumCompte = Number(data.numCompte);
                    soldeEl.innerText = data.solde;

                    txtmt.removeAttribute("disabled");
                    btnrt.removeAttribute("disabled")
                })
                .catch(error => {
                    console.error("Error calling servlet:", error);
                });
        } else {
            alert.classList.add("d-none");
            numcompteEl.innerText = 0;
            soldeEl.innerText = 0;
            txtmt.setAttribute("disabled", true);
            btnrt.setAttribute("disabled", true);
        }
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>
</html>
