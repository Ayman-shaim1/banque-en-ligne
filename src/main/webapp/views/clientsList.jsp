<%@ page import="java.util.List" %>
<%@ page import="dao.Client.Client" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Client> lstClients = (List<Client>) request.getAttribute("clients");
%>
<html>
<head>
    <title>clients</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"/>
</head>
<body>
    <p></p>
    <% for(Client client : lstClients) { %>
          <p><%= client.toString() %></p>
    <% }%>
</body>
</html>
