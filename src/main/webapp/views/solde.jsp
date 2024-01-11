<%@ page import="dao.Compte.Compte" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    double totalSoldes = (double) request.getAttribute("totalSoldes");
    List<Compte> lstcomptes = (List<Compte>)request.getAttribute("lstcomptes");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>Banque en-ligne (solde)</title>
</head>
<body class="bg-light">
<%@ include file="../header.jsp" %>
<div class="container">

    <div class="d-flex justify-content-center mt-4">
        <h1>
            Total de solde de tous les comptes : <span id="ttl"><%= totalSoldes %></span>
        </h1>
    </div>

    <div class="card mt-5">
        <div class="card-body">
            <div class="d-flex justify-content-center my-2">
                <h4>Liste de vos comptes</h4>
            </div>
            <table class="table mt-3 ">
                <thead>
                <tr>
                    <th>numero de compte</th>
                    <th>solde</th>
                </tr>
                </thead>
                <tbody>
                <% for(Compte compte:lstcomptes) {%>
                <tr>
                    <td><%= compte.getNumCompte() %></td>
                    <td><%= compte.getSolde() %></td>
                </tr>
                <%}%>
                </tbody>
            </table>
        </div>
    </div>

</div>

</body>
<script>
    document.addEventListener('DOMContentLoaded', function () {});
</script>
</html>
