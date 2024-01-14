<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>Banque en-ligne</title>
</head>
<body class="bg-light">
<%@ include file="header.jsp" %>
<div class="container mt-5">
    <div class="row">
        <div class="col-md-6 d-flex align-items-stretch">
            <div class="card flex-fill mb-4">
                <div class="card-body">
                    <h5 class="card-title">Solde</h5>
                    <p class="card-text">Vérifier le solde de votre compte</p>
                    <a href="/banque_war_exploded/solde" class="btn btn-primary">Accéder</a>
                </div>
            </div>
        </div>
        <div class="col-md-6 d-flex align-items-stretch">
            <div class="card flex-fill mb-4">
                <div class="card-body">
                    <h5 class="card-title">Retrait</h5>
                    <p class="card-text">Effectuer une opération de retrait.</p>
                    <a href="/banque_war_exploded/retrait" class="btn btn-primary">Accéder</a>
                </div>
            </div>
        </div>
        <div class="col-md-6 d-flex align-items-stretch">
            <div class="card flex-fill mb-4">
                <div class="card-body">
                    <h5 class="card-title">Virement</h5>
                    <p class="card-text">Transférer des fonds vers un autre compte.</p>
                    <a href="/banque_war_exploded/virement" class="btn btn-primary">Accéder</a>
                </div>
            </div>
        </div>
        <div class="col-md-6 d-flex align-items-stretch">
            <div class="card flex-fill mb-4">
                <div class="card-body">
                    <h5 class="card-title">Consultation des opérations</h5>
                    <p class="card-text">Consulter les détails des transactions.</p>
                    <a href="/banque_war_exploded/operations" class="btn btn-primary">Accéder</a>
                </div>
            </div>
        </div>



    </div>
</div>

<!-- Bootstrap JS and Popper.js -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
