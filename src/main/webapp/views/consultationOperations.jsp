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
    <title>Banque en-ligne (consultation des operations)</title>
</head>
<body class="bg-light">
<%@ include file="../header.jsp" %>
<div class="container">
    <div class="d-flex justify-content-center">
        <div class="col-md-4">
            <label>choisire un compte</label>
            <select class="form-control" onchange="getComptes(event)">
                <option value="">--- veuillez choisire un compte ---</option>
                <% for (Compte compte :lstcomptes){%>
                  <option value="<%= compte.getNumCompte() %>">compte numero : <%= compte.getNumCompte() %></option>
                <%}%>
            </select>
        </div>
    </div>
    <div class="card mt-5">
        <div class="card-body">
            <div class="d-flex justify-content-center my-2">
                <h4>Liste de vos comptes</h4>
            </div>
            <table class="table mt-3 ">
                <thead>
                <tr>
                    <th>numero d'operation</th>
                    <th>date d'operation</th>
                    <th>type d'operation</th>
                    <th>commission</th>
                </tr>
                </thead>
                <tbody id="ops">

                </tbody>
            </table>
        </div>
    </div>
</div>
<script>
    function getComptes(event){
        let url = ""
        let numCompte = event ? event.target.value : null;
        if(numCompte)
            url = '/banque_war_exploded/operationsByCompte?numCompte=' + numCompte;
        else
            url = "/banque_war_exploded/operationsByCompte";
        // Make a GET request to your servlet with parameters
        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json(); // Parse the response as JSON
            })
            .then(data => {
                let htmlOps = '';
                for(let op of data){
                   /* htmlOps += `<tr>
                        <td>${op.numOp}</td>
                        <td>${op.dateOp}</td>
                        <td>${op.type}</td>
                        <td>${op.commission}</td>
                    </tr>`*/
                    let formattedDate = new Date(op.dateOp).toLocaleString();
                    htmlOps += '<tr>';
                    htmlOps += "<td>"+op.numOp+"</td>";
                    htmlOps += "<td>"+formattedDate+"</td>";
                    htmlOps += "<td>"+op.type.toLowerCase()+"</td>";
                    htmlOps += "<td>"+op.commission+"</td>";
                    htmlOps += '</tr>';
                }
                document.getElementById('ops').innerHTML = htmlOps;
            })
            .catch(error => {
                console.error("Error calling servlet:", error);
            });
    }
    document.addEventListener('DOMContentLoaded', function () {
        console.log('DOM content loaded');
        getComptes();
    });
</script>
</body>
</html>
