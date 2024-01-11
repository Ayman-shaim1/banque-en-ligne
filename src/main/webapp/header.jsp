<%@ page import="dao.Client.Client" %><%
    Client client = (Client) request.getAttribute("client");
%>
<nav class="navbar navbar-light bg-white">
    <a class="navbar-brand" href="/banque_war_exploded/index.jsp">Banque en-ligne</a>
      <div class="d-flex align-items-center">
          <form action="/banque_war_exploded/logout" method="get">
              <button type="submit" class="btn btn-danger" href="#" role="button">se deconnecter</button>
          </form>
      </div>
</nav>
<% if(!request.getRequestURI().equals("/banque_war_exploded/") || request.getRequestURI().equals("/banque_war_exploded")) {%>
<a class="btn btn-secondary m-2 mt-2" href="/banque_war_exploded/">
    retourner
</a>
<% } %>
<div class="d-flex justify-content-center mt-2">
    <% if(client != null) {%>

      <h1>Bonjour <%= client.getPrenom() %> <%= client.getNom() %> !</h1>
    <%}%>
</div>


